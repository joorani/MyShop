package com.joorani.myshop.repository;

import com.joorani.myshop.controller.dtos.ProductResponseDto;
import com.joorani.myshop.controller.dtos.QProductResponseDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.joorani.myshop.entity.QProduct.product;
import static com.joorani.myshop.entity.QStore.store;

@Repository
public class SearchRepositoryImpl implements SearchRepository{

    private final JPAQueryFactory queryFactory;

    public SearchRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ProductResponseDto> searchProduct(String keyword) {

        List<ProductResponseDto> content = queryFactory.select(new QProductResponseDto(
                        product.imgPath, store.storeName, product.name, product.price))
                .from(product)
                .leftJoin(product.store, store)
                .where(productNameContains(keyword))
                .fetch();

        return content;
    }

    private BooleanExpression productNameContains(String keyword) {
        return StringUtils.hasText(keyword) ? product.name.contains(keyword) : null;
    }
}
