package com.nowon.cho.domain.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.nowon.cho.security.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SequenceGenerator(name = "gen_seq_mem", 
		sequenceName = "seq_mem", initialValue = 1, allocationSize = 1)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "Members")
public class MemberEntity extends BaseEntity {
	
	@Id
	@GeneratedValue(generator = "gen_seq_mem", strategy = GenerationType.SEQUENCE)
	private long memberNo;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String telNum;
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp(6) null" , nullable = false)
	private LocalDateTime createdDate;
	@UpdateTimestamp
	@Column(columnDefinition = "timestamp(6) null")
	private LocalDateTime updatedDate;
	
	
	@Column(columnDefinition = "CHAR(1) null default 'N'")
	private Character cancel;
	
	//role
	@Builder.Default
	//@Enumerated 선언하지 않으면 ordinal(숫자)로 저장됨
	@Enumerated(EnumType.STRING)//DB에 저장유형을 문자로저장
	@CollectionTable(name = "role")
	@ElementCollection(fetch = FetchType.EAGER)//1:N MemberEntity에서만 접근가능한 내장테이블
	private Set<MemberRole> memberRoles= new HashSet<>();
	
	
	//편의메서드
	public MemberEntity addRole(MemberRole role) {
		memberRoles.add(role);
		return this;
	}
	
}
