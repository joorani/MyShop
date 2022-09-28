package com.joorani.myshop.product.domain.entity;

import lombok.Getter;

@Getter
public enum ProductStatus {
    ON_SALE("판매중"),
    OUT_OF_STOCK("품절");

    private String desc;

    ProductStatus(String desc) {
        this.desc = desc;
    }
}
