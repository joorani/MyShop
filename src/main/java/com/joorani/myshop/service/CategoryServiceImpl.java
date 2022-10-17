package com.joorani.myshop.service;

import com.joorani.myshop.entity.dtos.CategoryResponseDto;
import com.joorani.myshop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public List<CategoryResponseDto> getFirstCategories() {
        return categoryRepository.findAllByParentIsNull().stream().map(CategoryResponseDto::of).collect(Collectors.toList());
    }


}
