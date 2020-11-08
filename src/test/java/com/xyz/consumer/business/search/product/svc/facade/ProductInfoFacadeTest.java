package com.xyz.consumer.business.search.product.svc.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.tech.enthusiasts.products.search.svc.assembler.ProductInfoAssembler;
import com.tech.enthusiasts.products.search.svc.facade.ProductInfoFacade;
import com.tech.enthusiasts.products.search.svc.manager.ProductInfoManager;
import com.tech.enthusiasts.products.search.svc.vo.entity.Product;
import com.tech.enthusiasts.products.search.svc.vo.response.ProductInfoView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.ProductBrandView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.ProductColorView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.ProductSizeView;

@ExtendWith(MockitoExtension.class)
public class ProductInfoFacadeTest {
	
	@Mock
	private ProductInfoManager productManager;
	
	@Mock
	private ProductInfoAssembler productAssembler;
	
	@InjectMocks
	private ProductInfoFacade productInfoFacade;
	
	@Test
	public final void testFindAllProductsGroupedByBrand() {
		final List<Product> products = new ArrayList<>();
		final List<ProductBrandView> productsGroupedByBrand = new ArrayList<>();
		Mockito.when(productManager.findAllProducts()).thenReturn(products);
		Mockito.when(productAssembler.assembleProductsGroupByBrand(Mockito.anyList())).thenReturn(productsGroupedByBrand);
		final ResponseEntity<List<ProductBrandView>> responseEntity = productInfoFacade.findAllProductsGroupedByBrand();
		Assertions.assertNotNull(responseEntity);
	}
	
	@Test
	public final void testFindAllProductsGroupedBySize() {
		final List<Product> products = new ArrayList<>();
		final List<ProductSizeView> productsGroupedBySize = new ArrayList<>();
		Mockito.when(productManager.findAllProducts()).thenReturn(products);
		Mockito.when(productAssembler.assembleProductsGroupBySize(Mockito.anyList())).thenReturn(productsGroupedBySize);
		final ResponseEntity<List<ProductSizeView>> responseEntity = productInfoFacade.findAllProductsGroupedBySize();
		Assertions.assertNotNull(responseEntity);
	}
	
	@Test
	public final void testFindAllProductsGroupedByColor() {
		final List<Product> products = new ArrayList<>();
		final List<ProductColorView> productsGroupedByBrand = new ArrayList<>();
		Mockito.when(productManager.findAllProducts()).thenReturn(products);
		Mockito.when(productAssembler.assembleProductsGroupByColor(Mockito.anyList())).thenReturn(productsGroupedByBrand);
		final ResponseEntity<List<ProductColorView>> responseEntity = productInfoFacade.findAllProductsGroupedByColor();
		Assertions.assertNotNull(responseEntity);
	}
	
	@Test
	public final void testFindProductDetailsById() {
		final Product product = new Product();
		final ProductInfoView productInfoView = new ProductInfoView();
		Mockito.when(productManager.findProductById(Mockito.anyLong())).thenReturn(Optional.of(product));
		Mockito.when(productAssembler.getProductInfoDetails(Mockito.any())).thenReturn(productInfoView);
		final ResponseEntity<ProductInfoView> responseEntity = productInfoFacade.findProductDetailsById(13L);
		Assertions.assertNotNull(responseEntity);
	}
}