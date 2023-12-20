package com.nowon.cho.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
	
	@GetMapping("/categories")
	public String products() {
		return "product/products";
	}
	
	@GetMapping("/productdetail")
	public String productDetail() {
		return "product/product-detail";
	}
}