package com.nowon.cho.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.cho.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ProductController {

	private final ProductService pds;

	@GetMapping("/admin/product-registration")
	public String productRegistration() {
		return "admin/product/product-registration";
	}
	@ResponseBody
	@PostMapping("/temp-upload") 
	public Map<String, String> productTemp(MultipartFile productImg) throws IOException {
		return pds.tempUpload(productImg);
	}
	
	@GetMapping("/admin/product-find")
	public String productFindPage(Model model) {
		pds.findAll(model);
		return "admin/product/find";
	}
}
