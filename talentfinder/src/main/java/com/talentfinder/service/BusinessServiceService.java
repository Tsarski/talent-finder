package com.talentfinder.service;

import com.talentfinder.dto.BusinessServiceDto;
import com.talentfinder.dto.BusinessServicePreviewDto;
import com.talentfinder.dto.CreateBusinessServiceDto;
import com.talentfinder.dto.FilterDto;
import com.talentfinder.model.BusinessService;

import java.util.List;

public interface BusinessServiceService {
    List<BusinessServicePreviewDto> getAllBusinessServices();
    BusinessServiceDto getBusinessServiceById(Long id);
    List<BusinessServicePreviewDto> getBusinessServiceByFilteringCriteria(FilterDto criteria);
    BusinessService createBusinessService(CreateBusinessServiceDto businessServiceDto);
}
