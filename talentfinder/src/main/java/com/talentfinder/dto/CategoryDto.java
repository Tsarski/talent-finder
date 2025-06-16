package com.talentfinder.dto;

import com.talentfinder.model.ServiceCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDto {
    private int id;
    private String name;

    public static CategoryDto fromEntity(ServiceCategory category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}
