package com.nowon.cho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowon.cho.domain.dto.HeaderBannerDTO;
import com.nowon.cho.service.HeaderBannerService;

@Controller
public class HeaderBannerController {
	
	@Autowired
	HeaderBannerService hbs;

	@GetMapping ("/admin/header-banner/save")
	public String hbc() {
		return "admin/header-banner/save";
	}
	@GetMapping("/admin/header-banne/find")
	@ResponseBody
	public List<String> find() {
		return hbs.find();
	}
	@PostMapping ("/admin/header-banner/save")
	public String save(HeaderBannerDTO headerBannerDTO) {
		hbs.save(headerBannerDTO);
		return "redirect:/";
	}
}
