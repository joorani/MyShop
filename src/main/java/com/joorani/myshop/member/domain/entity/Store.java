package com.joorani.myshop.member.domain.entity;

import com.joorani.myshop.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Store extends UserBaseEntity {

    private String registeredNumber;
    private String storeName;

    @OneToMany(mappedBy = "store")
    private List<Product> products = new ArrayList<>();


}
