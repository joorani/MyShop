package com.joorani.myshop.service;

import com.joorani.myshop.controller.dtos.ReviewSaveRequestDto;

public interface ReviewService {

    void registerReview(Long orderId, Long productId, ReviewSaveRequestDto reviewSaveRequestDto);

}
