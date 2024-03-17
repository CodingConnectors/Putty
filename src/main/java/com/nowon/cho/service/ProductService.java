package com.nowon.cho.service;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

	Map<String, String> tempUpload(MultipartFile productImg);

	void findAll(Model model);

}
