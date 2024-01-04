package com.nowon.cho.service;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import com.nowon.cho.domain.dto.PutBasketDTO;

public interface BasketService {

	void putProduct(Authentication auth, PutBasketDTO dto);

	void findProduct(Authentication auth, Model model);

	void deleteBasket(Authentication auth, long productId);

}
