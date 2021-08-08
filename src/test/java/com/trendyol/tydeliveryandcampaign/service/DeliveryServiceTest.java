package com.trendyol.tydeliveryandcampaign.service;

import com.trendyol.tydeliveryandcampaign.constant.ShippingConstant;
import com.trendyol.tydeliveryandcampaign.dto.CouponDto;
import com.trendyol.tydeliveryandcampaign.enums.DiscountType;
import com.trendyol.tydeliveryandcampaign.model.Coupon;
import com.trendyol.tydeliveryandcampaign.model.Parameter;
import com.trendyol.tydeliveryandcampaign.repository.CouponRepository;
import com.trendyol.tydeliveryandcampaign.repository.ParameterRepository;
import com.trendyol.tydeliveryandcampaign.service.impl.CouponServiceImpl;
import com.trendyol.tydeliveryandcampaign.service.impl.DeliveryServiceImpl;
import com.trendyol.tydeliveryandcampaign.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ParameterResolver;
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
class DeliveryServiceTest {

    @Mock
    ParameterRepository parameterRepository;

    @InjectMocks
    DeliveryServiceImpl deliveryService;

    @Test
    void getShippingFee_whenParameterIsSet(){
        double fixedShippingFee = 20.0;
        Parameter fixedShippingFeeParam = new Parameter();
        fixedShippingFeeParam.setKey(ShippingConstant.SHIPPING_FEE_TEXT);
        fixedShippingFeeParam.setValue(String.valueOf(fixedShippingFee));
        when(parameterRepository.findByKey(any())).thenReturn(fixedShippingFeeParam);
        assertThat(deliveryService.getShippingFee()).isEqualTo(fixedShippingFee);
    }

    @Test
    void getShippingFee_whenParameterIsNull(){
        when(parameterRepository.findByKey(any())).thenReturn(null);
        assertThat(deliveryService.getShippingFee()).isEqualTo(ShippingConstant.SHIPPINGFEE);
    }

}
