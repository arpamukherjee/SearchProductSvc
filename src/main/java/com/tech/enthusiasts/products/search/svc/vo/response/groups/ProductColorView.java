package com.tech.enthusiasts.products.search.svc.vo.response.groups;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tech.enthusiasts.products.search.svc.vo.response.BaseView;
import com.tech.enthusiasts.products.search.svc.vo.response.ProductInfoView;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@JsonInclude(value = Include.NON_EMPTY)
@JsonIgnoreProperties
public class ProductColorView extends BaseView{

	private static final long serialVersionUID = 4271005236815930837L;

	private String colorName;

	@JsonProperty("products")
	private List<ProductInfoView> productInfoView;
}
