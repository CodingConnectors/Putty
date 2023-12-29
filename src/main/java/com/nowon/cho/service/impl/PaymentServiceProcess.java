package com.nowon.cho.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.cho.domain.dto.PaymentProductsDTO;
import com.nowon.cho.service.PaymentService;

@Service
public class PaymentServiceProcess implements PaymentService {

	@Override
	public void page(PaymentProductsDTO dto, Model model) {
		long productAmount=0;
		long deliveryFee=0;
		long discountAmount=0;
		long totalPrice=0;
		dto.getProducts().forEach(null);
	}

}
