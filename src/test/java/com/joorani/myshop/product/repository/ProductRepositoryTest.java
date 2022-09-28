package com.joorani.myshop.product.repository;

import com.joorani.myshop.member.domain.entity.Store;
import com.joorani.myshop.member.domain.repository.StoreRepository;
import com.joorani.myshop.product.domain.entity.Product;
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
        store = Store.builder()
                .email("email@test.com")
                .password("password")
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