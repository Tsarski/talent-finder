package com.talentfinder.service.impl;

import com.talentfinder.dto.BusinessServiceDto;
import com.talentfinder.dto.BusinessServicePreviewDto;
import com.talentfinder.model.BusinessService;
import com.talentfinder.repository.ServiceRepository;
import com.talentfinder.service.BusinessServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
