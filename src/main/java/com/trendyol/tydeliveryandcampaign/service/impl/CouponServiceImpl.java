package com.trendyol.tydeliveryandcampaign.service.impl;

import com.trendyol.tydeliveryandcampaign.domain.coupon.DiscountCoupon;
import com.trendyol.tydeliveryandcampaign.dto.CouponCartDto;
import com.trendyol.tydeliveryandcampaign.dto.CouponDto;
import com.trendyol.tydeliveryandcampaign.dto.DiscountDto;
import com.trendyol.tydeliveryandcampaign.enums.DiscountType;
import com.trendyol.tydeliveryandcampaign.model.Coupon;
import com.trendyol.tydeliveryandcampaign.repository.CouponRepository;
import com.trendyol.tydeliveryandcampaign.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    private List<DiscountCoupon> discountCouponList;

    private CouponRepository couponRepository;

    @Autowired
    public CouponServiceImpl(List<DiscountCoupon> discountCouponList, CouponRepository couponRepository) {
        this.discountCouponList = discountCouponList;
        this.couponRepository = couponRepository;
    }

    @Override
    public String addCoupon(CouponDto couponDto) {
        Coupon coupon = new Coupon();
        coupon.setTitle(couponDto.getTitle());
        coupon.setDescription(couponDto.getDescription());
        coupon.setCouponType(couponDto.getCouponType());
        coupon.setMinPurchaseAmount(couponDto.getMinPurchaseAmount());
        coupon.setStartDate(couponDto.getStartDate());
        coupon.setEndDate(couponDto.getEndDate());

        if(couponDto.getCouponType() == DiscountType.RATE)
            coupon.setRate(couponDto.getRate());
        else
            coupon.setDiscountAmount(couponDto.getDiscountAmount());

        couponRepository.save(coupon);

        return "Coupon is added";
    }

    @Override
    public CouponDto getCoupon(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(EntityNotFoundException::new);
        CouponDto couponDto = new CouponDto();
        couponDto.setTitle(coupon.getTitle());
        couponDto.setDescription(coupon.getDescription());
        couponDto.setCouponType(coupon.getCouponType());
        couponDto.setDiscountAmount(coupon.getDiscountAmount());
        couponDto.setMinPurchaseAmount(coupon.getMinPurchaseAmount());
        couponDto.setStartDate(coupon.getStartDate());
        couponDto.setEndDate(coupon.getEndDate());
        couponDto.setRate(coupon.getRate());

        return couponDto;
    }

    @Override
    public DiscountDto getDiscount(CouponCartDto couponCartDto) {

        DiscountCoupon usableCoupon = discountCouponList.stream().filter(x -> x.isUsable(couponCartDto)).findFirst().orElse(null);

        return usableCoupon.applyCoupon(couponCartDto);
    }
}
