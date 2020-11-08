package com.tech.enthusiasts.products.search.svc.vo.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tech.enthusiasts.products.search.svc.util.CommonUtil;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@JsonInclude(value = Include.NON_EMPTY)
@JsonIgnoreProperties
public class SuppliedProductsInfoView extends BaseView {

	private static final long serialVersionUID = 3301830893351828032L;

	@JsonProperty("productInformation")
	private ProductInfoView productInfoView;

	@JsonProperty("availableQuantity")
	private Integer suppliedQuantity;

	@JsonProperty("sellingPrice")
	private String productSellingPrice;
	
	private SuppliedProductsInfoView(final ProductInfoView productInfoView, final Integer suppliedQuantity,
			final String productSellingPrice) {
		this.productInfoView = productInfoView;
		this.suppliedQuantity = suppliedQuantity;
		this.productSellingPrice = productSellingPrice;
	}

	public static final class SuppliedProductsInfoBuilder {

		private Long productId;
		private String productName;
		private Integer suppliedQuantity;
		private String productSellingPrice;
		
		public SuppliedProductsInfoBuilder() {
			//Do-Nothing constructor
		}

		public SuppliedProductsInfoView build() {
			final ProductInfoView productInfoView = new ProductInfoView();
			productInfoView.setProductId(this.productId);
			productInfoView.setProductName(this.productName);
			return new SuppliedProductsInfoView(productInfoView, this.suppliedQuantity, this.productSellingPrice);
		}
		
		public SuppliedProductsInfoBuilder buildProductId(final Long productId) {
			this.productId = productId;
			return this;
		}
		
		public SuppliedProductsInfoBuilder buildProductName(final String productName) {
			this.productName = productName;
			return this;
		}
		
		public SuppliedProductsInfoBuilder buildAvailableQuantity(final Integer suppliedQuantity) {
			this.suppliedQuantity = suppliedQuantity;
			return this;
		}
		
		public SuppliedProductsInfoBuilder buildSellingPrice(final BigDecimal productSellingPrice) {
			this.productSellingPrice = CommonUtil.getFormattedPrice(productSellingPrice);
			return this;
		}
	}
}