package com.joorani.myshop.controller.dtos;


import com.joorani.myshop.entity.Product;
import com.joorani.myshop.entity.ProductCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ProductRegisterForm {

    //상품 이름
    private String name;

    // 가격
    private int price;

    // 이미지
    private String imagePath;

    //카테고리
    private Long firstCategory;
    private Long secondCategory;

    //옵션 추가
    private List<OptionDto> options;

    @Builder
    public ProductRegisterForm(String name, int price, String imagePath, Long firstCategory, Long secondCategory, List<OptionDto> options) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.firstCategory = firstCategory;
        this.secondCategory = secondCategory;
        this.options = options;
    }

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .price(price)
                .imgPath(imagePath)
                .productCategory(new ProductCategory(firstCategory, secondCategory))
                .options(options.stream().map(OptionDto::toEntity).collect(Collectors.toList()))
                .build();

    }




}
