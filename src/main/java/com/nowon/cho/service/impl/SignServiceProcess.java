package com.nowon.cho.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nowon.cho.domain.dto.MemberDTO;
import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.domain.entity.MemberRepository;
import com.nowon.cho.security.MemberRole;
import com.nowon.cho.service.SignService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class SignServiceProcess implements SignService {

	@Autowired
	private final MemberRepository memberRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public void saveMember(MemberDTO dto) {
		memberRepository.save(dto.toMemberEntity(passwordEncoder).addRole(MemberRole.USER));
	}
	
	public MemberEntity findByEmail(String email) {
		return memberRepository.findByEmail(email);
	}

	@Override
	public MemberEntity findByEmailAndPassword(String email, String password) {
		return memberRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public boolean existsByEmail(String email) {
		return memberRepository.existsByEmail(email);
	}

	@Override
	public MemberEntity getMemberByMemberNo(String memberNo) {
		return null;
	}
	
}