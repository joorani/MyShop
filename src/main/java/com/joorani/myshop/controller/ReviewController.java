package com.joorani.myshop.controller;

import com.joorani.myshop.entity.dtos.ReviewSaveRequestDto;
import com.joorani.myshop.service.ReviewService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "Review save", description = "리뷰 등록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping("/reviews")
    public void register(
            @RequestParam("orderNo") Long orderNo,
            @RequestParam("productId") Long productId,
            @RequestBody ReviewSaveRequestDto reviewSaveRequestDto
    ) {
        reviewService.registerReview(orderNo, productId, reviewSaveRequestDto);
    }

}
