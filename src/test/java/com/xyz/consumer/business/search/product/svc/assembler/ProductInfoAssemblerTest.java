package com.xyz.consumer.business.search.product.svc.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tech.enthusiasts.products.search.svc.assembler.ProductInfoAssembler;
import com.tech.enthusiasts.products.search.svc.vo.entity.Brand;
import com.tech.enthusiasts.products.search.svc.vo.entity.Product;
import com.tech.enthusiasts.products.search.svc.vo.entity.ProductCategory;
import com.tech.enthusiasts.products.search.svc.vo.response.ProductInfoView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.ProductBrandView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.ProductColorView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.ProductSizeView;

@ExtendWith(MockitoExtension.class)
public class ProductInfoAssemblerTest {
	
	@InjectMocks
	private ProductInfoAssembler productInfoAssembler;
	
	@Test
	public final void testAssembleProductsGroupByBrand() {
		final List<Product> products = new ArrayList<>();
		final Product product = new Product();
		final Brand brand = new Brand();
		final ProductCategory productCategory = new ProductCategory();
		final Product secondProduct = new Product();
		final Brand secondBrand = new Brand();
		final ProductCategory secondProductCategory = new ProductCategory();
		brand.setBrandName("LEE");
		productCategory.setProductCategoryName("SHIRTS");
		secondBrand.setBrandName("USPA");
		secondProductCategory.setProductCategoryName("T-SHIRTS");
		product.setBrand(brand);
		product.setProductCategory(productCategory);
		product.setProductName("FULL SLEEVS SHIRTS");
		product.setProductColor("WHITE");
		product.setProductId(112L);
		product.setProductSize("MEDIUM");
		secondProduct.setBrand(secondBrand);
		secondProduct.setProductCategory(secondProductCategory);
		secondProduct.setProductName("POLO T - SHIRTS");
		secondProduct.setProductColor("BLACK");
		secondProduct.setProductId(112L);
		secondProduct.setProductSize("SMALL");
		products.add(product);
		products.add(secondProduct);
		final List<ProductBrandView> response = productInfoAssembler.assembleProductsGroupByBrand(products);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(2, response.size());
	}
	
	@Test
	public final void testAssembleProductsGroupByColor() {
		final List<Product> products = new ArrayList<>();
		final Product product = new Product();
		final Brand brand = new Brand();
		final ProductCategory productCategory = new ProductCategory();
		final Product secondProduct = new Product();
		final Brand secondBrand = new Brand();
		final ProductCategory secondProductCategory = new ProductCategory();
		brand.setBrandName("LEE");
		productCategory.setProductCategoryName("SHIRTS");
		secondBrand.setBrandName("USPA");
		secondProductCategory.setProductCategoryName("T-SHIRTS");
		product.setBrand(brand);
		product.setProductCategory(productCategory);
		product.setProductName("FULL SLEEVS SHIRTS");
		product.setProductColor("WHITE");
		product.setProductId(112L);
		product.setProductSize("MEDIUM");
		secondProduct.setBrand(secondBrand);
		secondProduct.setProductCategory(secondProductCategory);
		secondProduct.setProductName("POLO T - SHIRTS");
		secondProduct.setProductColor("BLACK");
		secondProduct.setProductId(112L);
		secondProduct.setProductSize("SMALL");
		products.add(product);
		products.add(secondProduct);
		final List<ProductColorView> response = productInfoAssembler.assembleProductsGroupByColor(products);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(2, response.size());
	}
	
	@Test
	public final void testAssembleProductsGroupBySize() {
		final List<Product> products = new ArrayList<>();
		final Product product = new Product();
		final Brand brand = new Brand();
		final ProductCategory productCategory = new ProductCategory();
		final Product secondProduct = new Product();
		final Brand secondBrand = new Brand();
		final ProductCategory secondProductCategory = new ProductCategory();
		brand.setBrandName("LEE");
		productCategory.setProductCategoryName("SHIRTS");
		secondBrand.setBrandName("USPA");
		secondProductCategory.setProductCategoryName("T-SHIRTS");
		product.setBrand(brand);
		product.setProductCategory(productCategory);
		product.setProductName("FULL SLEEVS SHIRTS");
		product.setProductColor("WHITE");
		product.setProductId(112L);
		product.setProductSize("SMALL");
		secondProduct.setBrand(secondBrand);
		secondProduct.setProductCategory(secondProductCategory);
		secondProduct.setProductName("POLO T - SHIRTS");
		secondProduct.setProductColor("BLACK");
		secondProduct.setProductId(112L);
		secondProduct.setProductSize("SMALL");
		products.add(product);
		products.add(secondProduct);
		final List<ProductSizeView> response = productInfoAssembler.assembleProductsGroupBySize(products);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(1, response.size());
	}
	
	@Test
	public final void getProductInfoDetails() {
		final Product product = new Product();
		final Brand brand = new Brand();
		final ProductCategory productCategory = new ProductCategory();
		brand.setBrandName("LEE");
		productCategory.setProductCategoryName("SHIRTS");
		product.setBrand(brand);
		product.setProductCategory(productCategory);
		product.setProductName("FULL SLEEVS SHIRTS");
		product.setProductColor("WHITE");
		product.setProductId(112L);
		product.setProductSize("SMALL");
		final ProductInfoView productInfoView = productInfoAssembler.getProductInfoDetails(Optional.of(product));
		Assertions.assertNotNull(productInfoView);
		Assertions.assertNotNull(productInfoView.getProductId());
	}
	
	@Test
	public final void getProductInfoDetailsWhenProductIsEmpty() {
		final ProductInfoView productInfoView = productInfoAssembler.getProductInfoDetails(Optional.empty());
		Assertions.assertNotNull(productInfoView);
		Assertions.assertNull(productInfoView.getProductId());
	}

}
