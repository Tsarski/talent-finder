package com.talentfinder.service.impl;

import com.talentfinder.dto.BusinessServiceDto;
import com.talentfinder.dto.BusinessServicePreviewDto;
import com.talentfinder.dto.FilterDto;
import com.talentfinder.model.BusinessService;
import com.talentfinder.repository.ServiceRepository;
import com.talentfinder.service.BusinessServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BusinessServiceServiceImpl implements BusinessServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public BusinessServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
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
        return services.stream().map(BusinessServicePreviewDto::fromEntity).toList();
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
}
