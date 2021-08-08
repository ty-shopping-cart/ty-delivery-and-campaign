package com.trendyol.tydeliveryandcampaign.domain.delivery;

import com.trendyol.tydeliveryandcampaign.constant.ShippingConstant;
import com.trendyol.tydeliveryandcampaign.dto.DeliveryCampaignDto;
import com.trendyol.tydeliveryandcampaign.dto.DiscountDto;
import com.trendyol.tydeliveryandcampaign.dto.ParameterDto;
import com.trendyol.tydeliveryandcampaign.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryCampaignImpl implements DeliveryCampaign {
    private ParameterService parameterService;

    @Autowired
    public DeliveryCampaignImpl(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @Override
    public boolean isUsable(Double totalAmount) {
        return totalAmount >= ShippingConstant.MIN_CART_PRICE_FOR_SHIPPING_FEE;
    }

    @Override
    public DiscountDto getShippingFee(Double totalAmount, Double shippingFee) {

        DiscountDto discountDto = new DiscountDto();
        discountDto.setDiscount(shippingFee);
        discountDto.setDiscountName(ShippingConstant.FREE_DELIVERY_CAMPAIGN);
        discountDto.setDiscountId(0L);

        return discountDto;
    }
}
