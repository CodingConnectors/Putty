package com.nowon.cho.faq.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowon.cho.domain.dto.MemberDTO;
import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.domain.entity.MemberRepository;

@Service
public class MemberService {
	
	 private final MemberRepository memberRepository;

	    @Autowired
	    public MemberService(MemberRepository memberRepository) {
	        this.memberRepository = memberRepository;
	    }

	    public MemberDTO getMemberById(long memberId) {
	        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(memberId);
	        return memberEntityOptional.map(this::mapToMemberDTO).orElse(null);
	    }

	    private MemberDTO mapToMemberDTO(MemberEntity memberEntity) {
	        MemberDTO memberDTO = new MemberDTO();
	        memberDTO.setMember_no(memberEntity.getMember_no());
	        memberDTO.setEmail(memberEntity.getEmail());
	        memberDTO.setName(memberEntity.getName());
	        memberDTO.setTel_num(memberEntity.getTel_num());
	        memberDTO.setCreatedDate(memberEntity.getCreatedDate());
	        memberDTO.setUpdatedDate(memberEntity.getUpdatedDate());
	        return memberDTO;
	    }
	
}
