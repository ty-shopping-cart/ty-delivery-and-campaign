package com.trendyol.tydeliveryandcampaign.domain.coupon;

import com.trendyol.tydeliveryandcampaign.dto.CouponCartDto;
import com.trendyol.tydeliveryandcampaign.dto.DiscountDto;
import com.trendyol.tydeliveryandcampaign.enums.DiscountType;
import com.trendyol.tydeliveryandcampaign.model.Coupon;
import com.trendyol.tydeliveryandcampaign.repository.CouponRepository;
import com.trendyol.tydeliveryandcampaign.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Component
public class RateCoupon implements DiscountCoupon {

    private CouponRepository couponRepository;

    @Autowired
    public RateCoupon(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public boolean isUsable(CouponCartDto couponCartDto) {
        Coupon coupon = couponRepository.findByIdAndCouponType(couponCartDto.getCouponId(), DiscountType.RATE);

        if(Objects.isNull(coupon))
            return false;

        if(!DateUtil.checkDateRange(coupon.getStartDate(),coupon.getEndDate()))
            return false;

        if(couponCartDto.getTotalAmount() < coupon.getMinPurchaseAmount())
            return false;

        return true;
    }

    @Override
    public DiscountDto applyCoupon(CouponCartDto couponCartDto) {
        Coupon coupon = couponRepository.findById(couponCartDto.getCouponId()).orElseThrow(EntityNotFoundException::new);

        DiscountDto discountDto = new DiscountDto();
        discountDto.setDiscount(couponCartDto.getTotalAmount() * (coupon.getRate() * 0.01));
        discountDto.setDiscountId(coupon.getId());
        discountDto.setDiscountName(coupon.getTitle());

        return discountDto;
    }
}
