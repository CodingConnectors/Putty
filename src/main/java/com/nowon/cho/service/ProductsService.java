package com.nowon.cho.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.nowon.cho.domain.dto.admin.ProductsDTO;
import com.nowon.cho.domain.dto.admin.ProductsImgDTO;

public interface ProductsService {
	
	Map<String, String> tempUpload(MultipartFile multipartFile);
	
	void productSave(ProductsDTO productsDTO, ProductsImgDTO productsImgDTO);
}