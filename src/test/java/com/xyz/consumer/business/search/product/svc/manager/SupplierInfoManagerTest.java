package com.xyz.consumer.business.search.product.svc.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tech.enthusiasts.products.search.svc.manager.SupplierInfoManager;
import com.tech.enthusiasts.products.search.svc.repository.ProductSupplierRepository;
import com.tech.enthusiasts.products.search.svc.repository.SupplierRepository;
import com.tech.enthusiasts.products.search.svc.vo.entity.Brand;
import com.tech.enthusiasts.products.search.svc.vo.entity.Product;
import com.tech.enthusiasts.products.search.svc.vo.entity.ProductCategory;
import com.tech.enthusiasts.products.search.svc.vo.entity.Supplier;
import com.tech.enthusiasts.products.search.svc.vo.request.ProductSupplyReq;
import com.tech.enthusiasts.products.search.svc.vo.request.SupplierInfoReq;

@ExtendWith(MockitoExtension.class)
public class SupplierInfoManagerTest {
	
	@Mock
	private SupplierRepository supplierRepository;
	
	@Mock
	private ProductSupplierRepository productSupplierRepository;
	
	@InjectMocks
	private SupplierInfoManager supplierInfoManager;
	
	@Test
	public final void testAddSupplierDetails() {
		//Initialization
		final SupplierInfoReq supplierInfoReq = new SupplierInfoReq();
		final List<ProductSupplyReq> productSupplyRequests = new ArrayList<>();
		final ProductSupplyReq productSupplyReq = new ProductSupplyReq();
		final List<Product> products = new ArrayList<>();
		final Product product = new Product();
		final Brand brand = new Brand();
		final ProductCategory productCategory = new ProductCategory();
		supplierInfoReq.setProductSupplyRequests(productSupplyRequests);
		productSupplyRequests.add(productSupplyReq);
		supplierInfoReq.setSupplierName("E-Commerce");
		productSupplyReq.setProductId(112L);
		productSupplyReq.setQuantitiesSupplied(112);
		productSupplyReq.setSellingPrice(123.23D);
		brand.setBrandName("LEE");
		productCategory.setProductCategoryName("SHIRTS");
		product.setProductCategory(productCategory);
		product.setProductName("FULL SLEEVS SHIRTS");
		product.setProductColor("WHITE");
		product.setProductId(112L);
		product.setProductSize("MEDIUM");
		products.add(product);
		//Mocking
		Mockito.when(supplierRepository.findAllById(Mockito.any())).thenReturn(products);
		//Execution
		final Supplier supplier = supplierInfoManager.addSupplierDetails(supplierInfoReq);
		//Assertion
		Assertions.assertNotNull(supplier);
		Assertions.assertNotNull(supplier.getSupplierName());
	}
	
	

}
