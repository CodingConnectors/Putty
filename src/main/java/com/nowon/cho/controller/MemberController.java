package com.nowon.cho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

	@GetMapping("/members")
    public String mypage(Model model) {

            return "views/member/mypage";
        }
	
	@GetMapping("/members_data")
	public String Members_data() {
		
		return"views/member/members_data";
	}
	
}
