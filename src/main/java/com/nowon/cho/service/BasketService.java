package com.nowon.cho.service;

import org.springframework.ui.Model;

import com.nowon.cho.domain.dto.PutBasketDTO;

public interface BasketService {

	void putProduct(PutBasketDTO dto);

	void findProduct(Model model);

	void deleteBasket(long productId);

}
