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
@Table(name = "SUPPLIER", schema = "btoc")
@EntityListeners(value = AuditingEntityListener.class)
public class Supplier extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SUPPLIER_ID")
	private Long supplierId;

	@Column(name = "SUPPLIER_NAME")
	private String supplierName;
	
	@OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, orphanRemoval = true)
	@lombok.EqualsAndHashCode.Exclude
	@lombok.ToString.Exclude
	private Set<ProductSupplier> productSuppliers = new HashSet<>();
}