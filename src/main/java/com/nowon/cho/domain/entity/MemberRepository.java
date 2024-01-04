package com.nowon.cho.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{

	MemberEntity findByEmail(String email);

	MemberEntity findByEmailAndPassword(String email, String password);
	
}