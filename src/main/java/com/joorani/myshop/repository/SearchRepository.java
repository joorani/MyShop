package com.joorani.myshop.repository;

import com.joorani.myshop.controller.dtos.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchRepository {

    Page<ProductResponseDto> searchProduct(String keyword, Pageable pageable);

    Page<ProductResponseDto> searchProduct(String keyword, String sortCondition, Pageable pageable);

}
