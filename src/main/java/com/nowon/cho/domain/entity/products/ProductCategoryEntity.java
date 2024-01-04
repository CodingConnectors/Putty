package com.nowon.cho.domain.entity.products;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_category")
@SequenceGenerator(name = "gen_product_category_seq", sequenceName = "product_category_seq", allocationSize = 1)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductCategoryEntity {
	
	@Id
	@GeneratedValue(generator = "gen_product_category_seq", strategy = GenerationType.SEQUENCE)
	private long productCategoryNo;
	
	@Column(nullable = false)
	private String productCategoryName;
	
	@OneToMany(mappedBy = "productCategoryEntity")
	private List<ProductsEntity> products;
}