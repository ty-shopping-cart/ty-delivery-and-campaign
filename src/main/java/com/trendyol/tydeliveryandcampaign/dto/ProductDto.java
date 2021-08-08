package com.trendyol.tydeliveryandcampaign.dto;

import lombok.Data;

@Data
public class ProductDto {
    /*private Long productId;
    private Long categoryId;
    private Integer productQuantity;
    private Double price;
    private Long supplierId;*/
    private String title;
    private Double price;
    private String imageURL;
    private String barcode;
    private Integer stock;
    private Integer categoryId;
}
