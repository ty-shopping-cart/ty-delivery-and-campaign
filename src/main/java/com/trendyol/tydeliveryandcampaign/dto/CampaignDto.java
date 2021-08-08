package com.trendyol.tydeliveryandcampaign.dto;

import com.trendyol.tydeliveryandcampaign.enums.DiscountType;
import lombok.Data;

@Data
public class CampaignDto {

    private Integer categoryId;
    private Double minPurchaseAmount;
    private Double discountAmount;
    private Integer startDate;
    private Integer endDate;
    private String title;
    private String description;
    private DiscountType campaignType;
    private Integer rate;
}
