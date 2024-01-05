package com.nowon.cho.domain.dto;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.nowon.cho.domain.entity.MemberEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberDTO {
	
	private long memberNo;
	
	private String email;
	
	private String password;
	
	private String name;
	
	private String telNum;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime updatedDate;

	public MemberEntity toMemberEntity(PasswordEncoder passwordEncoder) {
		return MemberEntity.builder()
				.memberNo(memberNo)
				.email(email)
				.password(passwordEncoder.encode(password))
				.name(name)
				.telNum(telNum)
				.createdDate(createdDate)
				.updatedDate(updatedDate)
				.build();
	}
	
}
