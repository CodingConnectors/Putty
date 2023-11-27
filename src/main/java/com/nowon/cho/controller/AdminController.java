package com.nowon.cho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

	@GetMapping("/admin/index")
	public String adminIndex() {
		return "admin/index";
	}
}
