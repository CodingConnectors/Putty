package com.nowon.cho.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.cho.domain.dto.PaymentPageDTO;
import com.nowon.cho.domain.dto.PaymentProductsDTO;
import com.nowon.cho.domain.dto.totalPayDTO;
import com.nowon.cho.domain.dto.admin.FindProductsDTO;
import com.nowon.cho.domain.entity.products.ProductsEntity;
import com.nowon.cho.domain.entity.products.ProductsEntityRepository;
import com.nowon.cho.domain.entity.products.ProductsImgEntity;
import com.nowon.cho.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PaymentServiceProcess implements PaymentService {
	
	private final ProductsEntityRepository prodRepo;

	@Override
	public void page(PaymentProductsDTO dto, Model model) {
		
		List<ProductsEntity> products = prodRepo.findAllById(dto.getProductNo());
		
		//모델에 리스트 추가
		model.addAttribute("reqPaymentList", products.stream().map(ProductsEntity-> toPaymentPageDTO(ProductsEntity))
				.collect(Collectors.toList()));
		model.addAttribute("totalPays", paysProcess(products));
	}

	
	private totalPayDTO paysProcess(List<ProductsEntity> products) {
		long productAmount = products.stream().mapToLong(ProductsEntity::getPrice).sum();
		long discountAmount = products.stream().mapToLong(enti -> (long) (enti.getPrice() * (1.0*enti.getSaleDiscount()/100)) ).sum();
		long totalPrice = productAmount - discountAmount;
		return totalPayDTO.builder()
				.productAmount(productAmount)
				.deliveryFee(0)
				.discountAmount(discountAmount)
				.totalPrice(totalPrice)
				.build();
	}


	private PaymentPageDTO toPaymentPageDTO(ProductsEntity productsEntity) {
		ProductsImgEntity mainImg = productsEntity.getImgs().stream()
				.filter(img -> img.isImgType() == true)
				.findFirst()
				.get();
		
		return PaymentPageDTO.builder()
				.productNo(productsEntity.getProductNo())
				.productName(productsEntity.getProductName())
				.price(productsEntity.getPrice())
				.saleDiscount(productsEntity.getSaleDiscount())
				.mainImgUrl("https://0idealisawsbucket.s3.ap-northeast-2.amazonaws.com/" + mainImg.getBucketKey())
				.build();
	}


}
