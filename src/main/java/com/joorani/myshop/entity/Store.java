package com.joorani.myshop.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Store{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 10)
    private String registeredNumber;

    @Column(unique = true, nullable = false, length = 20)
    private String storeName;

    @OneToMany(mappedBy = "store")
    private List<Product> products = new ArrayList<>();

    //memberId와 매핑
    protected Store() {
    }

    @Builder
    public Store(Long id, String registeredNumber, String storeName, List<Product> products) {
        this.id = id;
        this.registeredNumber = registeredNumber;
        this.storeName = storeName;
        this.products = products;
    }
}
