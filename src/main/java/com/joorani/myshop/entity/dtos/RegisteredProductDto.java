package com.joorani.myshop.entity.dtos;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RegisteredProductDto {

    private String productName;
    private int price;
    private List<OptionDto> options;

    @Builder
    public RegisteredProductDto(String productName, int price, List<OptionDto> options) {
        this.productName = productName;
        this.price = price;
        this.options = options;
    }
}
