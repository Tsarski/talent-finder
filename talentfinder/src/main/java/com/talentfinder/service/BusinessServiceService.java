package com.talentfinder.service;

import com.talentfinder.dto.BusinessServiceDto;
import com.talentfinder.dto.BusinessServicePreviewDto;

import java.util.List;

public interface BusinessServiceService {
    public List<BusinessServicePreviewDto> getAllBusinessServices();
}
