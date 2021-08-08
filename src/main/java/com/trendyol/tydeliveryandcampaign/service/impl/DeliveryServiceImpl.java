package com.trendyol.tydeliveryandcampaign.service.impl;

import com.trendyol.tydeliveryandcampaign.constant.ShippingConstant;
import com.trendyol.tydeliveryandcampaign.domain.delivery.DeliveryCampaign;
import com.trendyol.tydeliveryandcampaign.dto.DeliveryCampaignDto;
import com.trendyol.tydeliveryandcampaign.dto.DiscountDto;
import com.trendyol.tydeliveryandcampaign.model.Parameter;
import com.trendyol.tydeliveryandcampaign.repository.ParameterRepository;
import com.trendyol.tydeliveryandcampaign.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private ParameterRepository parameterRepository;
    private DeliveryCampaign deliveryCampaign;


    @Autowired
    public DeliveryServiceImpl(ParameterRepository parameterRepository, DeliveryCampaign deliveryCampaign) {
        this.parameterRepository = parameterRepository;
        this.deliveryCampaign = deliveryCampaign;
    }

    @Override
    public Double getShippingFee() {
        Double fixedShippingFee = ShippingConstant.SHIPPINGFEE;

        Parameter shippingFee = parameterRepository.findByKey(ShippingConstant.SHIPPING_FEE_TEXT);

        if(!Objects.isNull(shippingFee)) {
            fixedShippingFee = Double.parseDouble(shippingFee.getValue());
        }
        return fixedShippingFee;
    }
}
