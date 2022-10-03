package com.joorani.myshop.entity;

import lombok.Getter;

@Getter
public enum ProductStatus {
    ON_SALE("판매중"),
    OUT_OF_STOCK("품절");

    private final String desc;

    ProductStatus(String desc) {
        this.desc = desc;
    }
}
