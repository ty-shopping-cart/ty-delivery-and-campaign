package com.trendyol.tydeliveryandcampaign.domain.coupon;

import com.trendyol.tydeliveryandcampaign.dto.CouponCartDto;
import com.trendyol.tydeliveryandcampaign.dto.DiscountDto;
import com.trendyol.tydeliveryandcampaign.enums.DiscountType;
import com.trendyol.tydeliveryandcampaign.model.Coupon;
import com.trendyol.tydeliveryandcampaign.repository.CouponRepository;
import com.trendyol.tydeliveryandcampaign.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AmountCouponTest {

    @Mock
    CouponRepository couponRepository;

    @InjectMocks
    AmountCoupon amountCoupon;

    @Test
    void isUsable_returnsFalse_whenCouponIdNotValid(){
        when(couponRepository.findByIdAndCouponType(any(), eq(DiscountType.AMOUNT))).thenReturn(null);

        CouponCartDto couponCartDto = new CouponCartDto();
        couponCartDto.setCouponId(1L);
        couponCartDto.setTotalAmount(200.0);
        boolean result = amountCoupon.isUsable(couponCartDto);
        assertThat(result).isFalse();
    }

    @Test
    void isUsable_returnsFalse_whenCouponDateNotValid(){
        Coupon coupon = new Coupon();
        coupon.setId(1L);
        coupon.setTitle("Coupon1");
        coupon.setDescription("Coupon1 Desc");
        coupon.setCouponType(DiscountType.AMOUNT);
        coupon.setMinPurchaseAmount(100.0);
        coupon.setStartDate(DateUtil.dateToInteger(LocalDate.now().plusDays(1L)));
        coupon.setEndDate(DateUtil.dateToInteger(LocalDate.now().plusDays(2L)));
        coupon.setDiscountAmount(20.0);
        when(couponRepository.findByIdAndCouponType(any(), eq(DiscountType.AMOUNT))).thenReturn(coupon);

        CouponCartDto couponCartDto = new CouponCartDto();
        couponCartDto.setCouponId(1L);
        couponCartDto.setTotalAmount(200.0);
        boolean result = amountCoupon.isUsable(couponCartDto);
        assertThat(result).isFalse();
    }

    @Test
    void isUsable_returnsFalse_whenTotalAmountNotEnough(){
        Coupon coupon = new Coupon();
        coupon.setId(1L);
        coupon.setTitle("Coupon1");
        coupon.setDescription("Coupon1 Desc");
        coupon.setCouponType(DiscountType.AMOUNT);
        coupon.setMinPurchaseAmount(100.0);
        coupon.setStartDate(DateUtil.dateToInteger(LocalDate.now().minusDays(1L)));
        coupon.setEndDate(DateUtil.dateToInteger(LocalDate.now().plusDays(1L)));
        coupon.setDiscountAmount(20.0);
        when(couponRepository.findByIdAndCouponType(any(), eq(DiscountType.AMOUNT))).thenReturn(coupon);

        CouponCartDto couponCartDto = new CouponCartDto();
        couponCartDto.setCouponId(1L);
        couponCartDto.setTotalAmount(10.0);
        boolean result = amountCoupon.isUsable(couponCartDto);
        assertThat(result).isFalse();
    }

    @Test
    void isUsable_returnsTrue_whenEverythingIsOkay(){
        Coupon coupon = new Coupon();
        coupon.setId(1L);
        coupon.setTitle("Coupon1");
        coupon.setDescription("Coupon1 Desc");
        coupon.setCouponType(DiscountType.AMOUNT);
        coupon.setMinPurchaseAmount(100.0);
        coupon.setStartDate(DateUtil.dateToInteger(LocalDate.now().minusDays(1L)));
        coupon.setEndDate(DateUtil.dateToInteger(LocalDate.now().plusDays(1L)));
        coupon.setDiscountAmount(20.0);
        when(couponRepository.findByIdAndCouponType(any(), eq(DiscountType.AMOUNT))).thenReturn(coupon);

        CouponCartDto couponCartDto = new CouponCartDto();
        couponCartDto.setCouponId(1L);
        couponCartDto.setTotalAmount(200.0);
        boolean result = amountCoupon.isUsable(couponCartDto);
        assertThat(result).isTrue();
    }

    @Test
    void applyCoupon_throwsException_whenCouponNotFound(){
        when(couponRepository.findById(any())).thenReturn(Optional.empty());

        CouponCartDto couponCartDto = new CouponCartDto();
        couponCartDto.setCouponId(1L);
        couponCartDto.setTotalAmount(200.0);

        assertThrows(EntityNotFoundException.class, () -> amountCoupon.applyCoupon(couponCartDto));
    }

    @Test
    void applyCoupon_returnsDiscount_whenSuccessful(){
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

        CouponCartDto couponCartDto = new CouponCartDto();
        couponCartDto.setCouponId(1L);
        couponCartDto.setTotalAmount(200.0);

        DiscountDto discountDto = amountCoupon.applyCoupon(couponCartDto);
        assertThat(discountDto.getDiscount()).isEqualTo(coupon.getDiscountAmount());
        assertThat(discountDto.getDiscountId()).isEqualTo(coupon.getId());
        assertThat(discountDto.getDiscountName()).isEqualTo(coupon.getTitle());
    }

}
