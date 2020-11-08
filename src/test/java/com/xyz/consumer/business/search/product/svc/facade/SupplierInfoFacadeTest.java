package com.xyz.consumer.business.search.product.svc.facade;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.tech.enthusiasts.products.search.svc.assembler.SupplierInfoAssembler;
import com.tech.enthusiasts.products.search.svc.facade.SupplierInfoFacade;
import com.tech.enthusiasts.products.search.svc.manager.SupplierInfoManager;
import com.tech.enthusiasts.products.search.svc.vo.entity.Supplier;
import com.tech.enthusiasts.products.search.svc.vo.response.SupplierInfoView;

@ExtendWith(MockitoExtension.class)
public class SupplierInfoFacadeTest {

	@Mock
	private SupplierInfoManager supplierInfoManager;

	@Mock
	private SupplierInfoAssembler supplierInfoAssembler;

	@InjectMocks
	private SupplierInfoFacade supplierInfoFacade;

	@Test
	public final void testFindProductsBySupplier() {
		final Supplier supplier = new Supplier();
		final SupplierInfoView supplierInfoView = new SupplierInfoView();
		final Optional<Supplier> supplierInfo = Optional.of(supplier);
		Mockito.when(supplierInfoManager.findSupplierById(Mockito.anyLong())).thenReturn(supplierInfo);
		Mockito.when(supplierInfoAssembler.assembleSupplierInfo(Mockito.any())).thenReturn(supplierInfoView);
		final ResponseEntity<SupplierInfoView> responseEntity = supplierInfoFacade.findProductsBySupplier(11L);
		Assertions.assertNotNull(responseEntity);
	}

}
