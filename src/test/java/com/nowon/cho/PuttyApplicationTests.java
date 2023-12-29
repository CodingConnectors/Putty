package com.nowon.cho;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.nowon.cho.domain.entity.BasketEntity;
import com.nowon.cho.domain.entity.BasketRepository;
import com.nowon.cho.domain.entity.MemberRepository;
import com.nowon.cho.domain.entity.products.ProductsEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootTest
class PuttyApplicationTests {


	private final BasketRepository basketRepo;
	private final MemberRepository memberRepo;
	private final ProductsEntityRepository prodRepo;
	
	@Test
	void contextLoads() {
		
	}

	@Test
	void 장바구니추가() {

		basketRepo.save(BasketEntity.builder()
				.member_no(memberRepo.findById(2L).orElse(null))
				.productNo(prodRepo.findById(2L).orElse(null))
				.volume(1)
				.build());
	}
}
