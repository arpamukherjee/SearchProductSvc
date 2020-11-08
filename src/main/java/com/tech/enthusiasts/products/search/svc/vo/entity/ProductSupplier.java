package com.tech.enthusiasts.products.search.svc.vo.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@Entity
@Table(name = "SUPPLIER_CRN_PRODUCT", schema = "btoc")
@NoArgsConstructor
@EntityListeners(value = AuditingEntityListener.class)
public class ProductSupplier extends BaseEntity {
	
	@EmbeddedId
	private ProductSupplierPK productSupplierId;

	@ManyToOne
	@MapsId(value = "productId")
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;

	@ManyToOne
	@MapsId(value = "supplierId")
	@JoinColumn(name = "SUPPLIER_ID")
	private Supplier supplier;

	@Column(name = "QUANTITY")
	private int quantity;
	
	@Column(name = "SELLING_PRICE")
	private BigDecimal productSellingPrice;

	public ProductSupplier(final Product product, final Supplier supplier, final int quantity, final BigDecimal productSellingPrice) {
		this.product = product;
		this.supplier = supplier;
		this.quantity = quantity;
		this.productSellingPrice = productSellingPrice;
		this.productSupplierId = new ProductSupplierPK(product.getProductId(), supplier.getSupplierId());
	}
	
	
}