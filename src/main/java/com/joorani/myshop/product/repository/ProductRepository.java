package com.joorani.myshop.product.repository;

import com.joorani.myshop.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByStore(Long storeId);
}
