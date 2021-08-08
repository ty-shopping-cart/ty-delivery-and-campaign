package com.trendyol.tydeliveryandcampaign.model;

import com.trendyol.tydeliveryandcampaign.enums.DiscountType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table
@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer categoryId;
    private Double minPurchaseAmount;
    private Double discountAmount;
    private Integer startDate;
    private Integer endDate;
    private String title;
    private String description;
    private Integer rate;
    @Enumerated(EnumType.STRING)
    private DiscountType campaignType;
}
