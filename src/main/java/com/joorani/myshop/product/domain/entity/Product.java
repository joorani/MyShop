package com.joorani.myshop.product.domain.entity;

import com.joorani.myshop.member.domain.entity.Store;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Product extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private int stockQuantity;

    private int price;

    private String imgPath;

    // 상점(판매자)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    protected Product() {
    }

    @Builder
    public Product(Long id, String name, int stockQuantity, int price, String imgPath, Store store) {
        this.id = id;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.imgPath = imgPath;
        this.store = store;
    }
}
