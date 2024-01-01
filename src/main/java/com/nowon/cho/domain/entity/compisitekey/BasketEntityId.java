package com.nowon.cho.domain.entity.compisitekey;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasketEntityId implements Serializable {
	private long memberNo;
	private long productNo;
	
	// 생성자, getter 등 생략

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BasketEntityId other = (BasketEntityId) obj;
        return memberNo == other.memberNo && productNo == other.productNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberNo, productNo);
    }
}
