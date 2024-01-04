package com.nowon.cho.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
	
	// 이미 존재하는 이메일인지 확인
    boolean existsByEmail(String email);

	MemberEntity findByEmail(String email);

	MemberEntity findByEmailAndPassword(String email, String password);
	
}