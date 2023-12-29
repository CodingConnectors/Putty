package com.nowon.cho.domain.entity.products;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@SequenceGenerator(name = "gen_products_seq", sequenceName = "products_seq", allocationSize = 1)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductsEntity {
	
	@Id
	@GeneratedValue(generator = "gen_products_seq", strategy = GenerationType.SEQUENCE)
	private long productNo;
	
	@Column(nullable = false)
	private String productName;
	
	@Column(nullable = false)
	private long price;
	
	@Lob
	private String productContent;
	
	@Column(nullable = false)
	private long productStock;
	
	@CreationTimestamp
	private LocalDateTime registrationDate;
	
	private long saleDiscount;
	private long saleSum;
	private long wishlistCnt;
}