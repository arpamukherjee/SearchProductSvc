package com.tech.enthusiasts.products.search.svc.vo.response;

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
public class ProductInfoView extends BaseView {

	private static final long serialVersionUID = -5338392651054221832L;
	
	@JsonProperty("id")
	private Long productId;
	
	@JsonProperty("name")
	private String productName;
	
	@JsonProperty("color")
	private String productColor;
	
	@JsonProperty("size")
	private String productSize;
	
	@JsonProperty("brand")
	private String productBrandName;
}
