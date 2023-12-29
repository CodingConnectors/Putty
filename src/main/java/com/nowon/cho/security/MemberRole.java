package com.nowon.cho.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
	USER("일반유저"),//0
	ADMIN("관리자");//1
	private final String roleName;
}
