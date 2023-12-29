package com.nowon.cho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.nowon.cho.domain.dto.PutBasketDTO;
import com.nowon.cho.service.BasketService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BasketController {
	
	private final BasketService basket;

	@GetMapping("/basket")
	public String basket() {
		return "views/basket/basket";
	}
	@PostMapping("/basket")
	public String putBasket(PutBasketDTO dto) {
		basket.putProduct(dto);
		return "views/basket/basket";
	}
}
