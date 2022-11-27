package com.joorani.myshop.controller;

import com.joorani.myshop.controller.dtos.ProductRegisterForm;
import com.joorani.myshop.controller.dtos.ProductDetailResponseDto;
import com.joorani.myshop.controller.dtos.ProductResponseDto;
import com.joorani.myshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 현재 URI에서 storeId를 받고 있지만 security 적용 후 role에 따라서가능여부 확인하는 방식으로 Refactoring
     */
    @PostMapping("/products/{storeId}")
    public Long register(@RequestBody ProductRegisterForm registerForm, @PathVariable Long storeId) {
        return productService.registerProduct(registerForm, storeId);
    }

    /**
     * store가 등록한 전체 상품 조회 API
     * 사용자의 role에 따라서 조건을 달리해서 검색하도록 Refactoring
     */
    @GetMapping("/products")
    public List<ProductDetailResponseDto> findAllProducts(@RequestParam("storeId") Long storeId) {
        return productService.findAllProducts(storeId);
    }

    @GetMapping("products/{productId}")
    public ProductDetailResponseDto getProductInfo(@PathVariable Long productId) {
        return productService.findProductDetail(productId);
    }

    /**
     * 검색어로 상품 조회
     */
    @GetMapping("/search/integration")
    public Page<ProductResponseDto> searchIntegration(@RequestParam("q") String keyword, Pageable pageable) {
        return productService.searchProducts(keyword, pageable);
    }


    @GetMapping("/search/products")
    public Page<ProductResponseDto> searchProductOrderBy(@RequestParam("q") String keyword, @RequestParam("sort") String sortCondition, Pageable pageable) {
        return productService.searchProducts(keyword, sortCondition, pageable);
    }
}
