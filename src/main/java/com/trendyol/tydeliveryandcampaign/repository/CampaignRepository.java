package com.trendyol.tydeliveryandcampaign.repository;

import com.trendyol.tydeliveryandcampaign.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign,Long> {
    Campaign findByCategoryId(Integer categoryId);
}
