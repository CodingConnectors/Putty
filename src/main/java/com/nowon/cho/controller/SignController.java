package com.nowon.cho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nowon.cho.domain.dto.MemberDTO;
import com.nowon.cho.service.SignService;



@Controller
public class SignController {
	
	@Autowired
	private  SignService signservice;
	
	//로그인 페이지 이동
	@GetMapping("/signin")
	public String signin() {
		return "views/sign/signin";
	}
	
	//회원가입 페이지 이동
	@GetMapping("/signup")
	public String signup() {
		return "views/sign/signup";
	}
	
	//이메일 찾기 페이지 이동
	@GetMapping("/find_email")
	public String find_email() {
		return "views/sign/find_email";
	}
	
	//비밀번호 찾기 페이지 이동
	@GetMapping("/find_password")
	public String find_password() {
		return "views/sign/find_password";
	}
	
	//로그인하기
	@PostMapping("/signin")
	public String signin(String email, String password) {
		System.out.println(email);
		System.out.println(password);
		return "redirect:/";
	}
	
	//회원가입 하기
	@PostMapping("/signup")
	public String signup(MemberDTO dto) throws Exception {
		signservice.saveMember(dto);
		return "redirect:/signin";
	}
	
	//이메일 찾기
	@PostMapping("/find_email")
	public String find_email(long tel_num) {
		System.out.println(tel_num);
		return "views/sign/find_email_after";
	}
	
	//비밀번호 찾기
	@PostMapping("/find_password")
	public String find_password(long tel_num, String email) {
		System.out.println(tel_num);
		System.out.println(email);
		return "views/sign/find_password_after";
	}
	
	//로그아웃 하기
	@RequestMapping("signout")
	public String signout() {
		return "redirect:/";
	}
	
}