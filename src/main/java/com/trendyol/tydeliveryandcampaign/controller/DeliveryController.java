package com.trendyol.tydeliveryandcampaign.controller;

import com.trendyol.tydeliveryandcampaign.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/")
    public ResponseEntity<Double> getShippingFee () {
        return ResponseEntity.ok(deliveryService.getShippingFee());
    }
}
