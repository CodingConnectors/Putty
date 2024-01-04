package com.nowon.cho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nowon.cho.domain.dto.PaymentProductsDTO;
import com.nowon.cho.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/payment")
public class PaymentCotroller {

	private final PaymentService service;
	
	@GetMapping
	public String paymentOpen() {
		return "views/payment/payment";
	}

	@PostMapping
	public String paymentPage(@ModelAttribute PaymentProductsDTO dto,Model model) {
//		System.out.println("list>>>>:"+dto.getProductNo());
//		dto.getProductNo().forEach(a->System.out.println(a)); 
		service.page(dto, model);
		
		return "views/payment/payment";
	}
	
	
	@GetMapping("/suc")
	public String successOpen() {
		return "views/payment/success";
	}
}
