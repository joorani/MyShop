package com.joorani.myshop.service;

import com.joorani.myshop.controller.dtos.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    List<CategoryResponseDto> getFirstCategories();
}

