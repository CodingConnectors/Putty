package com.nowon.cho.naverapi.response;

import java.util.List;

import lombok.Getter;

@Getter
public class ResponseDTO {
	private List<Element> elements;
	private int totalCount;
}
