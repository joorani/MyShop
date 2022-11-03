package com.joorani.myshop.controller.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductResponseDto {

    private String productName;
    private int price;
    private List<OptionDto> options;
    private List<ReviewResponseDto> reviews;

    @Builder
    public ProductResponseDto(String productName,
                              int price,
                              List<OptionDto> options,
                              List<ReviewResponseDto> reviews
    ) {
        this.productName = productName;
        this.price = price;
        this.options = options;
        this.reviews = reviews;
    }

}
