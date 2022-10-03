package com.joorani.myshop.repository;

import com.joorani.myshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByStore(Long storeId);
}
