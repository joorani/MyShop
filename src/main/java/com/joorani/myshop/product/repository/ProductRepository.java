package com.joorani.myshop.product.repository;

import com.joorani.myshop.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
