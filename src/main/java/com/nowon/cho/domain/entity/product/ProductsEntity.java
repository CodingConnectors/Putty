package com.nowon.cho.domain.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductsEntity {
	
	@Id
	long productNo;
	
	@Column(nullable = false)
	String productName;
	
	@Column(nullable = false)
	long price;
	
	String productContent;
	
	@Column(nullable = false)
	long productStack;
	
	@CreationTimestamp
	long registrationDate;
	
	@Column(nullable = false)
	long sale;
	
	long sale_discount;
	long sale_sum;
	long wishlistCnt;
}