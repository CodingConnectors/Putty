package com.nowon.cho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowon.cho.domain.dto.AddressDTO;
import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.domain.entity.address.AddressEntity;
import com.nowon.cho.domain.entity.address.AddressRepository;
import com.nowon.cho.service.AddressService;
import com.nowon.cho.service.SignService;

@Controller
public class MemberController {
	
	@Autowired
	private SignService signservice;
	
	@Autowired
    private AddressService addressservice;
	
	@GetMapping("/members")
    public String mypage(Model model) {

            return "views/member/mypage";
        }
	
	@GetMapping("/members-data")
	public String Members_data() {
		
		return"views/member/members_data";
	}
	
	@PostMapping("/members/address")
    public String saveAddress(Authentication auth, AddressDTO dto) throws Exception {
		addressservice.saveAddress(auth,dto );
        return "redirect:/members";
    }
	
}
