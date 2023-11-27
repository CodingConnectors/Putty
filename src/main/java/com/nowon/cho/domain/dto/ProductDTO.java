package com.nowon.cho.domain.dto;

import com.nowon.cho.domain.entity.ProductEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

	private String name;
	private long price;
	private int stock;
	private String content;
	
	public ProductEntity toEntity() {
		return ProductEntity.builder()
				.name(name)
				.price(price)
				.stock(stock)
				.content(content)
				.build();
	}
}
