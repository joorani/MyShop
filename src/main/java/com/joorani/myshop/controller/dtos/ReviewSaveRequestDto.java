package com.joorani.myshop.controller.dtos;

import com.joorani.myshop.entity.OrderProductInfo;
import com.joorani.myshop.entity.Product;
import com.joorani.myshop.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ReviewSaveRequestDto {

    @NotBlank(message = "공백은 입력할 수 없습니다.")
    private String content;

    public ReviewSaveRequestDto(String content) {
        this.content = content;
    }

    public Review toEntity(Product product) {
        return Review.builder()
                .content(content)
                .productInfo(new OrderProductInfo(product.getId(), product.getName()))
                .build();
    }

}
