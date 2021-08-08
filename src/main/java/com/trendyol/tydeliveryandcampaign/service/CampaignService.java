package com.trendyol.tydeliveryandcampaign.service;

import com.trendyol.tydeliveryandcampaign.dto.CampaignCartDto;
import com.trendyol.tydeliveryandcampaign.dto.DiscountDto;
import com.trendyol.tydeliveryandcampaign.dto.CampaignDto;

import java.util.List;

public interface CampaignService {

    String addCampaign(CampaignDto campaignDto);

    CampaignDto getCampaign(Long campaignId);

    List<DiscountDto> getDiscount(CampaignCartDto campaignCartDto);

    DiscountDto getDeliveryDiscount(Double totalAmount);
}
