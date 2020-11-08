package com.tech.enthusiasts.products.search.svc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tech.enthusiasts.products.search.svc.vo.entity.Product;
import com.tech.enthusiasts.products.search.svc.vo.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	
	//@EntityGraph(attributePaths = {"productSuppliers"})
	@Query("SELECT product FROM Product product LEFT JOIN FETCH product.productSuppliers WHERE product.productId IN :ids")
	public List<Product> findAllById(@Param("ids") final List<Long> productIds);

}
