package com.xyz.consumer.business.search.product.svc.controller;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tech.enthusiasts.products.search.svc.controller.ProductSearchController;
import com.tech.enthusiasts.products.search.svc.facade.ProductInfoFacade;
import com.tech.enthusiasts.products.search.svc.facade.SupplierInfoFacade;
import com.tech.enthusiasts.products.search.svc.vo.response.ProductInfoView;
import com.tech.enthusiasts.products.search.svc.vo.response.SupplierInfoView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.ProductBrandView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.ProductColorView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.ProductSizeView;

@ExtendWith(MockitoExtension.class)
public class ProductSearchControllerTest {

	@Mock
	private ProductInfoFacade productInfoFacade;

	@Mock
	private SupplierInfoFacade supplierInfoFacade;

	@InjectMocks
	private ProductSearchController productController;

	@Test
	public final void testFindAllProductsGroupedByBrand() {
		final ResponseEntity<List<ProductBrandView>> expected = new ResponseEntity<>(HttpStatus.OK);
		Mockito.when(productInfoFacade.findAllProductsGroupedByBrand()).thenReturn(expected);
		final ResponseEntity<List<ProductBrandView>> actual = productController.findAllProductsGroupedByBrand();
		Assertions.assertEquals(expected, actual);
	}

	@Test
	public final void testFindAllProductsGroupedByColor() {
		final ResponseEntity<List<ProductColorView>> expected = new ResponseEntity<>(HttpStatus.OK);
		Mockito.when(productInfoFacade.findAllProductsGroupedByColor()).thenReturn(expected);
		final ResponseEntity<List<ProductColorView>> actual = productController.findAllProductsGroupedByColor();
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public final void testFindAllProductsGroupedBySize() {
		final ResponseEntity<List<ProductSizeView>> expected = new ResponseEntity<>(HttpStatus.OK);
		Mockito.when(productInfoFacade.findAllProductsGroupedBySize()).thenReturn(expected);
		final ResponseEntity<List<ProductSizeView>> actual = productController.findAllProductsGroupedBySize();
		Assertions.assertEquals(expected, actual);
	}

	@Test
	public final void testFindProductById() {
		final ResponseEntity<ProductInfoView> expected = new ResponseEntity<>(HttpStatus.OK);
		Mockito.when(productInfoFacade.findProductDetailsById(Mockito.anyLong())).thenReturn(expected);
		final ResponseEntity<ProductInfoView> actual = productController.findProductById(11L);
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public final void testFindProductsBySupplier() {
		final ResponseEntity<SupplierInfoView> expected = new ResponseEntity<>(HttpStatus.OK);
		Mockito.when(supplierInfoFacade.findProductsBySupplier(Mockito.anyLong())).thenReturn(expected);
		final ResponseEntity<SupplierInfoView> actual = productController.findProductsBySupplier(112L);
		Assertions.assertEquals(expected, actual);
	}
}