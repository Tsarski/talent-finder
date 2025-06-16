package com.talentfinder.dto;

import com.talentfinder.model.Picture;
import com.talentfinder.model.BusinessService;
import com.talentfinder.model.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessServiceDto {
    private Long id;

    private String title;

    private String description;

    private Double price;

    private String serviceCategory;

    private String location;

    private PublicUserDataDto user;

    private List<String> pictures;

    private List<String> videos;

    private List<ReviewDto> reviews;

    public static BusinessServiceDto fromEntity(BusinessService service) {
        List<String> pictures = service.getPictures().stream().map(p -> "http://localhost:8080/api/images/" + p.getUrl()).toList();
        List<String> videos = service.getVideos().stream().map(Video::getUrl).toList();
        List<ReviewDto> reviews = service.getReviews().stream().map(ReviewDto::fromEntity).toList();
        String serviceCategory = service.getCategory() != null ? service.getCategory().getName() : null;
        String location = service.getLocation().getLocationName();
        PublicUserDataDto user = new PublicUserDataDto(service.getUser());
        return new BusinessServiceDto(service.getId(), service.getTitle(), service.getDescription(), service.getPrice(),
                serviceCategory, location, user, pictures, videos, reviews);
    }
}
