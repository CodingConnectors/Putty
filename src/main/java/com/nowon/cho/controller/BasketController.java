package com.nowon.cho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasketController {

	@GetMapping("/basket")
	public String basket() {
		return "views/basket/basket";
	}
}
