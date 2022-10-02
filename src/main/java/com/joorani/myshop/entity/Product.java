package com.joorani.myshop.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Product extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @Column(unique = true, nullable = false)
    private int stockQuantity;

    @Column(unique = true, nullable = false)
    private int price;

    @Column(unique = true, nullable = false)
    private String imgPath;

    @Enumerated(value = EnumType.STRING)
    private ProductStatus productStatus;

    // 상점(판매자)
    @ManyToOne
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
        this.productStatus = setProductStatus();
        this.store = store;
    }

    public ProductStatus setProductStatus() {
        return productStatus = (this.stockQuantity == 0) ? ProductStatus.OUT_OF_STOCK : ProductStatus.ON_SALE;
    }

}
