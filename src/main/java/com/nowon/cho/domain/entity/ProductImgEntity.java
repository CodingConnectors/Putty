package com.nowon.cho.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table (name = "productImg")
@Entity
public class ProductImgEntity {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long no;
	
	private String bucketKey;
	private String orgName;
	
	@ManyToOne
	ProductEntity productEn;
}
