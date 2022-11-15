package com.joorani.myshop.service;

import com.joorani.myshop.controller.dtos.ProductRegisterForm;
import com.joorani.myshop.controller.dtos.ProductResponseDto;

import java.util.List;

public interface ProductService {

    // 상품등록
    Long registerProduct(ProductRegisterForm registerForm, Long storeId);

    // 등록된 상품조회
    List<ProductResponseDto> findAllProducts(Long storeId);

    //상품 상세조회
    ProductResponseDto findProductDetail(Long productId);
}
