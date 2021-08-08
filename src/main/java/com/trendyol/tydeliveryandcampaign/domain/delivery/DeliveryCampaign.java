package com.trendyol.tydeliveryandcampaign.domain.delivery;

import com.trendyol.tydeliveryandcampaign.dto.DiscountDto;

public interface DeliveryCampaign {
    boolean isUsable(Double totalAmount);

    DiscountDto getShippingFee(Double totalAmount, Double shippingFee);
}
