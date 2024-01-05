package com.nowon.cho.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.cho.domain.dto.PutBasketDTO;
import com.nowon.cho.domain.entity.BasketEntity;
import com.nowon.cho.domain.entity.BasketRepository;
import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.domain.entity.MemberRepository;
import com.nowon.cho.domain.entity.compisitekey.BasketEntityId;
import com.nowon.cho.domain.entity.products.ProductsEntityRepository;
import com.nowon.cho.security.MyUserDetails;
import com.nowon.cho.service.BasketService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BasketProcess implements BasketService {

	private final BasketRepository basketRepo;
	private final MemberRepository memberRepo;
	private final ProductsEntityRepository prodRepo;
	
	@Override
	public void putProduct(Authentication auth, PutBasketDTO dto) {
	    long memberNo = 0;
	    // 멤버넘버 추출
	    if(auth != null) {
	    	memberNo = authToMemberNo(auth);
	    } else {
	    	System.out.println("로그인정보없음");
	    }
		dto.setVolume();
		
		if(memberNo != 0) {
			basketRepo.save(BasketEntity.builder()
					.memberNo(memberRepo.findById(memberNo).orElse(null))
					.productNo(prodRepo.findById(dto.getProductNo()).orElse(null))
					.volume(dto.getVolume())
					.build());
		}
	}

	@Override
	public void findProduct(Authentication auth, Model model) {
	    long memberNo = 0;
	    // 멤버넘버 추출
	    if(auth != null) {
	    	memberNo = authToMemberNo(auth);
	    } else {
	    	System.out.println("로그인정보없음");
	    }

	    if( memberNo != 0 ) {
		    MemberEntity memberEnti = memberRepo.findById(memberNo).orElse(null);
		    List<BasketEntity> basketEntity = basketRepo.findByMemberNo(memberEnti);
		    
		    if (basketEntity != null) {
		        model.addAttribute("basketList", basketEntity.stream().map(enti->enti.getProductNo().findProducts()).collect(Collectors.toList()));
		    } else {
		        // 엔터티가 찾아지지 않은 경우를 처리할 수 있도록
		        System.out.println("basketEntity가 존재하지않습니다.");;
		    }
	    } else System.out.println("회원정보를 불러오는데 오류가 발생했습니다.");
	}
	@Override
	public void deleteBasket(Authentication auth, long productId) {
	    long memberNo = 0;
	    // 멤버넘버 추출
	    if(auth != null) {
	    	memberNo = authToMemberNo(auth);
	    } else {
	    	System.out.println("로그인정보없음");
	    }
	    if(memberNo != 0) {
			BasketEntityId key = new BasketEntityId();
			key.setMemberNo(memberNo);
			key.setProductNo(productId);
			basketRepo.deleteById(key);
	    } else System.out.println("회원정보를 불러오는데 오류가 발생했습니다.");
	}

	
	private long authToMemberNo(Authentication auth) {
	    // Authentication 객체에서 Principal을 가져온다.
	    Object principal = auth.getPrincipal();
	    // Principal이 UserDetails를 구현한 경우 UserDetails로 캐스팅한다.
	    if (principal instanceof UserDetails) {
	    	MyUserDetails userDetails = (MyUserDetails) principal;
	    	return userDetails.getMemberNo();
	    }
		return 0;
	}

}
