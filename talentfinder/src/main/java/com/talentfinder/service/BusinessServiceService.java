package com.talentfinder.service;

import com.talentfinder.dto.BusinessServiceDto;
import com.talentfinder.dto.BusinessServicePreviewDto;
import com.talentfinder.dto.FilterDto;

import java.util.List;

public interface BusinessServiceService {
    List<BusinessServicePreviewDto> getAllBusinessServices();
    BusinessServiceDto getBusinessServiceById(Long id);
    List<BusinessServicePreviewDto> getBusinessServiceByFilteringCriteria(FilterDto criteria);
}
