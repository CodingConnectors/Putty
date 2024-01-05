package com.nowon.cho.domain.dto.admin;

import com.nowon.cho.domain.entity.products.ProductsEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductsDTO {
	
	private String productName;
	private long price;
	private long saleDiscount;
	private long productStock;
	private String productCategory;
	private String productContent;
	
	public ProductsEntity toProductsEntity() {
		return ProductsEntity.builder()
				.productName(productName)
				.price(price)
				.saleDiscount(saleDiscount)
				.productStock(productStock)
				.productCategory(productCategory)
				.productContent(productContent)
				.build();
	}
}