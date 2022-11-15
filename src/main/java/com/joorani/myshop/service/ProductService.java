package com.joorani.myshop.service;

import com.joorani.myshop.entity.dtos.ProductRegisterForm;
import com.joorani.myshop.entity.dtos.RegisteredProductDto;

import java.util.List;

public interface ProductService {

    // 상품등록
    Long registerProduct(ProductRegisterForm registerForm, Long storeId);

    // 등록된 상품조회
    List<RegisteredProductDto> findAllProducts(Long storeId);
}
