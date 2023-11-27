package com.nowon.cho.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table (name = "puttyTest")
@Entity
public class ProductEntity extends BaseEntity{

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long no;
	@Column (nullable = false)
	private String name;
	@Column (nullable = false)
	private long price;
	@Column (nullable = false)
	private int stock;
	private String content;
}
