package com.nowon.cho.domain.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FindProductListDTO {

	private long no;
	private String name;
	private long price;
	private int stock;
	private String content;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}
