package com.nowon.cho.domain.entity.payment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nowon.cho.domain.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "orders")
@Entity
public class OrdersEntity extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long order_no;
	@Column(nullable = false)
	private long order_regular;	// 상품총가격
	private long order_discount;	// 할인되는 가격
	@Column(nullable = false)
	private long order_total_price;	// 할인 후 가격
	private long order_points;	//적립포인트
	@Column(nullable = false)
	private String order_address;	// 주소
	@Column(nullable = false)
	private String order_address_detail;	// 상세주소
	@Column(nullable = false)
	private String order_zip_code;	//우편번호
	@Column(nullable = false)
	private String order_tel;	//전화번호
	private String order_req;	//요청사항
	@Column(nullable = false)
	private String order_name;	//받는사람 성함
	private long member_no;
}
