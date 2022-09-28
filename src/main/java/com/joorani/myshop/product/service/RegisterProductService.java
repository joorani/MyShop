package com.joorani.myshop.product.service;

import com.joorani.myshop.common.exception.RestApiException;
import com.joorani.myshop.member.domain.entity.Store;
import com.joorani.myshop.member.domain.repository.StoreRepository;
import com.joorani.myshop.product.domain.dtos.ProductRegisterForm;
import com.joorani.myshop.product.domain.dtos.RegisteredProductDto;
import com.joorani.myshop.product.domain.entity.Product;
import com.joorani.myshop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.joorani.myshop.common.exception.errorcode.CustomErrorCode.STORE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RegisterProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Product register(ProductRegisterForm form, Long storeId) {

        Store store = storeRepository.findById(storeId).orElseThrow(() -> new RestApiException(STORE_NOT_FOUND));
        return productRepository.save(form.toEntity(store));
    }

    public List<RegisteredProductDto> findAllProducts(Long storeId) {

        Store store = storeRepository.findById(storeId).orElseThrow(() -> new RestApiException(STORE_NOT_FOUND));
        List<Product> products = productRepository.findAllByStore(store.getId());

        List<RegisteredProductDto> registeredProductDtos = products.stream().map(p -> RegisteredProductDto.builder()
                .productName(p.getName())
                .price(p.getPrice())
                .stockQuantity(p.getStockQuantity())
                .productStatus(p.getProductStatus())
                .build()).collect(Collectors.toList());

        return registeredProductDtos;
    }

}

