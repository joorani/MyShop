package com.joorani.myshop.controller.dtos;

import com.joorani.myshop.entity.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
public class CategoryResponseDto {

    private Long id;
    private String name;
    private int depth;
    private List<CategoryResponseDto> children;

    @Builder
    public CategoryResponseDto(Long id, String name, int depth, List<CategoryResponseDto> children) {
        this.id = id;
        this.name = name;
        this.depth = depth;
        this.children = children;
    }

    public static CategoryResponseDto of(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .depth(category.getDepth())
                .children(category.getChildren().stream().map(CategoryResponseDto::of).collect(Collectors.toList()))
                .build();
    }
}
