package com.nowon.cho.service.impl;

import org.springframework.stereotype.Service;

import com.nowon.cho.domain.dto.PutBasketDTO;
import com.nowon.cho.domain.entity.BasketEntity;
import com.nowon.cho.domain.entity.BasketRepository;
import com.nowon.cho.domain.entity.MemberRepository;
import com.nowon.cho.domain.entity.products.ProductsEntityRepository;
import com.nowon.cho.service.BasketService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BasketProcess implements BasketService {

	private final BasketRepository basketRepo;
	private final MemberRepository memberRepo;
	private final ProductsEntityRepository prodRepo;
	
	@Override
	public void putProduct(PutBasketDTO dto) {
		dto.setVolume();
		basketRepo.save(BasketEntity.builder()
				.member_no(memberRepo.findById(dto.getMember()).orElse(null))
				.productNo(prodRepo.findById(dto.getProduct()).orElse(null))
				.volume(dto.getVolume())
				.build());
	}

}
