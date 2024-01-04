package com.nowon.cho.service;

import com.nowon.cho.domain.dto.MemberDTO;
import com.nowon.cho.domain.entity.MemberEntity;

public interface SignService {

	void saveMember(MemberDTO dto);

	MemberEntity findByEmailAndPassword(String email, String password);

	boolean existsByEmail(String email);

}
