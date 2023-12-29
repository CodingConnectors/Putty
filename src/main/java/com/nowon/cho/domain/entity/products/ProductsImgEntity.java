package com.nowon.cho.domain.entity.products;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nowon.cho.domain.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products_img")
@SequenceGenerator(name = "gen_products_img_seq", sequenceName = "products_img_seq", allocationSize = 1)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductsImgEntity extends BaseEntity {
	
	@Id
	@GeneratedValue(generator = "gen_products_img_seq", strategy = GenerationType.SEQUENCE)
	private long imgNo;
	
	@Column(nullable = false)
	private String imgOrgName;
	
	@Column(nullable = false)
	private String imgNewName;
	
	@Column(nullable = false)
	private String bucketKey;
	
	@Column(nullable = false)
	private long imgSize;
	
	@Column(nullable = false)
	private boolean imgType;
	
	@ManyToOne
	ProductsEntity productsEntity;
}