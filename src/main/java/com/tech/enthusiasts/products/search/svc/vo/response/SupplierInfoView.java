package com.tech.enthusiasts.products.search.svc.vo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@JsonInclude(value = Include.NON_EMPTY)
@JsonIgnoreProperties
public class SupplierInfoView extends BaseView{

	private static final long serialVersionUID = 574830967527521390L;

	private Long supplierId;
	
	private String supplierName;
	
	@JsonProperty("suppliedProducts")
	private List<SuppliedProductsInfoView> productsInfoViews;
}