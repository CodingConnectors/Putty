package com.nowon.cho.service;

import org.springframework.ui.Model;

import com.nowon.cho.domain.dto.PaymentProductsDTO;

public interface PaymentService {

	void page(PaymentProductsDTO dto, Model model);

}
