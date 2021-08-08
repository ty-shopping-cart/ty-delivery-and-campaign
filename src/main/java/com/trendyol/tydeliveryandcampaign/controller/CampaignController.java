package com.trendyol.tydeliveryandcampaign.controller;

import com.trendyol.tydeliveryandcampaign.dto.*;
import com.trendyol.tydeliveryandcampaign.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

    private CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping("/")
    public ResponseEntity<String> addCampaign (@RequestBody CampaignDto campaignDto) {
        return ResponseEntity.ok(campaignService.addCampaign(campaignDto));
    }

    @GetMapping("/")
    public ResponseEntity<CampaignDto> getCampaign (Long campaignId) {
        return ResponseEntity.ok(campaignService.getCampaign(campaignId));
    }

    @PostMapping("/getCampaignDiscount")
    public ResponseEntity<List<DiscountDto>> getDiscount (@RequestBody CampaignCartDto campaignCartDto) {
        return ResponseEntity.ok(campaignService.getDiscount(campaignCartDto));
    }

    @GetMapping("/getDeliveryCampaign")
    public ResponseEntity<DiscountDto> getDeliveryDiscount(Double totalAmount) {
        return ResponseEntity.ok(campaignService.getDeliveryDiscount(totalAmount));
    }

}
