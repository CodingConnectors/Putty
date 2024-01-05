package com.nowon.cho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.nowon.cho.service.SignService;

@Controller
public class MemberController {
	
	@Autowired
	private SignService signservice;

	@GetMapping("/members")
    public String mypage(Model model) {

            return "views/member/mypage";
        }
	
	@GetMapping("/members_data")
	public String Members_data() {
		
		return"views/member/members_data";
	}
	
	/*
	@PostMapping("/members_data")
    public String members_data(MemberUpdateDto memberUpdateDto) {
        // MemberUpdateDto는 수정할 정보를 담은 DTO 클래스로 필요에 따라 생성해야 합니다.
		signservice.updateMember(memberUpdateDto);
        return "redirect:views/member/mypage"; // 수정 후 프로필 페이지로 리다이렉트
    }*/
	
}
