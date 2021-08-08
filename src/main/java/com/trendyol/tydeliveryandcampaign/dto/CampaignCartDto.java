package com.trendyol.tydeliveryandcampaign.dto;

import lombok.Data;

import java.util.List;

@Data
public class CampaignCartDto {
    private List<CampaignProductDto> campaignCartList;
    private Double totalAmount;
    private Integer userId;
}
