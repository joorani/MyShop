package com.joorani.myshop.controller.dtos;

import com.joorani.myshop.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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

    public ProductResponseDto(Product entity) {
        this.productName = entity.getName();
        this.price = entity.getPrice();
        this.options = entity.getOptions().stream().map(OptionDto::of).collect(Collectors.toList());
        this.reviews = entity.getReviews().stream().map(ReviewResponseDto::new).collect(Collectors.toList());
    }
}
