package com.tech.enthusiasts.products.search.svc.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.tech.enthusiasts.products.search.svc.assembler.SupplierInfoAssembler;
import com.tech.enthusiasts.products.search.svc.manager.CachingManager;
import com.tech.enthusiasts.products.search.svc.manager.SupplierInfoManager;
import com.tech.enthusiasts.products.search.svc.vo.entity.ProductSupplier;
import com.tech.enthusiasts.products.search.svc.vo.entity.Supplier;
import com.tech.enthusiasts.products.search.svc.vo.request.SupplierInfoReq;
import com.tech.enthusiasts.products.search.svc.vo.response.SupplierInfoView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.SuppliedProductsPriceView;

@Component
public class SupplierInfoFacade {
	
	private SupplierInfoManager supplierInfoManager;
	private SupplierInfoAssembler supplierInfoAssembler;
	private CachingManager cachingManager;
	
	@Autowired
	public SupplierInfoFacade(final SupplierInfoManager supplierInfoManager, final SupplierInfoAssembler supplierInfoAssembler, final CachingManager cachingManager) {
		this.supplierInfoManager = supplierInfoManager;
		this.supplierInfoAssembler = supplierInfoAssembler;
		this.cachingManager = cachingManager;
	}

	public ResponseEntity<SupplierInfoView> findProductsBySupplier(final Long supplierId) {
		final Optional<Supplier> supplierInfo = supplierInfoManager.findSupplierById(supplierId);
		final SupplierInfoView supplierInfoView = supplierInfoAssembler.assembleSupplierInfo(supplierInfo);
		return ResponseEntity.status(HttpStatus.OK).body(supplierInfoView);
	}

	public ResponseEntity<SupplierInfoView> addSupplierDetails(final SupplierInfoReq supplierInfoReq) {
		final Supplier supplierInfo = supplierInfoManager.addSupplierDetails(supplierInfoReq);
		final SupplierInfoView supplierInfoView = supplierInfoAssembler.assembleSupplierInfo(Optional.of(supplierInfo));
		cachingManager.clearAllCaches();
		return ResponseEntity.status(HttpStatus.OK).body(supplierInfoView);
	}
	
	public ResponseEntity<String> deleteSupplierById(final Long supplierId){
		supplierInfoManager.deleteSupplierById(supplierId);
		cachingManager.clearAllCaches();
		return ResponseEntity.status(HttpStatus.OK).body("Supplier with ID "+supplierId+" and its associated products are no longer available");
	}
	
	public ResponseEntity<List<SuppliedProductsPriceView>> findAllProductsGroupedByPrice() {
		final List<ProductSupplier> productSuppliers = supplierInfoManager.findAllProductsAndSuppliers();
		final List<SuppliedProductsPriceView> suppliedProducts = supplierInfoAssembler.assembleProductsAndSuppliers(productSuppliers);
		return ResponseEntity.status(HttpStatus.OK).body(suppliedProducts);
	}

	public ResponseEntity<Boolean> validateSupplierByName(final String supplierName) {
		return ResponseEntity.status(HttpStatus.OK).body(supplierInfoManager.findAllSuppliers().stream().map(Supplier :: getSupplierName)
				.anyMatch(name -> name.equals(supplierName)));
	}
}