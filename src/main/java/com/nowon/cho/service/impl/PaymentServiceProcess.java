package com.nowon.cho.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.cho.domain.dto.PaymentProductsDTO;
import com.nowon.cho.domain.entity.products.ProductsEntity;
import com.nowon.cho.domain.entity.products.ProductsEntityRepository;
import com.nowon.cho.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PaymentServiceProcess implements PaymentService {
	
	private final ProductsEntityRepository prodRepo;

	@Override
	public void page(PaymentProductsDTO dto, Model model) {
		long productAmount=0;
		long deliveryFee=0;
		long discountAmount=0;
		long totalPrice=0;
		

		List<ProductsEntity> products = prodRepo.findAllById(dto.getProductNo());
		
		System.out.println(products.toString());

		// 이후 로직에서 조회한 엔터티들을 활용
		model.addAttribute("reqPaymentList", products.stream().map(ProductsEntity::findProducts)
				.collect(Collectors.toList()));
	}

}
