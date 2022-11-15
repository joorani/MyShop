package com.joorani.myshop.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Product extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String imgPath;

    @Enumerated(value = EnumType.STRING)
    private ProductStatus productStatus;

    // 상점(판매자)
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    // 상품 카테고리
    @Embedded
    private ProductCategory productCategory;

    // 상품 옵션
    @OneToMany(mappedBy = "product") //조회만 가능
    private List<ProductOption> options = new ArrayList<>();

    protected Product() {

    }

    @Builder
    public Product(String name, int price, String imgPath, ProductStatus productStatus, Store store, ProductCategory productCategory, List<ProductOption> options) {
        this.name = name;
        this.price = price;
        this.imgPath = imgPath;
        this.productStatus = options.size() > 0 ? ProductStatus.ON_SALE : ProductStatus.OUT_OF_STOCK;
        this.store = store;
        this.productCategory = productCategory;
        this.options = options;
    }

    public void addStore(Store store) {
        this.store = store;
        if (!store.getProducts().contains(this)) {
            store.getProducts().add(this);
        }
    }
}
