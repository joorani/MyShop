package com.joorani.myshop.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @Embedded
    private OrderProductInfo productInfo;

    @Column(nullable = false)
    private String author;

    protected Review() {

    }

    @Builder
    public Review(String content, OrderProductInfo productInfo, String author) {
        this.content = content;
        this.productInfo = productInfo;
        this.author = author;
    }

    public void setProductInfo(OrderProductInfo productInfo) {
        if (productInfo == null) throw new IllegalArgumentException("No product");
        this.productInfo = productInfo;
    }

    public void update(String content) {
        this.content = content;
    }
}
