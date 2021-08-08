package com.trendyol.tydeliveryandcampaign.domain.delivery;

import com.trendyol.tydeliveryandcampaign.dto.DiscountDto;
import com.trendyol.tydeliveryandcampaign.dto.ParameterDto;
import com.trendyol.tydeliveryandcampaign.service.ParameterService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeliveryCampaignTest {
    @Mock
    ParameterService parameterService;

    @InjectMocks
    DeliveryCampaignImpl deliveryCampaign;

    @ParameterizedTest
    @ValueSource(doubles = {100.0, 150.0, 200.0})//TODO Burası yanlış oldu gibi
    void isUsable_returnTrue(Double totalAmount){
        boolean result = deliveryCampaign.isUsable(totalAmount);
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(doubles = {10.0, 50.0, 90.0})//TODO Burası yanlış oldu gibi
    void isUsable_returnFalse(Double totalAmount){
        boolean result = deliveryCampaign.isUsable(totalAmount);
        assertThat(result).isFalse();
    }
    @ParameterizedTest
    @ValueSource(doubles = {100.0, 150.0, 200.0})
    void getShippingFee(Double totalAmount){
        double shippingFee = 4.99;
        ParameterDto parameterDto = new ParameterDto("key", String.valueOf(shippingFee));
        when(parameterService.getParameter(any())).thenReturn(parameterDto);

        DiscountDto discountDto = deliveryCampaign.getShippingFee(totalAmount,shippingFee);
        assertThat(discountDto.getDiscount()).isEqualTo(shippingFee);
    }
}
