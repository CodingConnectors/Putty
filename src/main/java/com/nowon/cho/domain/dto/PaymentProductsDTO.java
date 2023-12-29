package com.nowon.cho.domain.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentProductsDTO {

	List<PaymentProduct> products;
}

@Getter
@Setter
class PaymentProduct {
	long productNo;
	int product_volume; // 구매수량
}
