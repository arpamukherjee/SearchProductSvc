package com.tech.enthusiasts.products.search.svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.enthusiasts.products.search.svc.vo.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

}
