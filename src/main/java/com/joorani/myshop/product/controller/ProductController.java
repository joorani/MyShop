package com.joorani.myshop.product.controller;

import com.joorani.myshop.product.domain.dtos.ProductRegisterForm;
import com.joorani.myshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public Long register(@RequestBody ProductRegisterForm registerForm, Long storeId) {
        return productService.register(registerForm, storeId).getId();
    }

}
