package com.trendyol.tydeliveryandcampaign.service.impl;

import com.trendyol.tydeliveryandcampaign.domain.campaign.DiscountCampaign;
import com.trendyol.tydeliveryandcampaign.domain.delivery.DeliveryCampaign;
import com.trendyol.tydeliveryandcampaign.dto.CampaignCartDto;
import com.trendyol.tydeliveryandcampaign.dto.DiscountDto;
import com.trendyol.tydeliveryandcampaign.dto.CampaignDto;
import com.trendyol.tydeliveryandcampaign.enums.DiscountType;
import com.trendyol.tydeliveryandcampaign.model.Campaign;
import com.trendyol.tydeliveryandcampaign.repository.CampaignRepository;
import com.trendyol.tydeliveryandcampaign.service.CampaignService;
import com.trendyol.tydeliveryandcampaign.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    private CampaignRepository campaignRepository;
    private DeliveryCampaign deliveryCampaign;
    private DiscountCampaign discountCampaign;
    private DeliveryService deliveryService;

    @Autowired
    public CampaignServiceImpl(CampaignRepository campaignRepository,
                               DeliveryCampaign deliveryCampaign,
                               DiscountCampaign discountCampaign,
                               DeliveryService deliveryService) {
        this.campaignRepository = campaignRepository;
        this.deliveryCampaign = deliveryCampaign;
        this.discountCampaign = discountCampaign;
        this.deliveryService = deliveryService;
    }

    @Override
    public String addCampaign(CampaignDto campaignDto) {
        Campaign campaign = new Campaign();
        campaign.setCampaignType(campaignDto.getCampaignType());
        campaign.setCategoryId(campaignDto.getCategoryId());
        campaign.setDescription(campaignDto.getDescription());
        campaign.setDiscountAmount(campaignDto.getDiscountAmount());
        campaign.setEndDate(campaignDto.getEndDate());
        campaign.setStartDate(campaignDto.getStartDate());
        campaign.setTitle(campaignDto.getTitle());
        campaign.setMinPurchaseAmount(campaignDto.getMinPurchaseAmount());

        if(campaignDto.getCampaignType() == DiscountType.RATE)
            campaign.setRate(campaignDto.getRate());
        else
            campaign.setDiscountAmount(campaignDto.getDiscountAmount());

        campaignRepository.save(campaign);

        return "Campaign is added";

    }

    @Override
    public CampaignDto getCampaign(Long campaignId) {

        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(EntityNotFoundException::new);

        CampaignDto campaignDto = new CampaignDto();
        campaignDto.setTitle(campaign.getTitle());
        campaignDto.setRate(campaign.getRate());
        campaignDto.setStartDate(campaign.getStartDate());
        campaignDto.setDiscountAmount(campaign.getDiscountAmount());
        campaignDto.setEndDate(campaign.getEndDate());
        campaignDto.setMinPurchaseAmount(campaign.getMinPurchaseAmount());
        campaignDto.setCampaignType(campaign.getCampaignType());
        campaignDto.setCategoryId(campaign.getCategoryId());
        campaignDto.setDescription(campaign.getDescription());

        return campaignDto;
    }

    @Override
    public List<DiscountDto> getDiscount(CampaignCartDto campaignCartDto) {

        List<Campaign> usableCampaignList = discountCampaign.getUsableCampaigns(campaignCartDto);

        return discountCampaign.applyCampaigns(campaignCartDto,usableCampaignList);
    }

    @Override
    public DiscountDto getDeliveryDiscount(Double totalAmount) {

        if(deliveryCampaign.isUsable(totalAmount))
            return deliveryCampaign.getShippingFee(totalAmount,deliveryService.getShippingFee());

        return null;
    }
}
