package com.joorani.myshop.controller.dtos;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private String imgPath;
    private String storeName;
    private String productName;
    private int price;

    @QueryProjection
    public ProductResponseDto(String imgPath, String storeName, String productName, int price) {
        this.imgPath = imgPath;
        this.storeName = storeName;
        this.productName = productName;
        this.price = price;
    }
}
