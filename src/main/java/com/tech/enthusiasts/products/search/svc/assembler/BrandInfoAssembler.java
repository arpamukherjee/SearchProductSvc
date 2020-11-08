package com.tech.enthusiasts.products.search.svc.assembler;

import org.springframework.stereotype.Component;

import com.tech.enthusiasts.products.search.svc.vo.entity.Brand;
import com.tech.enthusiasts.products.search.svc.vo.response.BrandInfoView;

@Component
public class BrandInfoAssembler {

	public BrandInfoView assembleBrandDetails(final Brand brand) {
		final BrandInfoView brandInfoView = new BrandInfoView();
		brandInfoView.setBrandId(brand.getBrandId());
		brandInfoView.setBrandName(brand.getBrandName());
		return brandInfoView;
	}

}
