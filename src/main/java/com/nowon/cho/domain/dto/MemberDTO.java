package com.nowon.cho.domain.dto;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nowon.cho.domain.entity.MemberEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberDTO {
	
	private long member_no;
	
	private String email;
	
	private String password;
	
	private String name;
	
	private String tel_num;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime updatedDate;

	public MemberEntity toMemberEntity(PasswordEncoder passwordEncoder) {
		return MemberEntity.builder()
				.member_no(member_no)
				.email(email)
				.password(passwordEncoder.encode(password))
				.name(name)
				.tel_num(tel_num)
				.createdDate(createdDate)
				.updatedDate(updatedDate)
				.build();
	}
	
}
