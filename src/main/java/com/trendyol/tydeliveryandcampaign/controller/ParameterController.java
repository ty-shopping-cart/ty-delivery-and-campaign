package com.trendyol.tydeliveryandcampaign.controller;

import com.trendyol.tydeliveryandcampaign.dto.ParameterDto;
import com.trendyol.tydeliveryandcampaign.service.DeliveryService;
import com.trendyol.tydeliveryandcampaign.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parameter")
public class ParameterController {

    private ParameterService parameterService;

    @Autowired
    public ParameterController(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @GetMapping("/")
    public ResponseEntity<ParameterDto> getParameter (String key) {
        return ResponseEntity.ok(parameterService.getParameter(key));
    }

    @PostMapping("/")
    public ResponseEntity<String> addParameter (ParameterDto parameterDto) {
        return ResponseEntity.ok(parameterService.addParameter(parameterDto));
    }

    @PutMapping("/")
    public ResponseEntity<ParameterDto> updateParameter (ParameterDto parameterDto) {
        return ResponseEntity.ok(parameterService.updateParameter(parameterDto));
    }
}
