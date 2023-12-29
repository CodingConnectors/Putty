package com.nowon.cho.domain.entity.compisitekey;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BasketEntityId implements Serializable {
	private long member_no;
	private long productNo;
	
	// 생성자, getter 등 생략

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BasketEntityId other = (BasketEntityId) obj;
        return member_no == other.member_no && productNo == other.productNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(member_no, productNo);
    }
}
