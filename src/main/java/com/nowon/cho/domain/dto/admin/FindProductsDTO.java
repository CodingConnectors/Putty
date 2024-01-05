package com.nowon.cho.domain.dto.admin;

import java.util.List;

import com.nowon.cho.domain.entity.products.ProductsImgEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindProductsDTO {
	
	private long productNo;
	private String productName;
	private long price;
	private long saleDiscount;
	private String productCategory;
	private List<ProductsImgEntity> imgs;
	private String mainImgUrl;
}