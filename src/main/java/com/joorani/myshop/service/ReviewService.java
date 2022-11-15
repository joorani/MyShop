package com.joorani.myshop.service;

import com.joorani.myshop.controller.dtos.ReviewResponseDto;
import com.joorani.myshop.controller.dtos.ReviewSaveRequestDto;

import java.util.List;

public interface ReviewService {

    void registerReview(Long orderId, Long productId, ReviewSaveRequestDto reviewSaveRequestDto);

    ReviewResponseDto findReviewById(Long reviewId);

    void remove(Long reviewId);

    void update(Long reviewId, ReviewSaveRequestDto reviewSaveRequestDto);

    List<ReviewResponseDto> findProductReviews(Long productId);
}
