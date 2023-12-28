package com.nowon.cho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nowon.cho.faq.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FaqController {
	
	@GetMapping("/faq/faq_info")
	public String faqinfo() {
		return "faq/faq_info";
	}
	
	@GetMapping("/faq/frequently_asked")
	public String frequently_asked() {
		return "faq/frequently_asked";
	}
	
	@GetMapping("faq/faq_notice")
	public String faq_notice() {
		return "faq/faq_notice";
	}
	
	@GetMapping("faq/faq_list")
	public String faq_list() {
		return "faq/faq_list";
	}
	

}
