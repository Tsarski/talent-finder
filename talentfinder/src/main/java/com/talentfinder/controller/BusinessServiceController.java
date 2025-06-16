package com.talentfinder.controller;

import com.talentfinder.dto.BusinessServiceDto;
import com.talentfinder.dto.BusinessServicePreviewDto;
import com.talentfinder.dto.FilterDto;
import com.talentfinder.service.BusinessServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class BusinessServiceController {

    private final BusinessServiceService businessServiceService;

    @Autowired
    public BusinessServiceController(BusinessServiceService businessServiceService) {
        this.businessServiceService = businessServiceService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BusinessServicePreviewDto>> getAllServices() {
        List<BusinessServicePreviewDto> result = businessServiceService.getAllBusinessServices();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BusinessServiceDto> getServiceById(@PathVariable String id) {
        BusinessServiceDto res = businessServiceService.getBusinessServiceById(Long.parseLong(id));
        if (res == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getByCriteria")
    public ResponseEntity<List<BusinessServicePreviewDto>> getServiceByCriteria(@RequestBody FilterDto filterCriteria) {
        List<BusinessServicePreviewDto> res = businessServiceService.getBusinessServiceByFilteringCriteria(filterCriteria);
        return ResponseEntity.ok(res);
    }

}
