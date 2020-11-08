package com.tech.enthusiasts.products.search.svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.enthusiasts.products.search.svc.vo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}