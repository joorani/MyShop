package com.joorani.myshop.service;

import com.joorani.myshop.common.exception.DBEmptyDataException;
import com.joorani.myshop.entity.Product;
import com.joorani.myshop.controller.dtos.ReviewSaveRequestDto;
import com.joorani.myshop.entity.Review;
import com.joorani.myshop.repository.ProductRepository;
import com.joorani.myshop.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void registerReview(Long orderId, Long productId, ReviewSaveRequestDto reviewSaveRequestDto) {

        //주문번호 확인 로직, 작성자 추가

        Product product = findProduct(productId);
        reviewRepository.save(reviewSaveRequestDto.toEntity(product));
    }

    @Override
    public Review findReviewById(Long reviewId) {
        return findReview(reviewId);
    }

    @Override
    public void remove(Long reviewId) {
        Review review = findReview(reviewId);
        reviewRepository.delete(review);
    }

    @Override
    @Transactional
    public void update(Long reviewId, ReviewSaveRequestDto reviewSaveRequestDto) {
        Review review = findReview(reviewId);
        review.update(reviewSaveRequestDto.getContent());
    }

    private Review findReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new DBEmptyDataException("Not exist: Review id = " + reviewId));
    }

    private Product findProduct(Long productId) {
        log.info("findProduct ");
        return productRepository.findById(productId).orElseThrow(() -> new DBEmptyDataException("Not exist: product id = " + productId));
    }
}
