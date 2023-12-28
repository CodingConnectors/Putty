package com.nowon.cho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
/*@RequiredArgsConstructor*/
public class MemberController {

    /*@Autowired
    private MemberRepository memberRepository;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;*/

	@GetMapping("/members")
	public String MyPage() {
	/*(Principal principal, ModelMap modelMap){
        String loginId = principal.getName();
        Member member = memberRepository.findByEmail(loginId);
        modelMap.addAttribute("member", member);*/
		return "views/member/mypage";
	}
	
	@GetMapping("/members_data")
	public String Members_data() {
		
		return"views/member/members_data";
	}
	
}
