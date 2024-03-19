package com.nowon.cho.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.amazonaws.services.s3.AmazonS3Client;
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

	private final AmazonS3Client amazonS3Client;
	
	@Value("${cloud.aws.s3.bucket}")
	private String butcketName;

	@Override
	public void page(PaymentProductsDTO dto, Model model) {
		
		dto.setProducts( prodRepo.findAllById(dto.getProductNo()) );
		List<PaymentPageDTO> paymentPageList = new ArrayList<>();
	    for (int i = 0; i < dto.getProducts().size(); i++) {
	        PaymentPageDTO paymentPageDTO = toPaymentPageDTO(dto.getProducts().get(i), dto.getVolume().get(i));
	        paymentPageList.add(paymentPageDTO);
	    }
		//모델에 리스트 추가

	    model.addAttribute("reqPaymentList", paymentPageList);
		model.addAttribute("totalPays", paysProcess(paymentPageList));
	}

	
	private totalPayDTO paysProcess(List<PaymentPageDTO> paymentPageList) {
		
		long productAmount = paymentPageList.stream().mapToLong(paylist->paylist.getPrice()*paylist.getVolume()).sum();
		long discountAmount = paymentPageList.stream().mapToLong(paylist -> (long) (paylist.getPrice() * (1.0*paylist.getSaleDiscount()/100)) * paylist.getVolume() ).sum();
		long totalPrice = productAmount - discountAmount;
		return totalPayDTO.builder()
				.productAmount(productAmount)
				.deliveryFee(0)
				.discountAmount(discountAmount)
				.totalPrice(totalPrice)
				.build();
		
	}


	private PaymentPageDTO toPaymentPageDTO(ProductsEntity productsEntity, long volume) {
	    ProductsImgEntity mainImg = productsEntity.getImgs().stream()
	            .filter(img -> img.isImgType() == true)
	            .findFirst()
	            .orElse(null); // ProductsImgEntity가 없을 경우에 대한 처리 추가할 수 있음
	    
	    return PaymentPageDTO.builder()
	            .productNo(productsEntity.getProductNo())
	            .productName(productsEntity.getProductName())
	            .price(productsEntity.getPrice())
	            .saleDiscount(productsEntity.getSaleDiscount())
	            .mainImgUrl(amazonS3Client.getUrl(butcketName, mainImg.getBucketKey()).toString())
	            .volume(volume) // volume 추가
	            .build();
	}


}
