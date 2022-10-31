package com.joorani.myshop.controller;

import com.joorani.myshop.controller.dtos.ReviewSaveRequestDto;
import com.joorani.myshop.service.ReviewService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
            @Parameter(description = "주문번호", required = true, example = "123")
            @RequestParam("orderNo") Long orderNo,
            @Parameter(description = "상품번호", required = true, example = "456")
            @RequestParam("productId") Long productId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "리뷰 작성", required = true,
                    content = @Content(schema = @Schema(implementation = ReviewSaveRequestDto.class)))
            @RequestBody ReviewSaveRequestDto reviewSaveRequestDto
    ) {

        reviewService.registerReview(orderNo, productId, reviewSaveRequestDto);
    }


}