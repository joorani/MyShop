package com.joorani.myshop.repository;

import com.joorani.myshop.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
