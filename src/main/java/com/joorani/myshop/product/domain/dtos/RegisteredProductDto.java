package com.joorani.myshop.product.domain.dtos;

import com.joorani.myshop.product.domain.entity.ProductStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RegisteredProductDto {

    private String productName;
    private int price;
    private int stockQuantity;
    private ProductStatus productStatus;

    @Builder
    public RegisteredProductDto(String productName, int price, int stockQuantity, ProductStatus productStatus) {
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productStatus = productStatus;
    }

}
