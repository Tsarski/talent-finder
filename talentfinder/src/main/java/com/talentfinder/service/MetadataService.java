package com.talentfinder.service;

import com.talentfinder.dto.CategoryDto;
import com.talentfinder.dto.LocationDto;

import java.util.List;

public interface MetadataService {
    List<CategoryDto> getAllCategories();
    List<LocationDto> getAllLocations();
}
