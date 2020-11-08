package com.tech.enthusiasts.products.search.svc.manager;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.tech.enthusiasts.products.search.svc.repository.BrandRepository;
import com.tech.enthusiasts.products.search.svc.repository.ProductCategoryRepository;
import com.tech.enthusiasts.products.search.svc.vo.entity.Brand;
import com.tech.enthusiasts.products.search.svc.vo.entity.Product;
import com.tech.enthusiasts.products.search.svc.vo.entity.ProductCategory;
import com.tech.enthusiasts.products.search.svc.vo.request.BrandInfoReq;

@Service
public class BrandInfoManager {

	private BrandRepository brandRepository;
	private ProductCategoryRepository productCategoryRepository;
	
	@Resource
	private BrandInfoManager brandInformationManager;

	@Autowired
	public BrandInfoManager(BrandRepository brandRepository, ProductCategoryRepository productCategoryRepository) {
		this.brandRepository = brandRepository;
		this.productCategoryRepository = productCategoryRepository;
	}

	/**
	 * Service method to add brand details
	 * @param brandInfoReq
	 * @return Brand
	 */
	public Brand addBrandDetails(final BrandInfoReq brandInfoReq) {
		final Brand brand = new Brand();
		brand.setBrandName(brandInfoReq.getBrandName());
		if (brandInfoReq.getProductInfo() != null && !brandInfoReq.getProductInfo().isEmpty()) {
			final Map<Long, ProductCategory> categoryMap = brandInformationManager.getAllCategories().stream()
					.collect(Collectors.toMap(ProductCategory::getProductCategoryId, Function.identity()));
			brandInfoReq.getProductInfo().stream().forEach(productInfo -> {
				final Product product = new Product();
				product.setProductColor(productInfo.getProductColor());
				product.setProductSize(productInfo.getProductSize());
				product.setProductName(productInfo.getProductName());
				product.setProductCategory(categoryMap.get(productInfo.getProductCategoryId()));
				product.setBrand(brand);
				brand.getProducts().add(product);
			});
		}
		brandRepository.saveAndFlush(brand);
		return brand;
	}

	/** Service method to delete a brand by ID
	 * @param brandId
	 */
	public void deleteBrandById(final Long brandId) {
		brandRepository.deleteById(brandId);
	}
	
	@Cacheable("categoriesCache")
	public List<ProductCategory> getAllCategories(){
		return productCategoryRepository.findAll();
	}
	
	@Cacheable("brandCache")
	public List<Brand> findAllBrands(){
		return brandRepository.findAll();
	}
}