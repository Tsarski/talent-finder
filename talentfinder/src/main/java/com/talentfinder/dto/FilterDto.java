package com.talentfinder.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterDto {
    private String categoryName;
    private String location;
    private String username;
    private String serviceName;
    private Integer maxPrice;
    private Integer minPrice;
}
