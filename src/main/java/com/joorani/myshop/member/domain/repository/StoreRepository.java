package com.joorani.myshop.member.domain.repository;

import com.joorani.myshop.member.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
