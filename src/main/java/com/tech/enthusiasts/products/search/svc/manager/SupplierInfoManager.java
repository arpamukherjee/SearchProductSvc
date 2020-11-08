package com.tech.enthusiasts.products.search.svc.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tech.enthusiasts.products.search.svc.repository.ProductSupplierRepository;
import com.tech.enthusiasts.products.search.svc.repository.SupplierRepository;
import com.tech.enthusiasts.products.search.svc.util.CommonUtil;
import com.tech.enthusiasts.products.search.svc.vo.entity.Product;
import com.tech.enthusiasts.products.search.svc.vo.entity.ProductSupplier;
import com.tech.enthusiasts.products.search.svc.vo.entity.Supplier;
import com.tech.enthusiasts.products.search.svc.vo.request.ProductSupplyReq;
import com.tech.enthusiasts.products.search.svc.vo.request.SupplierInfoReq;

@Service
public class SupplierInfoManager {
	
	private SupplierRepository supplierRepository;
	private ProductSupplierRepository productSupplierRepository;
	
	@Autowired
	public SupplierInfoManager(final SupplierRepository supplierRepository, final ProductSupplierRepository productSupplierRepository) {
		this.supplierRepository = supplierRepository;
		this.productSupplierRepository = productSupplierRepository;
	}
	
	@Cacheable("supplierByIdCache")
	public Optional<Supplier> findSupplierById(final Long supplierId) {
		final Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
		optionalSupplier.ifPresent(Supplier :: getProductSuppliers);
		return optionalSupplier;
	}
	
	@Transactional
	public Supplier addSupplierDetails(final SupplierInfoReq supplierInfoReq) {
		final Supplier supplier = new Supplier();
		persistSupplier(supplier, supplierInfoReq.getSupplierName());
		final List<ProductSupplyReq> productSupplyRequests = supplierInfoReq.getProductSupplyRequests();
		if(productSupplyRequests != null && !productSupplyRequests.isEmpty()) {
			final Map<Long, ProductSupplyReq> mapOfProducts = productSupplyRequests.stream().collect(Collectors.toMap(ProductSupplyReq :: getProductId, Function.identity()));
			final List<Product> products = supplierRepository.findAllById(productSupplyRequests.stream().map(ProductSupplyReq :: getProductId).collect(Collectors.toList()));
			final List<ProductSupplier> productSuppliers = new ArrayList<>();
			products.stream().forEach(product -> {
				final ProductSupplyReq productSupplyReq = mapOfProducts.get(product.getProductId());
				final ProductSupplier productSupplier = new ProductSupplier(product, supplier, productSupplyReq.getQuantitiesSupplied(), CommonUtil.getPriceInDecimal(productSupplyReq.getSellingPrice()));
				productSuppliers.add(productSupplier);
			});
			productSupplierRepository.saveAll(productSuppliers);
		}
		return supplier;
	}

	private void persistSupplier(final Supplier supplier, final String supplierName) {
		supplier.setSupplierName(supplierName);
		supplierRepository.saveAndFlush(supplier);
	}
	
	public void deleteSupplierById(final Long supplierId) {
		supplierRepository.deleteById(supplierId);
	}
	
	@Cacheable("allProductsAndSuppliersCache")
	public List<ProductSupplier> findAllProductsAndSuppliers() {
		return productSupplierRepository.findAll();
	}
	
	@Cacheable("suppliersCache")
	public List<Supplier> findAllSuppliers(){
		return supplierRepository.findAll();
	}
}