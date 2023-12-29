package com.nowon.cho.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PutBasketDTO {

	private long member;
	private long product;
	private long volume;
	
	public void setVolume() {
        this.volume = this.volume<=0?1:this.volume;
    }
}
