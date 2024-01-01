package com.nowon.cho.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.cho.domain.dto.PutBasketDTO;
import com.nowon.cho.domain.entity.BasketEntity;
import com.nowon.cho.domain.entity.BasketRepository;
import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.domain.entity.MemberRepository;
import com.nowon.cho.domain.entity.compisitekey.BasketEntityId;
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
				.memberNo(memberRepo.findById(dto.getMember()).orElse(null))
				.productNo(prodRepo.findById(dto.getProduct()).orElse(null))
				.volume(dto.getVolume())
				.build());
	}

	@Override
	public void findProduct(Model model) {
		BasketEntityId key = new BasketEntityId();
	    key.setMemberNo(1);
	    key.setProductNo(3);

	    MemberEntity memberEnti = memberRepo.findById(1L).orElse(null);
	    List<BasketEntity> basketEntity = basketRepo.findByMemberNo(memberEnti);
	    
	    if (basketEntity != null) {
	        model.addAttribute("basketList", basketEntity.stream().map(enti->enti.getProductNo().toProductListDTO()).collect(Collectors.toList()));
	    } else {
	        // 엔터티가 찾아지지 않은 경우를 처리할 수 있도록
	        System.out.println("basketEntity가 존재하지않습니다.");;
	    }
	}

}
