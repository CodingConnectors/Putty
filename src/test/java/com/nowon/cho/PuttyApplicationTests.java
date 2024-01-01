package com.nowon.cho;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nowon.cho.domain.entity.BasketEntity;
import com.nowon.cho.domain.entity.BasketRepository;
import com.nowon.cho.domain.entity.MemberRepository;
import com.nowon.cho.domain.entity.products.ProductsEntityRepository;

@SpringBootTest
class PuttyApplicationTests {


	@Autowired
	private BasketRepository basketRepo;
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private ProductsEntityRepository prodRepo;
	
	//@Test
	void contextLoads() {
		
	}

	@Test
	void 장바구니추가() {

		basketRepo.save(BasketEntity.builder()
				.memberNo(memberRepo.findById(1L).orElse(null))
				.productNo(prodRepo.findById(4L).orElse(null))
				.volume(2)
				.build());
	}
}
