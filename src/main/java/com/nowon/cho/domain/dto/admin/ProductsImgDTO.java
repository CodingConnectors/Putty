package com.nowon.cho.domain.dto.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ProductsImgDTO {
	
	private String[] tempKey;
	private String[] orgName;
	private long[] size;
	private boolean[] imgType;
}