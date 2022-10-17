package com.joorani.myshop.controller;

import com.joorani.myshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<?> getFirstCategories() {
        return ResponseEntity.ok(categoryService.getFirstCategories());
    }
}
