package com.joorani.myshop.service;

import com.joorani.myshop.entity.dtos.ReviewSaveRequestDto;

public interface ReviewService {

    void registerReview(Long orderId, Long productId, ReviewSaveRequestDto reviewSaveRequestDto);

}
