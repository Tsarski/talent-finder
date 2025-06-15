package com.talentfinder.dto;

import com.talentfinder.model.BusinessService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessServicePreviewDto {
    private Long id;

    private String title;

    private Double price;

    private String serviceCategory;

    private PublicUserDataDto user;

    private String mainPicture;

    public static BusinessServicePreviewDto fromEntity(BusinessService businessService) {
        BusinessServicePreviewDto dto = new BusinessServicePreviewDto();
        PublicUserDataDto user = new PublicUserDataDto(businessService.getUser());
        dto.setId(businessService.getId());
        dto.setTitle(businessService.getTitle());
        dto.setPrice(businessService.getPrice());
        dto.setUser(user);
        dto.setMainPicture("http://localhost:8080/api/images/" + businessService.getPictures().getFirst().getUrl());
        return dto;
    }
}
