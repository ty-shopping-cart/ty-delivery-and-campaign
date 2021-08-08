package com.trendyol.tydeliveryandcampaign.domain.campaign;

import com.trendyol.tydeliveryandcampaign.dto.CampaignCartDto;
import com.trendyol.tydeliveryandcampaign.dto.CampaignProductDto;
import com.trendyol.tydeliveryandcampaign.dto.DiscountDto;
import com.trendyol.tydeliveryandcampaign.enums.DiscountType;
import com.trendyol.tydeliveryandcampaign.model.Campaign;
import com.trendyol.tydeliveryandcampaign.repository.CampaignRepository;
import com.trendyol.tydeliveryandcampaign.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryCampaignTest {

    @Mock
    CampaignRepository campaignRepository;

    @InjectMocks
    CategoryCampaign categoryCampaign;

    @Test
    void getUsableCampaigns_returnsEmpty(){
        CampaignCartDto campaignCartDto = new CampaignCartDto();
        campaignCartDto.setCampaignCartList(Collections.emptyList());
        campaignCartDto.setTotalAmount(0.0);
        List<Campaign> campaignList = categoryCampaign.getUsableCampaigns(campaignCartDto);
        assertThat(campaignList).isNotNull().isEmpty();
    }

    @Test
    void getUsableCampaigns_returnsCampaignList(){
        Campaign campaign = new Campaign();
        campaign.setCampaignType(DiscountType.RATE);
        campaign.setCategoryId(3);
        campaign.setDescription("Category3");
        campaign.setRate(10);
        campaign.setEndDate(DateUtil.dateToInteger(LocalDate.now().plusDays(1L)));
        campaign.setStartDate(DateUtil.dateToInteger(LocalDate.now().minusDays(1L)));
        campaign.setTitle("Campaign3");
        campaign.setMinPurchaseAmount(0.0);

        when(campaignRepository.findByCategoryId(any())).thenReturn(campaign);

        CampaignProductDto productDto = new CampaignProductDto();
        productDto.setProductId(1);
        productDto.setCategoryId(3);
        productDto.setPrice(110.0);
        productDto.setProductQuantity(45);
        //productDto.setSupplierId(123L);

        List<CampaignProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(productDto);

        CampaignCartDto campaignCartDto = new CampaignCartDto();
        campaignCartDto.setCampaignCartList(productDtoList);
        campaignCartDto.setTotalAmount(0.0);

        List<Campaign> campaignList = categoryCampaign.getUsableCampaigns(campaignCartDto);

        assertThat(campaignList).isNotNull().hasSize(1);
    }

    @Test
    void applyCampaigns_returnsDiscountDtoList(){
        CampaignProductDto productDto = new CampaignProductDto();
        productDto.setProductId(1);
        productDto.setCategoryId(3);
        productDto.setPrice(110.0);
        productDto.setProductQuantity(45);

        List<CampaignProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(productDto);

        CampaignCartDto campaignCartDto = new CampaignCartDto();
        campaignCartDto.setCampaignCartList(productDtoList);
        campaignCartDto.setTotalAmount(0.0);

        Campaign campaign = new Campaign();
        campaign.setCampaignType(DiscountType.RATE);
        campaign.setCategoryId(3);
        campaign.setDescription("Category3");
        campaign.setRate(10);
        campaign.setEndDate(DateUtil.dateToInteger(LocalDate.now().plusDays(1L)));
        campaign.setStartDate(DateUtil.dateToInteger(LocalDate.now().minusDays(1L)));
        campaign.setTitle("Campaign3");
        campaign.setMinPurchaseAmount(0.0);
        List<Campaign> campaignList = List.of(campaign);

        List<DiscountDto> discountDtoList = categoryCampaign.applyCampaigns(campaignCartDto, campaignList);

        assertThat(discountDtoList).isNotNull().hasSize(1);
    }

}
