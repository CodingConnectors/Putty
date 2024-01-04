package com.nowon.cho.naverapi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nowon.cho.naverapi.NaverAPIService;
import com.nowon.cho.naverapi.request.DeptSaveDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class NaverAPIController {

	private final NaverAPIService service;
	
	@GetMapping("/naver/dept")
	public String dept(Model model) {
		service.deptList(model);
		return "naver/dept/list";
	}
	
	@PostMapping("/naver/dept")
	public String dept(DeptSaveDTO dto) {
		service.saveDept(dto);
		return "redirect:/naver/dept";
	}
	
	
}
