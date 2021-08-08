package com.trendyol.tydeliveryandcampaign.repository;

import com.trendyol.tydeliveryandcampaign.enums.DiscountType;
import com.trendyol.tydeliveryandcampaign.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
    Coupon findByIdAndCouponType(Long couponId, DiscountType couponType);
}
