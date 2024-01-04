package com.nowon.cho.security;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.Column;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.nowon.cho.domain.entity.MemberEntity;

import lombok.Getter;

@Getter
public class MyUserDetails extends User{
	
	private static final long serialVersionUID = 1L;
	private long memberNo;
	private String name;
	private String telNum;

	private MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public MyUserDetails(MemberEntity member) {
		this(member.getEmail(), member.getPassword(), member.getMemberRoles().stream()
				.map((myRole) -> new SimpleGrantedAuthority("ROLE_"+myRole.name()))
				.collect(Collectors.toSet()));
		memberNo=member.getMemberNo();
		name=member.getName();
		telNum=member.getTelNum();
	}

}
