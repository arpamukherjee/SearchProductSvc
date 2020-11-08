package com.tech.enthusiasts.products.search.svc.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tech.enthusiasts.products.search.svc.vo.entity.Product;
import com.tech.enthusiasts.products.search.svc.vo.response.ProductInfoView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.ProductBrandView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.ProductColorView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.ProductSizeView;

@Component
public class ProductInfoAssembler {

	public List<ProductBrandView> assembleProductsGroupByBrand(final List<Product> totalProducts) {
		final List<ProductBrandView> brandGroups = new ArrayList<>();
		final Map<String, List<Product>> productBrandMap = totalProducts.stream().collect(Collectors.groupingBy(product -> product.getBrand().getBrandName()));
		productBrandMap.entrySet().stream().forEach(entry -> {
			final ProductBrandView brandGroup = new ProductBrandView();
			final List<Product> products = entry.getValue();
			final String brandName = entry.getKey();
			brandGroup.setBrandName(brandName);
			brandGroup.setProductInfoView(mapProductsToView(products, true, false, false));
			brandGroups.add(brandGroup);
		});
		return brandGroups;
	}

	private List<ProductInfoView> mapProductsToView(final List<Product> products, boolean isGroupByBrand, boolean isGroupByColor, boolean isGroupBySize) {
		return products.stream().map(product -> {
			final ProductInfoView infoView = new ProductInfoView();
			infoView.setProductId(product.getProductId());
			infoView.setProductName(product.getProductName());
			infoView.setProductBrandName(isGroupByBrand ? null : product.getBrand().getBrandName());
			infoView.setProductColor(isGroupByColor ? null : product.getProductColor());
			infoView.setProductSize(isGroupBySize ? null : product.getProductSize());
			return infoView;
		}).collect(Collectors.toList());
	}
	
	public List<ProductColorView> assembleProductsGroupByColor(final List<Product> totalProducts) {
		final List<ProductColorView> colorGroups = new ArrayList<>();
		final Map<String, List<Product>> productBrandMap = totalProducts.stream().collect(Collectors.groupingBy(Product :: getProductColor));
		productBrandMap.entrySet().stream().forEach(entry -> {
			final ProductColorView colorGroup = new ProductColorView();
			final List<Product> products = entry.getValue();
			final String color = entry.getKey();
			colorGroup.setColorName(color);
			colorGroup.setProductInfoView(mapProductsToView(products, false, true, false));
			colorGroups.add(colorGroup);
		});
		return colorGroups;
	}
	
	public List<ProductSizeView> assembleProductsGroupBySize(final List<Product> totalProducts) {
		final List<ProductSizeView> sizeGroups = new ArrayList<>();
		final Map<String, List<Product>> productBrandMap = totalProducts.stream().collect(Collectors.groupingBy(Product :: getProductSize));
		productBrandMap.entrySet().stream().forEach(entry -> {
			final ProductSizeView sizeGroup = new ProductSizeView();
			final List<Product> products = entry.getValue();
			final String size = entry.getKey();
			sizeGroup.setProductSize(size);
			sizeGroup.setProductInfoView(mapProductsToView(products, false, false, true));
			sizeGroups.add(sizeGroup);
		});
		return sizeGroups;
	}

	public ProductInfoView getProductInfoDetails(final Optional<Product> optionalProduct) {
		final ProductInfoView productInfoView = new ProductInfoView();
		optionalProduct.ifPresent(product -> {
			productInfoView.setProductColor(product.getProductColor());
			productInfoView.setProductId(product.getProductId());
			productInfoView.setProductName(product.getProductName());
			productInfoView.setProductSize(product.getProductSize());
			productInfoView.setProductBrandName(product.getBrand().getBrandName());
		});
		return productInfoView;
	}
}
