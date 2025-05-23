package com.talentfinder.controller;

import com.talentfinder.dto.BusinessServiceDto;
import com.talentfinder.service.BusinessServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller("/api/services")
public class BusinessServiceController {

    private final BusinessServiceService businessServiceService;

    @Autowired
    public BusinessServiceController(BusinessServiceService businessServiceService) {
        this.businessServiceService = businessServiceService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BusinessServiceDto>> getAllServices() {
        List<BusinessServiceDto> result = businessServiceService.getAllBusinessServices();
        return ResponseEntity.ok(result);
    }
}
