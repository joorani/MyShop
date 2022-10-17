package com.joorani.myshop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class ProductOption {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stackQuantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public ProductOption(String name, int price, int stackQuantity) {
        this.name = name;
        this.price = price;
        this.stackQuantity = stackQuantity;
    }

    public void addProduct(Product product) {
        this.product = product;
        if (!product.getOptions().contains(this)) {
            product.getOptions().add(this);
        }
     }



}

