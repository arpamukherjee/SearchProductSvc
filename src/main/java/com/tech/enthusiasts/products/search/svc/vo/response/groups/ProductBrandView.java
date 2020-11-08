package com.tech.enthusiasts.products.search.svc.vo.response.groups;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tech.enthusiasts.products.search.svc.vo.response.BaseView;
import com.tech.enthusiasts.products.search.svc.vo.response.ProductInfoView;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@JsonInclude(value = Include.NON_EMPTY)
@JsonIgnoreProperties
public class ProductBrandView extends BaseView{

	private static final long serialVersionUID = -9202499068075538051L;

	private String brandName;
	
	@JsonProperty("products")
	private List<ProductInfoView> productInfoView;
}
