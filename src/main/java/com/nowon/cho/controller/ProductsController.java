package com.nowon.cho.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.cho.domain.dto.admin.ProductsDTO;
import com.nowon.cho.domain.dto.admin.ProductsImgDTO;
import com.nowon.cho.service.ProductsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductsController {
	
	private final ProductsService productsService;
	
	@PostMapping("/products")
	public String productRegistration(ProductsDTO productsDTO, ProductsImgDTO productsImgDTO) {
		productsService.productSave(productsDTO, productsImgDTO);
		
		return "redirect:/admin/product-registration";
	}
	
	@PostMapping("/temp-product-img")
	@ResponseBody
	public Map<String, String> productsTemp(MultipartFile multipartFile) {
		return productsService.tempUpload(multipartFile);
	}
}