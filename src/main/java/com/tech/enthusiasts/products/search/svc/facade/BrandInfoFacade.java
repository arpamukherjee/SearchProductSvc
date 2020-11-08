package com.tech.enthusiasts.products.search.svc.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.tech.enthusiasts.products.search.svc.assembler.BrandInfoAssembler;
import com.tech.enthusiasts.products.search.svc.manager.BrandInfoManager;
import com.tech.enthusiasts.products.search.svc.manager.CachingManager;
import com.tech.enthusiasts.products.search.svc.vo.entity.Brand;
import com.tech.enthusiasts.products.search.svc.vo.request.BrandInfoReq;
import com.tech.enthusiasts.products.search.svc.vo.response.BrandInfoView;

@Component
public class BrandInfoFacade {

	private BrandInfoManager brandInfoManager;
	private BrandInfoAssembler brandInfoAssembler;
	private CachingManager cachingManager;

	@Autowired
	public BrandInfoFacade(final BrandInfoManager brandInfoManager, final BrandInfoAssembler brandInfoAssembler, final CachingManager cachingManager) {
		this.brandInfoManager = brandInfoManager;
		this.brandInfoAssembler = brandInfoAssembler;
		this.cachingManager = cachingManager;
	}

	public ResponseEntity<BrandInfoView> addBrandDetails(final BrandInfoReq brandInfoReq) {
		final Brand brand = brandInfoManager.addBrandDetails(brandInfoReq);
		final BrandInfoView brandInfoView = brandInfoAssembler.assembleBrandDetails(brand);
		cachingManager.clearAllCaches();
		return ResponseEntity.status(HttpStatus.OK).body(brandInfoView);
	}

	public ResponseEntity<String> deleteBrandById(final Long brandId) {
		brandInfoManager.deleteBrandById(brandId);
		cachingManager.clearAllCaches();
		return ResponseEntity.status(HttpStatus.OK).body("Brand with ID "+brandId+" and its associated products are no longer available");
	}

	public ResponseEntity<Boolean> validateBrandByName(final String brandName) {
		return ResponseEntity.status(HttpStatus.OK).body(brandInfoManager.findAllBrands().
				stream().map(Brand :: getBrandName).anyMatch(name -> name.equals(brandName)));
	}

}
