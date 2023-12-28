package com.nowon.cho.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowon.cho.domain.dto.MemberDTO;
import com.nowon.cho.domain.entity.MemberRepository;
import com.nowon.cho.service.SignService;


@Service
public class SignServiceProcess implements SignService {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public void saveMember(MemberDTO dto) {
		memberRepository.save(dto.toMemberEntity());
	}
}