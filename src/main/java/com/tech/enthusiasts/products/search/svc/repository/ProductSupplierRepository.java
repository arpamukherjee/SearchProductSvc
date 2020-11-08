package com.tech.enthusiasts.products.search.svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.enthusiasts.products.search.svc.vo.entity.ProductSupplier;
import com.tech.enthusiasts.products.search.svc.vo.entity.ProductSupplierPK;

public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, ProductSupplierPK> {

}
