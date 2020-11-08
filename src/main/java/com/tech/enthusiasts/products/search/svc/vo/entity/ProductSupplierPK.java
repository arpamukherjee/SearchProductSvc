package com.tech.enthusiasts.products.search.svc.vo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@Embeddable
@NoArgsConstructor
public class ProductSupplierPK implements Serializable {

	private static final long serialVersionUID = -3415526760106178225L;

	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Column(name = "SUPPLIER_ID")
	private Long supplierId;

	public ProductSupplierPK(final Long productId, final Long supplierId) {
		this.productId = productId;
		this.supplierId = supplierId;
	}
	
}