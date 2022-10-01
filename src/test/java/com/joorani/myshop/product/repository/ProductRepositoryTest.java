package com.joorani.myshop.product.repository;

import com.joorani.myshop.entity.Store;
import com.joorani.myshop.repository.StoreRepository;
import com.joorani.myshop.entity.Product;
import com.joorani.myshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    ProductRepository productRepository;

    Store store;

    @BeforeEach
    void setUp() {

        Store store = Store.builder()
                .id(1L)
                .registeredNumber("12-345-677")
                .storeName("store name")
                .build();

        storeRepository.save(store);
    }

    @Test
    void productRegister() {

        Product product = Product.builder()
                .store(store)
                .name("product name")
                .price(10000)
                .stockQuantity(2000)
                .imgPath("image path")
                .build();

        Product savedProduct = productRepository.save(product);

        assertThat(savedProduct.getName()).isEqualTo(product.getName());
    }

}