package com.joorani.myshop.repository;

import com.joorani.myshop.controller.dtos.ProductResponseDto;
import com.joorani.myshop.controller.dtos.QProductResponseDto;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
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
    public Page<ProductResponseDto> searchProduct(String keyword, Pageable pageable) {
        return getProductResponseDtos(keyword, pageable, "");
    }


    @Override
    public Page<ProductResponseDto> searchProduct(String keyword, String sortCondition, Pageable pageable) {
        return getProductResponseDtos(keyword, pageable, sortCondition);
    }

    private Page<ProductResponseDto> getProductResponseDtos(String keyword, Pageable pageable, String sortCondition) {
        List<ProductResponseDto> content = queryFactory.select(new QProductResponseDto(
                        product.imgPath, store.storeName, product.name, product.price))
                .from(product)
                .leftJoin(product.store, store)
                .where(productNameContains(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(productSort(sortCondition))
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(product.count())
                .from(product)
                .leftJoin(product.store, store)
                .where(productNameContains(keyword));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression productNameContains(String keyword) {
        return StringUtils.hasText(keyword) ? product.name.contains(keyword) : null;
    }

    private OrderSpecifier<?> productSort(@Nullable String condition) {
        if (condition.isBlank()) {
            return new OrderSpecifier(Order.DESC, product.createdAt);
        } else if (condition.equals("price_low")) {
            return new OrderSpecifier(Order.ASC, product.price);
        } else if (condition.equals("price_high")) {
            return new OrderSpecifier(Order.DESC, product.price);
        }

        return null;
    }
}
