package com.tech.enthusiasts.products.search.svc.vo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public abstract class BaseEntity {
	
	@Column(name = "CRTN_GTS", updatable = false)
	@CreatedDate
	private LocalDateTime creationDateTime;
	
	@Column(name = "LST_UPDTD_GTS")
	@LastModifiedDate
	private LocalDateTime lastModifiedDateTime;
	
	@Column(name = "CRTN_USR_ID")
	private String creationUserId;
	
	@Column(name = "LST_MODFIED_USR_ID")
	private String lastModifiedUserId;
}