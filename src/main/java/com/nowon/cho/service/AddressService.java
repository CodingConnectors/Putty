package com.nowon.cho.service;

import org.springframework.security.core.Authentication;

import com.nowon.cho.domain.dto.AddressDTO;

public interface AddressService {


	void saveAddress(Authentication auth, AddressDTO dto);

}
