package com.nowon.cho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class SignController {
	
	@GetMapping("/signin")
	public String signin() {
		return "views/sign/signin";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "views/sign/signup";
	}
	
	@GetMapping("/find_email")
	public String find_email() {
		return "views/sign/find_email";
	}
	
	@GetMapping("/find_password")
	public String find_password() {
		return "views/sign/find_password";
	}
	
	@PostMapping("/signin")
	public String signin(String email, String password) {
		System.out.println(email);
		System.out.println(password);
		return "redirect:/";
	}
	
	@PostMapping("/signup")
	public String signup(String email, String password, String name, long tel_num) {
		System.out.println(email);
		System.out.println(password);
		System.out.println(name);
		System.out.println(tel_num);
		return "redirect:/signin";
	}
	
	@PostMapping("/find_email")
	public String find_email(long tel_num) {
		System.out.println(tel_num);
		return "views/sign/find_email_after";
	}
	
	@PostMapping("/find_password")
	public String find_password(long tel_num, String email) {
		System.out.println(tel_num);
		System.out.println(email);
		return "views/sign/find_password_after";
	}
	
}