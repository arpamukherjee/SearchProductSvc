package com.tech.enthusiasts.products.search.svc.assembler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tech.enthusiasts.products.search.svc.util.CommonUtil;
import com.tech.enthusiasts.products.search.svc.vo.entity.ProductSupplier;
import com.tech.enthusiasts.products.search.svc.vo.entity.Supplier;
import com.tech.enthusiasts.products.search.svc.vo.response.SuppliedProductsInfoView;
import com.tech.enthusiasts.products.search.svc.vo.response.SupplierInfoView;
import com.tech.enthusiasts.products.search.svc.vo.response.SuppliedProductsInfoView.SuppliedProductsInfoBuilder;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.SuppliedProductsPriceView;

@Component
public class SupplierInfoAssembler {

	/**
	 * Mapping the supplier entity into equivalent view instance
	 * @param supplierInfo
	 * @return SupplierInfoView
	 */
	public SupplierInfoView assembleSupplierInfo(final Optional<Supplier> supplierInfo) {
		final SupplierInfoView infoView = new SupplierInfoView();
		supplierInfo.ifPresent(supplier -> {
			final Set<ProductSupplier> suppliedProducts = supplier.getProductSuppliers();
			final List<SuppliedProductsInfoView> suppliedProductsInfos = suppliedProducts.stream()
					.map(suppliedProduct -> {
						final SuppliedProductsInfoBuilder suppliedProductInfoBuilder = new SuppliedProductsInfoBuilder();
						return suppliedProductInfoBuilder.buildProductId(suppliedProduct.getProduct().getProductId())
								.buildProductName(suppliedProduct.getProduct().getProductName())
								.buildAvailableQuantity(suppliedProduct.getQuantity())
								.buildSellingPrice(suppliedProduct.getProductSellingPrice()).build();
					}).collect(Collectors.toList());

			infoView.setProductsInfoViews(suppliedProductsInfos);
			infoView.setSupplierId(supplier.getSupplierId());
			infoView.setSupplierName(supplier.getSupplierName());
		});
		return infoView;
	}

	/**
	 * Mapping the collection of productSuppliers into view instance
	 * @param productSuppliers
	 * @return List<SuppliedProductsPriceView>
	 */
	public List<SuppliedProductsPriceView> assembleProductsAndSuppliers(final List<ProductSupplier> productSuppliers) {
		final Map<Double, List<ProductSupplier>> mapOfProductSuppliers = new HashMap<>();
		final List<SuppliedProductsPriceView> suppliedProductsPriceViews = new ArrayList<>();
		productSuppliers.stream().forEach(productSupplier -> {
			final Double sellingPrice = productSupplier.getProductSellingPrice().doubleValue();
			if(mapOfProductSuppliers.containsKey(sellingPrice)) {
				List<ProductSupplier> productSuppliersVal = mapOfProductSuppliers.get(sellingPrice);
				productSuppliersVal.add(productSupplier);
				mapOfProductSuppliers.put(sellingPrice, productSuppliersVal);
			}else {
				List<ProductSupplier> productSuppliersVal = new ArrayList<>();
				productSuppliersVal.add(productSupplier);
				mapOfProductSuppliers.put(sellingPrice, productSuppliersVal);
			}
		});
		mapOfProductSuppliers.entrySet().stream().forEach(entry -> {
			final SuppliedProductsPriceView suppliedProductsPriceView = new SuppliedProductsPriceView();
			final List<ProductSupplier> productSuppliersVal = entry.getValue();
			suppliedProductsPriceView.setSuppliedProductsInfoViews(new ArrayList<>());
			suppliedProductsPriceView.setPrice(CommonUtil.getFormattedPrice(entry.getKey()));
			productSuppliersVal.stream().forEach(suppliedProduct -> {
				final SuppliedProductsInfoBuilder suppliedProductInfoBuilder = new SuppliedProductsInfoBuilder();
				final SuppliedProductsInfoView infoView = suppliedProductInfoBuilder.buildProductId(suppliedProduct.getProduct().getProductId())
						.buildProductName(suppliedProduct.getProduct().getProductName())
						.buildAvailableQuantity(suppliedProduct.getQuantity()).build();
				suppliedProductsPriceView.getSuppliedProductsInfoViews().add(infoView);
			});
			suppliedProductsPriceViews.add(suppliedProductsPriceView);
		}); 
		return suppliedProductsPriceViews;
	}

}