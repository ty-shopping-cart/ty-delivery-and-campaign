package com.trendyol.tydeliveryandcampaign.domain.coupon;

import com.trendyol.tydeliveryandcampaign.dto.CouponCartDto;
import com.trendyol.tydeliveryandcampaign.dto.DiscountDto;


public interface DiscountCoupon {

    boolean isUsable(CouponCartDto couponCartDto);

    DiscountDto applyCoupon(CouponCartDto CouponCartDto);
}
