package com.talentfinder.service.impl;

import com.talentfinder.dto.CategoryDto;
import com.talentfinder.dto.LocationDto;
import com.talentfinder.model.Location;
import com.talentfinder.model.ServiceCategory;
import com.talentfinder.repository.CategoryRepository;
import com.talentfinder.repository.LocationRepository;
import com.talentfinder.service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MetadataServiceImpl implements MetadataService {
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public MetadataServiceImpl(CategoryRepository categoryRepository, LocationRepository locationRepository) {
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<ServiceCategory> serviceCategories = categoryRepository.findAll();
        return serviceCategories.stream().map(CategoryDto::fromEntity).toList();
    }

    @Override
    public List<LocationDto> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream().map(LocationDto::fromEntity).toList();
    }
}
