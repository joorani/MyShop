package com.joorani.myshop.controller.dtos;

import com.joorani.myshop.entity.OrderProductInfo;
import com.joorani.myshop.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewResponseDto {

    private Long reviewId;
    private String author;
    private String content;
    private OrderProductInfo orderProductInfo;
    private LocalDateTime writtenTime;

    public ReviewResponseDto(Review entity) {
        this.reviewId = entity.getId();
        this.author = entity.getAuthor();
        this.content = entity.getContent();
        this.orderProductInfo = entity.getProductInfo();
        this.writtenTime = entity.getCreatedAt();
    }
}
