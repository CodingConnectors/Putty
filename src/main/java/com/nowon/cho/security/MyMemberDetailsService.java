package com.nowon.cho.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.domain.entity.MemberRepository;



public class MyMemberDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println(">>>>>>>>>>>>>>>>>"+email);
		MemberEntity member = memberRepository.findByEmail(email);
		if(member==null) {
			System.out.println("존재하지 않는 회원입니다.");
		}else {
			System.out.println("존재하는 회원입니다.");
			// 콘솔에 member_no 출력
            System.out.println("Logged in user's member_no: " + member.getMemberNo());
		}
		
		/*
		 * DB에 해당하는 사용자가 있으면 사용자의 ROLE(권한)을 객체에 넣어 반환.
		 * Spring.io Security 를 참고하여 ROLE을 반환타입에 맞게 변환해준다.
		 */
		/*
		Set<SimpleGrantedAuthority> grnatedAuthority = member.getMemberRoles().stream()
				.map((myRole) -> new SimpleGrantedAuthority("ROLE_"+myRole.name()))
				.collect(Collectors.toSet());
		*/
		
		return new MyUserDetails(member);
	}
	
}
