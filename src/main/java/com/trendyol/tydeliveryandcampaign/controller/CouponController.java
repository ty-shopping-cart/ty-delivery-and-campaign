package com.trendyol.tydeliveryandcampaign.controller;

import com.trendyol.tydeliveryandcampaign.dto.CouponCartDto;
import com.trendyol.tydeliveryandcampaign.dto.CouponDto;
import com.trendyol.tydeliveryandcampaign.dto.DiscountDto;
import com.trendyol.tydeliveryandcampaign.service.CouponService;
import com.trendyol.tydeliveryandcampaign.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    private CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("/")
    public ResponseEntity<String> addCoupon (@RequestBody CouponDto couponDto) {
        return ResponseEntity.ok(couponService.addCoupon(couponDto));
    }

    @GetMapping("/")
    public ResponseEntity<CouponDto> getCoupon (Long couponId) {
        return ResponseEntity.ok(couponService.getCoupon(couponId));
    }

    @PostMapping("/getCouponDiscount")
    public ResponseEntity<DiscountDto> getDiscount (@RequestBody CouponCartDto couponCartDto) {
        return ResponseEntity.ok(couponService.getDiscount(couponCartDto));
    }


}
