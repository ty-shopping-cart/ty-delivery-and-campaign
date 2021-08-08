package com.trendyol.tydeliveryandcampaign.domain.campaign;

import com.trendyol.tydeliveryandcampaign.dto.CampaignCartDto;
import com.trendyol.tydeliveryandcampaign.dto.DiscountDto;
import com.trendyol.tydeliveryandcampaign.enums.DiscountType;
import com.trendyol.tydeliveryandcampaign.model.Campaign;
import com.trendyol.tydeliveryandcampaign.repository.CampaignRepository;
import com.trendyol.tydeliveryandcampaign.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;


@Component
public class CategoryCampaign implements DiscountCampaign {
    private CampaignRepository campaignRepository;

    @Autowired
    public CategoryCampaign(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public List<Campaign> getUsableCampaigns(CampaignCartDto campaignCartDto) {

        HashSet<Integer> categorySet = new HashSet<>();
        campaignCartDto.getCampaignCartList().stream().forEach(x -> {
            categorySet.add(x.getCategoryId());
        });

        List<Campaign> campaignList = new ArrayList<>();

        for(Integer categoryId : categorySet) {
            Campaign campaign = campaignRepository.findByCategoryId(categoryId);
            if(Objects.isNull(campaign) || !DateUtil.checkDateRange(campaign.getStartDate(),campaign.getEndDate()))
                break;

            Double productCategoryTotal = campaignCartDto.getCampaignCartList().stream().
                    filter(x -> x.getCategoryId() == categoryId)
                    .mapToDouble(z -> z.getPrice()*z.getProductQuantity()).sum();

            if(productCategoryTotal > campaign.getMinPurchaseAmount())
                campaignList.add(campaign);
        }

        return campaignList;
    }

    @Override
    public List<DiscountDto> applyCampaigns(CampaignCartDto campaignCartDto, List<Campaign> campaignList) {

        List<DiscountDto> discountDtoList = new ArrayList<>();

        for (int i = 0; i < campaignList.size(); i++) {

            Campaign categoryCampaign = campaignList.get(i);

            Double campaignDiscount = 0.0;

            if (categoryCampaign.getCampaignType() == DiscountType.RATE) {
                campaignDiscount = campaignCartDto.getCampaignCartList().stream().
                        filter(x -> x.getCategoryId() == categoryCampaign.getCategoryId())
                        .mapToDouble(z -> z.getPrice() * z.getProductQuantity() * categoryCampaign.getRate() * 0.01).sum();
            } else
                campaignDiscount = categoryCampaign.getDiscountAmount();

            DiscountDto categoryDiscountDto = new DiscountDto();
            categoryDiscountDto.setDiscountId(categoryCampaign.getId());
            categoryDiscountDto.setDiscountName(categoryCampaign.getTitle());
            categoryDiscountDto.setDiscount(campaignDiscount);

            discountDtoList.add(categoryDiscountDto);
        }
        return discountDtoList;
    }
}
