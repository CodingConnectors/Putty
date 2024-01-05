package com.nowon.cho.domain.dto;


import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.domain.entity.address.AddressEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AddressDTO {
	
	   
	private String postCode;
    
	private String roadAddress;
    
	private String jibunAddress;
    
	private String addressDetail;
    
	private String addressReference;

    public AddressEntity toAddressEntity(MemberEntity member) {
		return AddressEntity.builder()
				.postCode(postCode)
				.roadAddress(roadAddress)
				.jibunAddress(jibunAddress)
				.addressDetail(addressDetail)
				.addressReference(addressReference)
				.member(member)
				.build();
    	
    }

}
