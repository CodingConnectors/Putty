package com.nowon.cho.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.nowon.cho.domain.dto.AddressDTO;
import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.domain.entity.MemberRepository;
import com.nowon.cho.domain.entity.address.AddressRepository;
import com.nowon.cho.service.AddressService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AddressServiceProcess implements AddressService {
	
	private final AddressRepository addressRepository;
	private final MemberRepository memberRepository;

	@Override
	public void saveAddress(Authentication auth, AddressDTO dto) {
		MemberEntity member= memberRepository.findByEmail(auth.getName());
		addressRepository.save(dto.toAddressEntity(member));
		
	}
	
	

}
