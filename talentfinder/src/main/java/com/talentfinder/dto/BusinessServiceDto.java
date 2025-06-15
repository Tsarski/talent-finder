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

    private PublicUserDataDto user;

    private List<String> pictures;

    private List<String> videos;

    public static BusinessServiceDto fromEntity(BusinessService service) {
        List<String> pictures = service.getPictures().stream().map(Picture::getUrl).toList();
        List<String> videos = service.getVideos().stream().map(Video::getUrl).toList();
        String serviceCategory = service.getCategory() != null ? service.getCategory().getName() : null;
        PublicUserDataDto user = new PublicUserDataDto(service.getUser());
        return new BusinessServiceDto(service.getId(), service.getTitle(), service.getDescription(), service.getPrice(),
                serviceCategory, user, pictures, videos);
    }
}
