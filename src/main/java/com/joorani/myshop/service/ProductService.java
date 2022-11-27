package com.joorani.myshop.service;

import com.joorani.myshop.controller.dtos.ProductDetailResponseDto;
import com.joorani.myshop.controller.dtos.ProductRegisterForm;
import com.joorani.myshop.controller.dtos.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    // 상품등록
    Long registerProduct(ProductRegisterForm registerForm, Long storeId);

    // 등록된 상품조회
    List<ProductDetailResponseDto> findAllProducts(Long storeId);

    //상품 상세조회
    ProductDetailResponseDto findProductDetail(Long productId);

    //검색어로 상품 조회
    Page<ProductResponseDto> searchProducts(String keyword, Pageable pageable);

    Page<ProductResponseDto> searchProducts(String keyword, String sortCondition, Pageable pageable);


}
