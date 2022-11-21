package com.joorani.myshop.service;

import com.joorani.myshop.common.exception.DBEmptyDataException;
import com.joorani.myshop.controller.dtos.*;
import com.joorani.myshop.entity.Product;
import com.joorani.myshop.entity.Store;
import com.joorani.myshop.repository.ProductRepository;
import com.joorani.myshop.repository.SearchRepository;
import com.joorani.myshop.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final ReviewService reviewService;
    private final SearchRepository searchRepository;

    // 추후 storeId 받지 않고 user 권한 확인하는 것으로 Refactoring 예정

    @Override
    public Long registerProduct(ProductRegisterForm registerForm, Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new DBEmptyDataException("Not exist: store id = " + storeId));
        Product product = registerForm.toEntity();
        product.addStore(store);

        return productRepository.save(product).getId();
    }

    @Override
    public List<ProductDetailResponseDto> findAllProducts(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new DBEmptyDataException("Not exist: store id = " + storeId));

        List<Product> products = productRepository.findAllByStore(store.getId());

        List<ProductDetailResponseDto> productDetailResponseDtos = products.stream().map(p -> ProductDetailResponseDto.builder()
                .productName(p.getName())
                .price(p.getPrice())
                .options(p.getOptions().stream().map(OptionDto::of).collect(Collectors.toList()))
                .build()).collect(Collectors.toList());

        return productDetailResponseDtos;
    }

    @Override
    public ProductDetailResponseDto findProductDetail(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new DBEmptyDataException("Now exist: product id= " + productId));

        List<ReviewResponseDto> productReviews = reviewService.findProductReviews(productId);

        return ProductDetailResponseDto.builder().productName(product.getName())
                .price(product.getPrice())
                .reviews(productReviews)
                .build();

    }

    /**
     * 검색어로 상품 조회
     */
    @Override
    public Page<ProductResponseDto> searchProducts(String keyword, Pageable pageable) {
        return searchRepository.searchProduct(keyword, pageable);
    }
}

