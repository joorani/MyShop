package com.joorani.myshop.service;

import com.joorani.myshop.controller.dtos.ReviewSaveRequestDto;
import com.joorani.myshop.entity.Review;

public interface ReviewService {

    void registerReview(Long orderId, Long productId, ReviewSaveRequestDto reviewSaveRequestDto);

    Review findReviewById(Long reviewId);

    void remove(Review review);
}
