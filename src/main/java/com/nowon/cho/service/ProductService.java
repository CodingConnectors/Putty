package com.nowon.cho.service;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.cho.domain.dto.ProductDTO;
import com.nowon.cho.domain.dto.ProductImgDTO;

public interface ProductService {

	Map<String, String> tempUpload(MultipartFile productImg);

	void save(ProductDTO productDTO, ProductImgDTO productImgDTO);

	void findAll(Model model);

}
