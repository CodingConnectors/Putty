package com.nowon.cho.domain.dto;

import java.util.List;

import com.nowon.cho.domain.entity.products.ProductsEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class PaymentProductsDTO {

	private String imp_uid;
	private String merchant_uid;
	private List<ProductsEntity> products;
	private List<Long> productNo;
	private List<Long> volume;
	private List<Long> totlaPirce;
}

