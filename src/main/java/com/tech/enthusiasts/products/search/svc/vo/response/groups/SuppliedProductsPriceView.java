package com.tech.enthusiasts.products.search.svc.vo.response.groups;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tech.enthusiasts.products.search.svc.vo.response.BaseView;
import com.tech.enthusiasts.products.search.svc.vo.response.SuppliedProductsInfoView;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@JsonInclude(value = Include.NON_EMPTY)
@JsonIgnoreProperties
public class SuppliedProductsPriceView extends BaseView {
	
	private static final long serialVersionUID = -5513595140162510358L;
	
	private String price;
	
	@JsonProperty("productInfo")
	private List<SuppliedProductsInfoView> suppliedProductsInfoViews;
}