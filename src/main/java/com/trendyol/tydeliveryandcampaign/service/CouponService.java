package com.trendyol.tydeliveryandcampaign.service;

import com.trendyol.tydeliveryandcampaign.dto.CouponCartDto;
import com.trendyol.tydeliveryandcampaign.dto.CouponDto;
import com.trendyol.tydeliveryandcampaign.dto.DiscountDto;

import java.util.List;

public interface CouponService {
    String addCoupon(CouponDto couponDto);

    CouponDto getCoupon(Long couponId);

    DiscountDto getDiscount(CouponCartDto couponCartDto);
}
