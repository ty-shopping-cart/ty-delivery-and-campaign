package com.trendyol.tydeliveryandcampaign.service;

import com.trendyol.tydeliveryandcampaign.dto.CouponDto;
import com.trendyol.tydeliveryandcampaign.enums.DiscountType;
import com.trendyol.tydeliveryandcampaign.model.Coupon;
import com.trendyol.tydeliveryandcampaign.repository.CouponRepository;
import com.trendyol.tydeliveryandcampaign.service.impl.CouponServiceImpl;
import com.trendyol.tydeliveryandcampaign.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CouponServiceTest {

    @Mock
    CouponRepository couponRepository;

    @InjectMocks
    CouponServiceImpl couponService;

    @Test
    void addCoupon_shouldSaveRateCoupon(){
        Coupon coupon = new Coupon();
        coupon.setTitle("Coupon1");
        coupon.setDescription("Coupon1 Desc");
        coupon.setCouponType(DiscountType.RATE);
        coupon.setMinPurchaseAmount(100.0);
        coupon.setStartDate(DateUtil.dateToInteger(LocalDate.now().minusDays(1L)));
        coupon.setEndDate(DateUtil.dateToInteger(LocalDate.now().plusDays(1L)));
        coupon.setRate(10);
        when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);

        CouponDto couponDto = new CouponDto();
        couponDto.setTitle("Coupon1");
        couponDto.setDescription("Coupon1 Desc");
        couponDto.setCouponType(DiscountType.AMOUNT);
        couponDto.setMinPurchaseAmount(100.0);
        couponDto.setStartDate(DateUtil.dateToInteger(LocalDate.now().minusDays(1L)));
        couponDto.setEndDate(DateUtil.dateToInteger(LocalDate.now().plusDays(1L)));
        couponDto.setRate(10);
        couponService.addCoupon(couponDto);

        Mockito.verify(couponRepository, Mockito.times(1)).save(any(Coupon.class));
    }

    @Test
    void addCampaign_shouldSaveAmountCampaign(){
        Coupon coupon = new Coupon();
        coupon.setTitle("Coupon1");
        coupon.setDescription("Coupon1 Desc");
        coupon.setCouponType(DiscountType.AMOUNT);
        coupon.setMinPurchaseAmount(100.0);
        coupon.setStartDate(DateUtil.dateToInteger(LocalDate.now().minusDays(1L)));
        coupon.setEndDate(DateUtil.dateToInteger(LocalDate.now().plusDays(1L)));
        coupon.setDiscountAmount(20.0);
        when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);

        CouponDto couponDto = new CouponDto();
        couponDto.setTitle("Coupon1");
        couponDto.setDescription("Coupon1 Desc");
        couponDto.setCouponType(DiscountType.AMOUNT);
        couponDto.setMinPurchaseAmount(100.0);
        couponDto.setStartDate(DateUtil.dateToInteger(LocalDate.now().minusDays(1L)));
        couponDto.setEndDate(DateUtil.dateToInteger(LocalDate.now().plusDays(1L)));
        couponDto.setDiscountAmount(20.0);
        couponService.addCoupon(couponDto);

        Mockito.verify(couponRepository, Mockito.times(1)).save(any(Coupon.class));
    }

    @Test
    void getCoupon_shouldReturnCampaign_whenCouponIdFound(){
        Coupon coupon = new Coupon();
        coupon.setId(1L);
        coupon.setTitle("Coupon1");
        coupon.setDescription("Coupon1 Desc");
        coupon.setCouponType(DiscountType.AMOUNT);
        coupon.setMinPurchaseAmount(100.0);
        coupon.setStartDate(DateUtil.dateToInteger(LocalDate.now().minusDays(1L)));
        coupon.setEndDate(DateUtil.dateToInteger(LocalDate.now().plusDays(1L)));
        coupon.setDiscountAmount(20.0);
        when(couponRepository.findById(any())).thenReturn(Optional.of(coupon));

        CouponDto couponDto = couponService.getCoupon(1L);

        assertThat(couponDto).isNotNull();
        assertThat(couponDto.getTitle()).isEqualTo(couponDto.getTitle());
        assertThat(couponDto.getDescription()).isEqualTo(couponDto.getDescription());
        assertThat(couponDto.getCouponType()).isEqualTo(couponDto.getCouponType());
        assertThat(couponDto.getMinPurchaseAmount()).isEqualTo(couponDto.getMinPurchaseAmount());
        assertThat(couponDto.getStartDate()).isEqualTo(couponDto.getStartDate());
        assertThat(couponDto.getEndDate()).isEqualTo(couponDto.getEndDate());
        assertThat(couponDto.getDiscountAmount()).isEqualTo(couponDto.getDiscountAmount());
        assertThat(couponDto.getRate()).isNull();
        Mockito.verify(couponRepository, Mockito.times(1)).findById(any());
    }

    @Test
    void getCampaign_throwException_whenCampaignIdNotFound(){
        when(couponRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> couponService.getCoupon(4L));
    }

    @Test
    void getDiscount_returnsDiscount_whenSuccessful(){
        //TODO
    }
}
