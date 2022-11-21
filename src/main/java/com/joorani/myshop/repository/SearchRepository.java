package com.joorani.myshop.repository;

import com.joorani.myshop.controller.dtos.ProductResponseDto;

import java.util.List;

public interface SearchRepository {

    List<ProductResponseDto> searchProduct(String keyword);
}
