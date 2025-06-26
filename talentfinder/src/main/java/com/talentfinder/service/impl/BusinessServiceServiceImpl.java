package com.talentfinder.service.impl;

import com.talentfinder.dto.BusinessServiceDto;
import com.talentfinder.dto.BusinessServicePreviewDto;
import com.talentfinder.dto.CreateBusinessServiceDto;
import com.talentfinder.dto.FilterDto;
import com.talentfinder.model.BusinessService;
import com.talentfinder.model.Location;
import com.talentfinder.model.Picture;
import com.talentfinder.model.ServiceCategory;
import com.talentfinder.model.User;
import com.talentfinder.model.Video;
import com.talentfinder.repository.CategoryRepository;
import com.talentfinder.repository.LocationRepository;
import com.talentfinder.repository.ServiceRepository;
import com.talentfinder.repository.UserRepository;
import com.talentfinder.service.BusinessServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class BusinessServiceServiceImpl implements BusinessServiceService {

    private static final String UPLOAD_DIR = "uploads";

    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public BusinessServiceServiceImpl(ServiceRepository serviceRepository, UserRepository userRepository,
                                      CategoryRepository categoryRepository, LocationRepository locationRepository) {
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<BusinessServicePreviewDto> getAllBusinessServices() {
        List<BusinessService> services = serviceRepository.findAll();
        return services.stream().map(BusinessServicePreviewDto::fromEntity).toList();
    }

    @Override
    public BusinessServiceDto getBusinessServiceById(Long id) {
        Optional<BusinessService> service = serviceRepository.findById(id);
        return service.map(BusinessServiceDto::fromEntity).orElse(null);
    }

    @Override
    public List<BusinessServicePreviewDto> getBusinessServiceByFilteringCriteria(FilterDto filterDto) {
        List<BusinessService> services;
        if (filterDto.getServiceName() != null && !filterDto.getServiceName().isEmpty()) {
            services = serviceRepository.findAllByTitleContaining(filterDto.getServiceName());
        } else {
            services = serviceRepository.findAll();
        }
        if (filterDto.getUsername() != null && !filterDto.getUsername().isEmpty()) {
            services = filterByUser(services, filterDto);
        }
        if (filterDto.getLocation() != null) {
            services = filterByLocation(services, filterDto);
        }
        if (filterDto.getCategoryName() != null) {
            services = filterByCategory(services, filterDto);
        }
        if (filterDto.getMinPrice() != null && filterDto.getMinPrice() > 0) {
            services = filterByMinPrice(services, filterDto);
        }
        if (filterDto.getMaxPrice() != null && filterDto.getMaxPrice() > 0) {
            services = filterByMaxPrice(services, filterDto);
        }

        return services.stream().map(BusinessServicePreviewDto::fromEntity).toList();
    }

    @Override
    public BusinessService createBusinessService(CreateBusinessServiceDto businessServiceDto) {
        BusinessService businessService = createBusinessServiceEntity(businessServiceDto);
        return serviceRepository.save(businessService);
    }

    private BusinessService createBusinessServiceEntity(CreateBusinessServiceDto businessServiceDto){
        BusinessService service = new BusinessService();
        User user = null;
        ServiceCategory category = getCategory(businessServiceDto.getServiceCategory());
        Location location = getLocation(businessServiceDto.getLocation());
        Optional<User> userOpt = userRepository.findByUsername(businessServiceDto.getUsername());
        if(userOpt.isPresent()){
            user = userOpt.get();
        }
        service.setUser(user);

        List<String> imageUrls = saveImages(businessServiceDto.getImages(), businessServiceDto.getUsername());
        List<Picture> pictures = imageUrls.stream().map(Picture::new).toList();
        service.addPictures(pictures);

        List<Video> videos = businessServiceDto.getVideoLinks().stream().map(Video::new).toList();
        service.addVideos(videos);

        service.setTitle(businessServiceDto.getTitle());
        service.setDescription(businessServiceDto.getDescription());
        service.setPrice(businessServiceDto.getPrice());
        service.setCategory(category);
        service.setLocation(location);

        return service;
    }

    private List<String> saveImages(List<MultipartFile> images, String username) {
        List<String> imageFilenames = new ArrayList<>();
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            for (MultipartFile image : images) {
                if (!image.isEmpty()) {
                    // Validate file type
//                    String contentType = image.getContentType();
//                    if (!isValidImageType(contentType)) {
//                        return ResponseEntity.badRequest()
//                                .body(new ApiResponse(false, "Invalid image type: " + contentType));
//                    }

                    // Validate file size (5MB limit)
//                    if (image.getSize() > 5 * 1024 * 1024) {
//                        return ResponseEntity.badRequest()
//                                .body(new ApiResponse(false, "Image size exceeds 5MB limit"));
//                    }

                    // Generate unique filename
                    String originalFilename = image.getOriginalFilename();
                    String fileExtension = getFileExtension(originalFilename);
                    String uniqueFilename = generateUniqueFilename(username, fileExtension);

                    // Save the file
                    Path filePath = uploadPath.resolve(uniqueFilename);
                    Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                    imageFilenames.add(uniqueFilename);
                }
            }
        } catch (IOException e) {

        }

        return imageFilenames;
    }

    private Location getLocation(String name){
        Optional<Location> locationOpt = locationRepository.findByLocationName(name);
        if(locationOpt.isPresent()){
            return locationOpt.get();
        }
        return null;
    }

    private ServiceCategory getCategory(String name){
        Optional<ServiceCategory> categoryOpt = categoryRepository.findByName(name);
        if(categoryOpt.isPresent()){
            return categoryOpt.get();
        }
        return null;
    }

    private String getFileExtension(String filename) {
        if (filename != null && filename.contains(".")) {
            return filename.substring(filename.lastIndexOf("."));
        }
        return ".jpg"; // default extension
    }

    private String generateUniqueFilename(String username, String extension) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        return username + "_" + timestamp + "_" + uuid + extension;
    }

    private List<BusinessService> filterByUser(List<BusinessService> services, FilterDto filterDto) {
        return services.stream()
                .filter(s -> s.getUser().getUsername().contains(filterDto.getUsername()))
                .toList();
    }

    private List<BusinessService> filterByCategory(List<BusinessService> services, FilterDto filterDto) {
        return services.stream()
                .filter(s -> Objects.equals(s.getCategory().getName(), filterDto.getCategoryName()))
                .toList();
    }

    private List<BusinessService> filterByLocation(List<BusinessService> services, FilterDto filterDto) {
        return services.stream()
                .filter(s -> Objects.equals(s.getLocation().getLocationName(), filterDto.getLocation()))
                .toList();
    }

    private List<BusinessService> filterByMaxPrice(List<BusinessService> services, FilterDto filterDto) {
        return services.stream().filter(s -> s.getPrice() <= filterDto.getMaxPrice()).toList();
    }

    private List<BusinessService> filterByMinPrice(List<BusinessService> services, FilterDto filterDto) {
        return services.stream().filter(s -> s.getPrice() >= filterDto.getMinPrice()).toList();
    }
}
