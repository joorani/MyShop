package com.joorani.myshop.product.controller;

import com.joorani.myshop.product.domain.dtos.ProductRegisterForm;
import com.joorani.myshop.product.domain.dtos.RegisteredProductDto;
import com.joorani.myshop.product.service.RegisterProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final RegisterProductService registerProductService;

    //추후 id 넘겨주는 대신 권한있는 사용자만 등록할 수 있도록 Refactoring
    @PostMapping("/store/{storeId}")
    public Long register(@RequestBody ProductRegisterForm registerForm, @PathVariable Long storeId) {
        return registerProductService.register(registerForm, storeId).getId();
    }

    /**
     * store가 등록한 전체 상품 조회 API
     */
    @GetMapping("/store/{storeId}")
    public List<RegisteredProductDto> findAllProducts(@PathVariable Long storeId) {
        return registerProductService.findAllProducts(storeId);
    }

}
