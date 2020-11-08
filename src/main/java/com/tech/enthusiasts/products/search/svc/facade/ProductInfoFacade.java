package com.tech.enthusiasts.products.search.svc.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.tech.enthusiasts.products.search.svc.assembler.ProductInfoAssembler;
import com.tech.enthusiasts.products.search.svc.manager.ProductInfoManager;
import com.tech.enthusiasts.products.search.svc.vo.entity.Product;
import com.tech.enthusiasts.products.search.svc.vo.response.ProductInfoView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.ProductBrandView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.ProductColorView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.ProductSizeView;

@Component
public class ProductInfoFacade {
	
	private ProductInfoManager productManager;
	private ProductInfoAssembler productAssembler;
	
	@Autowired
	public ProductInfoFacade(final ProductInfoManager productManager, final ProductInfoAssembler productAssembler) {
		this.productManager = productManager;
		this.productAssembler = productAssembler;
	}
	
	public ResponseEntity<List<ProductBrandView>> findAllProductsGroupedByBrand() {
		final List<Product> products = productManager.findAllProducts();
		final List<ProductBrandView> productsGroupedByBrand = productAssembler.assembleProductsGroupByBrand(products);
		return ResponseEntity.status(HttpStatus.OK).body(productsGroupedByBrand);
	}

	public ResponseEntity<List<ProductColorView>> findAllProductsGroupedByColor() {
		final List<Product> products = productManager.findAllProducts();
		final List<ProductColorView> productsGroupedByColor = productAssembler.assembleProductsGroupByColor(products);
		return ResponseEntity.status(HttpStatus.OK).body(productsGroupedByColor);
	}

	public ResponseEntity<List<ProductSizeView>> findAllProductsGroupedBySize() {
		final List<Product> products = productManager.findAllProducts();
		final List<ProductSizeView> productsGroupedBySize = productAssembler.assembleProductsGroupBySize(products);
		return ResponseEntity.status(HttpStatus.OK).body(productsGroupedBySize);
	}

	public ResponseEntity<ProductInfoView> findProductDetailsById(final Long productId) {
		final Optional<Product> product = productManager.findProductById(productId);
		final ProductInfoView productInfoView = productAssembler.getProductInfoDetails(product);
		return ResponseEntity.status(HttpStatus.OK).body(productInfoView);
	}

	public ResponseEntity<Boolean> validateProductByName(final String productName) {
		return ResponseEntity.status(HttpStatus.OK).body(productManager.findAllProducts().
				stream().map(Product :: getProductName).anyMatch(name -> name.equals(productName)));
	}	
}
