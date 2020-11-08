package com.xyz.consumer.business.search.product.svc.manager;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tech.enthusiasts.products.search.svc.manager.ProductInfoManager;
import com.tech.enthusiasts.products.search.svc.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductInfoManagerTest {
	
	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductInfoManager productInfoManager;
	
	@Test
	public final void testFindAllProducts() {
		Mockito.when(productRepository.findAll()).thenReturn(new ArrayList<>());
		Assertions.assertNotNull(productInfoManager.findAllProducts());
	}
}
