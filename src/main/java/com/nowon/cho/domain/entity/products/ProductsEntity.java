package com.nowon.cho.domain.entity.products;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
	private long productNo;
	
	@Column(nullable = false)
	private String productName;
	
	@Column(nullable = false)
	private long price;
	
	@Lob
	private String productContent;
	
	@Column(nullable = false)
	private long productStack;
	
	@CreationTimestamp
	private LocalDateTime registrationDate;
	
	@Column(nullable = false)
	private long sale;
	
	private long sale_discount;
	private long sale_sum;
	private long wishlistCnt;
}