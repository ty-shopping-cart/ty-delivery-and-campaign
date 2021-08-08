package com.trendyol.tydeliveryandcampaign.service;

import com.trendyol.tydeliveryandcampaign.constant.ShippingConstant;
import com.trendyol.tydeliveryandcampaign.domain.campaign.DiscountCampaign;
import com.trendyol.tydeliveryandcampaign.domain.delivery.DeliveryCampaign;
import com.trendyol.tydeliveryandcampaign.dto.CampaignCartDto;
import com.trendyol.tydeliveryandcampaign.dto.CampaignDto;
import com.trendyol.tydeliveryandcampaign.dto.DiscountDto;
import com.trendyol.tydeliveryandcampaign.enums.DiscountType;
import com.trendyol.tydeliveryandcampaign.model.Campaign;
import com.trendyol.tydeliveryandcampaign.repository.CampaignRepository;
import com.trendyol.tydeliveryandcampaign.service.impl.CampaignServiceImpl;
import com.trendyol.tydeliveryandcampaign.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CampaignServiceTest {

    @Mock
    CampaignRepository campaignRepository;

    @Mock
    DeliveryCampaign deliveryCampaign;

    @Mock
    DiscountCampaign discountCampaign;

    @Mock
    DeliveryService deliveryService;

    @InjectMocks
    CampaignServiceImpl campaignService;

    @Test
    void addCampaign_shouldSaveRateCampaign(){
        Campaign campaign  = new Campaign();
        campaign.setCampaignType(DiscountType.RATE);
        campaign.setCategoryId(1);
        campaign.setDescription("Category1");
        campaign.setRate(10);
        campaign.setEndDate(DateUtil.dateToInteger(LocalDate.now().plusDays(1L)));
        campaign.setStartDate(DateUtil.dateToInteger(LocalDate.now().minusDays(1L)));
        campaign.setTitle("Campaign1");
        campaign.setMinPurchaseAmount(50.0);
        when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

        CampaignDto campaignDto = new CampaignDto();
        campaignDto.setCampaignType(DiscountType.RATE);
        campaignDto.setCategoryId(1);
        campaignDto.setDescription("Category1");
        campaignDto.setRate(10);
        campaignDto.setEndDate(DateUtil.dateToInteger(LocalDate.now().plusDays(1L)));
        campaignDto.setStartDate(DateUtil.dateToInteger(LocalDate.now().minusDays(1L)));
        campaignDto.setTitle("Campaign1");
        campaignDto.setMinPurchaseAmount(50.0);
        campaignService.addCampaign(campaignDto);

        Mockito.verify(campaignRepository, Mockito.times(1)).save(any(Campaign.class));
        //TODO değişkenleri ayrı mı tanımlıyorduk?
    }

    @Test
    void addCampaign_shouldSaveAmountCampaign(){
        Campaign campaign  = new Campaign();
        campaign.setCampaignType(DiscountType.AMOUNT);
        campaign.setCategoryId(2);
        campaign.setDescription("Category2");
        campaign.setDiscountAmount(30.0);
        campaign.setEndDate(DateUtil.dateToInteger(LocalDate.now().plusDays(1L)));
        campaign.setStartDate(DateUtil.dateToInteger(LocalDate.now().minusDays(1L)));
        campaign.setTitle("Campaign2");
        campaign.setMinPurchaseAmount(100.0);
        when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

        CampaignDto campaignDto = new CampaignDto();
        campaignDto.setCampaignType(DiscountType.AMOUNT);
        campaignDto.setCategoryId(2);
        campaignDto.setDescription("Category2");
        campaignDto.setDiscountAmount(30.0);
        campaignDto.setEndDate(DateUtil.dateToInteger(LocalDate.now().plusDays(1L)));
        campaignDto.setStartDate(DateUtil.dateToInteger(LocalDate.now().minusDays(1L)));
        campaignDto.setTitle("Campaign2");
        campaignDto.setMinPurchaseAmount(100.0);
        campaignService.addCampaign(campaignDto);

        Mockito.verify(campaignRepository, Mockito.times(1)).save(any(Campaign.class));
        //TODO değişkenleri ayrı mı tanımlıyorduk?
    }

    @Test
    void getCampaign_shouldReturnCampaign_whenCampaignIdFound(){
        Campaign campaign = new Campaign();
        campaign.setCampaignType(DiscountType.RATE);
        campaign.setCategoryId(3);
        campaign.setDescription("Category3");
        campaign.setRate(10);
        campaign.setEndDate(DateUtil.dateToInteger(LocalDate.now().plusDays(1L)));
        campaign.setStartDate(DateUtil.dateToInteger(LocalDate.now().minusDays(1L)));
        campaign.setTitle("Campaign3");
        campaign.setMinPurchaseAmount(100.0);
        when(campaignRepository.findById(any())).thenReturn(Optional.of(campaign));

        CampaignDto campaignDto = campaignService.getCampaign(3L);

        assertThat(campaignDto).isNotNull();
        assertThat(campaign.getCampaignType()).isEqualTo(campaignDto.getCampaignType());
        assertThat(campaign.getCategoryId()).isEqualTo(campaignDto.getCategoryId());
        assertThat(campaign.getDescription()).isEqualTo(campaignDto.getDescription());
        assertThat(campaign.getRate()).isEqualTo(campaignDto.getRate());
        assertThat(campaign.getEndDate()).isEqualTo(campaignDto.getEndDate());
        assertThat(campaign.getStartDate()).isEqualTo(campaignDto.getStartDate());
        assertThat(campaign.getTitle()).isEqualTo(campaignDto.getTitle());
        assertThat(campaign.getMinPurchaseAmount()).isEqualTo(campaignDto.getMinPurchaseAmount());
        assertThat(campaign.getDiscountAmount()).isNull();
        Mockito.verify(campaignRepository, Mockito.times(1)).findById(any());
        //TODO değişkenleri ayrı mı tanımlıyorduk?
    }

    @Test
    void getCampaign_throwException_whenCampaignIdNotFound(){
        when(campaignRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> campaignService.getCampaign(4L));
    }

    @Test
    void getDiscount_returnsList_whenSuccessful(){
        List<Campaign> usableCampaignList = new ArrayList<>();
        when(discountCampaign.getUsableCampaigns(any())).thenReturn(usableCampaignList);

        List<DiscountDto> discountDtoList = new ArrayList<>();
        when(discountCampaign.applyCampaigns(any(), any())).thenReturn(discountDtoList);

        CampaignCartDto campaignCartDto = new CampaignCartDto();
        campaignCartDto.setCampaignCartList(Collections.emptyList());
        campaignCartDto.setTotalAmount(0.0);

        List<DiscountDto> list = campaignService.getDiscount(campaignCartDto);
        assertThat(list).isNotNull();
        //TODO bu ne oldu la böyle
    }

    @ParameterizedTest
    @ValueSource(doubles = {10.0})
    void getDeliveryDiscount_returnsList_whenCampaignIsUnusable(Double totalAmount){
        when(deliveryCampaign.isUsable(totalAmount)).thenReturn(false);
        DiscountDto discountDto = campaignService.getDeliveryDiscount(totalAmount);
        assertThat(discountDto).isEqualTo(null);
    }

    @ParameterizedTest()
    @ValueSource(doubles = {100.0})
    void getDeliveryDiscount_returnsList_whenCampaignIsUsable(Double totalAmount){

        when(deliveryCampaign.isUsable(totalAmount)).thenReturn(true);

        DiscountDto discountDto = new DiscountDto();
        discountDto.setDiscountName(ShippingConstant.FREE_DELIVERY_CAMPAIGN);
        discountDto.setDiscount(ShippingConstant.SHIPPINGFEE);

        when(deliveryCampaign.getShippingFee(totalAmount, ShippingConstant.SHIPPINGFEE)).thenReturn(discountDto);

        DiscountDto deliveryDiscount = campaignService.getDeliveryDiscount(totalAmount);

        assertThat(deliveryDiscount).isEqualTo(discountDto);
    }

}
