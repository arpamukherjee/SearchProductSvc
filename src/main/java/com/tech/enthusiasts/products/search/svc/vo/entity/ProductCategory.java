package com.tech.enthusiasts.products.search.svc.vo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@Entity
@Table(name = "PRODUCT_CATEGORY", schema = "btoc")
@EntityListeners(value = AuditingEntityListener.class)
public class ProductCategory extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_CATEGORY_ID")
	private Long productCategoryId;
	
	@Column(name = "PRODUCT_CATEGORY_NAME")
	private String productCategoryName;
}