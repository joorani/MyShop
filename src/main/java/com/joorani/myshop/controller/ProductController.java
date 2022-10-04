package com.joorani.myshop.controller;

import com.joorani.myshop.entity.dtos.ProductRegisterForm;
import com.joorani.myshop.entity.dtos.RegisteredProductDto;
import com.joorani.myshop.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    /**
     * 현재 URI에서 storeId를 받고 있지만 security 적용 후 role에 따라서가능여부 확인하는 방식으로 Refactoring
     */
    @PostMapping("/products/{storeId}")
    public Long register(@RequestBody ProductRegisterForm registerForm, @PathVariable Long storeId) {
        return productServiceImpl.registerProduct(registerForm, storeId);
    }

    /**
     * store가 등록한 전체 상품 조회 API
     * 사용자의 role에 따라서 조건을 달리해서 검색하도록 Refactoring
     */
    @GetMapping("/products/{storeId}")
    public List<RegisteredProductDto> findAllProducts(@PathVariable Long storeId) {
        return productServiceImpl.findAllProducts(storeId);
    }

}
