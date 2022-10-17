package com.joorani.myshop.service;

import com.joorani.myshop.common.exception.DBEmptyDataException;
import com.joorani.myshop.entity.Product;
import com.joorani.myshop.entity.Store;
import com.joorani.myshop.entity.dtos.OptionDto;
import com.joorani.myshop.entity.dtos.ProductRegisterForm;
import com.joorani.myshop.entity.dtos.RegisteredProductDto;
import com.joorani.myshop.repository.ProductRepository;
import com.joorani.myshop.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    // 추후 storeId 받지 않고 user 권한 확인하는 것으로 Refactoring 예정

    @Override
    @Transactional
    public Long registerProduct(ProductRegisterForm registerForm, Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new DBEmptyDataException("Not exist: store id = " + storeId));
        Product product = registerForm.toEntity();
        product.addStore(store);

        return productRepository.save(product).getId();
    }

    @Override
    @Transactional
    public List<RegisteredProductDto> findAllProducts(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new DBEmptyDataException("Not exist: store id = " + storeId));

        List<Product> products = productRepository.findAllByStore(store.getId());

        List<RegisteredProductDto> registeredProductDtos = products.stream().map(p -> RegisteredProductDto.builder()
                .productName(p.getName())
                .price(p.getPrice())
                .options(p.getOptions().stream().map(OptionDto::of).collect(Collectors.toList()))
                .build()).collect(Collectors.toList());

        return registeredProductDtos;
    }

}
