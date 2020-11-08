package com.tech.enthusiasts.products.search.svc.manager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.tech.enthusiasts.products.search.svc.repository.ProductRepository;
import com.tech.enthusiasts.products.search.svc.vo.entity.Product;

@Service
public class ProductInfoManager {
	
	private ProductRepository productRepository;
	
	@Autowired
	public ProductInfoManager(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Cacheable("productsInformation")
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}
	
	@Cacheable("productByIdCache")
	public Optional<Product> findProductById(final Long productId) {
		return productRepository.findById(productId);
	}
}