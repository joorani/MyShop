package com.joorani.myshop.product.service;

import com.joorani.myshop.member.domain.entity.Store;
import com.joorani.myshop.member.domain.repository.StoreRepository;
import com.joorani.myshop.product.domain.dtos.ProductRegisterForm;
import com.joorani.myshop.product.domain.dtos.RegisteredProductDto;
import com.joorani.myshop.product.domain.entity.Product;
import com.joorani.myshop.product.domain.entity.ProductStatus;
import com.joorani.myshop.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RegisterProductServiceTest {

    @InjectMocks
    private RegisterProductService registerProductService;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private ProductRepository productRepository;

    @Test
    @DisplayName("상품 등록에 성공하는 경우")
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
        Product savedProduct = registerProductService.register(productRegisterForm, storeId);

        //then
        assertThat(savedProduct.getPrice()).isEqualTo(10000);
        assertThat(savedProduct.getProductStatus()).isEqualTo(ProductStatus.ON_SALE);

    }

    @Test
    @DisplayName("상품 등록 시 재고 0개인 경우 상품 상태는 품절이다.")
    void productRegister_outofstock() {
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
                .quantity(0)
                .imagePath("image path")
                .build();

        Product product = productRegisterForm.toEntity(store);

        when(storeRepository.findById(anyLong())).thenReturn(Optional.of(store));
        when(productRepository.save(any())).thenReturn(product);

        //when
        Product savedProduct = registerProductService.register(productRegisterForm, storeId);

        //then
        assertThat(savedProduct.getPrice()).isEqualTo(10000);
        assertThat(savedProduct.getProductStatus()).isEqualTo(ProductStatus.OUT_OF_STOCK);
    }

    @Test
    @DisplayName("Store가 등록한 전체 상품이 조회된다.")
    void findAll() {
        //given
        Long storeId = 1L;

        Store store = Store.builder()
                .id(storeId)
                .email("email@test.com")
                .password("password")
                .registeredNumber("12-345-677")
                .storeName("store name")
                .build();

        Product product1 = Product.builder()
                .name("product1")
                .price(10000)
                .stockQuantity(2000)
                .imgPath("image path")
                .store(store)
                .build();

        Product product2 = Product.builder()
                .name("product2")
                .price(7000)
                .stockQuantity(3000)
                .imgPath("image path")
                .store(store)
                .build();

        List<Product> productList= new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        when(storeRepository.findById(anyLong())).thenReturn(Optional.of(store));
        when(productRepository.findAllByStore(any())).thenReturn(productList);

        List<RegisteredProductDto> registeredProductDtos = registerProductService.findAllProducts(storeId);

        assertThat(registeredProductDtos.get(0).getProductName()).isEqualTo(product1.getName());
        assertThat(registeredProductDtos.size()).isEqualTo(2);
    }
}