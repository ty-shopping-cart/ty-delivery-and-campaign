package com.trendyol.tydeliveryandcampaign.domain.campaign;

import com.trendyol.tydeliveryandcampaign.dto.CampaignCartDto;
import com.trendyol.tydeliveryandcampaign.dto.DiscountDto;
import com.trendyol.tydeliveryandcampaign.model.Campaign;

import java.util.List;

public interface DiscountCampaign {

    List<Campaign> getUsableCampaigns(CampaignCartDto campaignCartDto);

    List<DiscountDto> applyCampaigns(CampaignCartDto campaignCartDto, List<Campaign> campaignList);
}
