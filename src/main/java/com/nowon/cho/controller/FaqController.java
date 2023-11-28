package com.nowon.cho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FaqController {
	
	@GetMapping("/faq/faq_info")
	public String faqinfo() {
		return "faq/faq_info";
	}

}
