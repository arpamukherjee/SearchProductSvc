package com.tech.enthusiasts.products.search.svc.vo.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@JsonInclude(value = Include.NON_EMPTY)
@JsonIgnoreProperties
public class ProductSupplyReq {

	@NotNull
	private Long productId;

	@NotNull
	private Integer quantitiesSupplied;

	@NotNull
	private Double sellingPrice;

}