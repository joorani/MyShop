package com.joorani.myshop.controller.dtos;

import com.joorani.myshop.entity.ProductOption;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OptionDto {

    private String name;
    private int price;
    private int quantity;

    @Builder
    public OptionDto(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductOption toEntity() {
        return ProductOption.builder()
                .name(name)
                .price(price)
                .stackQuantity(quantity)
                .build();
    }

    public static OptionDto of(ProductOption option) {
        return OptionDto.builder()
                .name(option.getName())
                .price(option.getPrice())
                .quantity(option.getStackQuantity())
                .build();
    }

}
