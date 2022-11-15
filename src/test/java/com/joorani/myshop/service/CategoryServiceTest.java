package com.joorani.myshop.service;

import com.joorani.myshop.entity.Category;
import com.joorani.myshop.entity.dtos.CategoryResponseDto;
import com.joorani.myshop.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    Category category1;
    Category category2;
    Category category1_1;
    Category category1_2;

    @BeforeEach
    void setUp() {
        category1 = Category.builder().id(1L)
                .name("상의")
                .depth(1)
                .build();

       category2 = Category.builder().id(2L)
                .name("하의")
                .depth(1)
                .build();

        categoryRepository.save(category1);
        categoryRepository.save(category2);

        category1_1 = Category.builder()
                .name("반팔티셔츠")
                .parent(category1)
                .depth(2).build();

        category1_2 = Category.builder()
                .name("긴팔티셔츠")
                .parent(category1)
                .depth(2).build();

        categoryRepository.save(category1_1);
        categoryRepository.save(category1_2);

        category1.addChildrenCategory(category1_1);
        category1.addChildrenCategory(category1_2);

    }

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }

    @Test
    void findAll() {
        List<CategoryResponseDto> responseDtos = categoryService.getFirstCategories();
        assertThat(responseDtos.size()).isEqualTo(2);
        assertThat(responseDtos.get(0).getChildren().size()).isEqualTo(2);
    }
}