package com.nowon.cho.domain.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class PaymentProductsDTO {

	private String imp_uid;
	private List<Long> productNo;
	private List<Long> volume;
}

