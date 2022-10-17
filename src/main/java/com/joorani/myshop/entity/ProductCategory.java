package com.joorani.myshop.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class ProductCategory {

    @Column
    private Long firstCategoryId;
    private Long secondCategoryId;

    protected ProductCategory() {
    }

    public ProductCategory(Long firstCategoryId, Long secondCategoryId) {
        this.firstCategoryId = firstCategoryId;
        this.secondCategoryId = secondCategoryId;
    }
}
