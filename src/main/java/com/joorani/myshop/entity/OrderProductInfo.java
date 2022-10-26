package com.joorani.myshop.entity;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class OrderProductInfo {
    private Long productId;
    private String productName;

    public OrderProductInfo(Long productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    protected OrderProductInfo() {
    }
}
