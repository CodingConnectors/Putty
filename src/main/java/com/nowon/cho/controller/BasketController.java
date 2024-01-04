package com.nowon.cho.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowon.cho.domain.dto.PutBasketDTO;
import com.nowon.cho.security.MyUserDetails;
import com.nowon.cho.service.BasketService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BasketController {
	
	private final BasketService basket;

	@GetMapping("/basket")
	public String basket(Authentication auth,Model model) {
		basket.findProduct(auth, model);
		return "views/basket/basket";
	}
	@PostMapping("/basket")
	public String putBasket(Authentication auth, PutBasketDTO dto) {
		basket.putProduct(auth, dto);
		return "redirect:/basket";
	}
	
	@DeleteMapping("/basket/{productId}")
	public @ResponseBody String delBasket(Authentication auth,@PathVariable long productId) {
		basket.deleteBasket(auth, productId);
		return "삭제완료";
	}
}
