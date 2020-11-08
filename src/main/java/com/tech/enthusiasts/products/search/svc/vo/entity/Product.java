package com.tech.enthusiasts.products.search.svc.vo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@Entity
@Table(name = "PRODUCT", schema = "btoc")
@EntityListeners(value = AuditingEntityListener.class)
public class Product extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@ManyToOne
	@JoinColumn(name = "BRAND_ID", nullable = false)
	private Brand brand;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_CATEGORY_ID", nullable = false)
	private ProductCategory productCategory;
	
	@Column(name = "PRODUCT_COLOR")
	private String productColor;
	
	@Column(name = "PRODUCT_SIZE")
	private String productSize;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, orphanRemoval = true)
	@lombok.EqualsAndHashCode.Exclude
	@lombok.ToString.Exclude
	private Set<ProductSupplier> productSuppliers = new HashSet<>();
}