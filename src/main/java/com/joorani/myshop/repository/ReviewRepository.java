package com.joorani.myshop.repository;

import com.joorani.myshop.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByProductInfoProductId(Long productId);
}
