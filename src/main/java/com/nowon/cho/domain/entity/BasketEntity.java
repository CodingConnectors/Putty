package com.nowon.cho.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nowon.cho.domain.entity.compisitekey.BasketEntityId;
import com.nowon.cho.domain.entity.products.ProductsEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Basket")
@IdClass(BasketEntityId.class)
public class BasketEntity {
	@Id
	@ManyToOne
	@JoinColumn(name = "productNo")
	private ProductsEntity productNo;
	@Id
	@ManyToOne
	@JoinColumn(name = "member_no")
	private MemberEntity member_no;
	
	private long volume;
}
