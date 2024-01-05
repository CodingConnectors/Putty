package com.nowon.cho.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class totalPayDTO {
	private long productAmount=0;
	private long deliveryFee=0;
	private long discountAmount=0;
	private long totalPrice=0;
}
