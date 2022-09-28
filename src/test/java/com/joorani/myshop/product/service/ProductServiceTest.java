package com.joorani.myshop.product.service;

import com.joorani.myshop.member.domain.entity.Store;
import com.joorani.myshop.member.domain.repository.StoreRepository;
import com.joorani.myshop.product.domain.dtos.ProductRegisterForm;
import com.joorani.myshop.product.domain.entity.Product;
import com.joorani.myshop.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private ProductRepository productRepository;


    @BeforeEach
    void setUp() {

    }

    @Test
    void productRegister_success() {
        //given
        Long storeId = 1L;

        Store store = Store.builder()
                .id(storeId)
                .email("email@test.com")
                .password("password")
                .registeredNumber("12-345-677")
                .storeName("store name")
                .build();

        ProductRegisterForm productRegisterForm = ProductRegisterForm.builder()
                .name("product name")
                .price(10000)
                .quantity(2000)
                .imagePath("image path")
                .build();

        Product product = productRegisterForm.toEntity(store);

        when(storeRepository.findById(anyLong())).thenReturn(Optional.of(store));
        when(productRepository.save(any())).thenReturn(product);

        //when
        Product savedProduct = productService.register(productRegisterForm, storeId);

        //then
        assertThat(savedProduct.getPrice()).isEqualTo(10000);

    }

}