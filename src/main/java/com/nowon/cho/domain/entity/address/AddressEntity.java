package com.nowon.cho.domain.entity.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nowon.cho.domain.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "Address")
public class AddressEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addrNo;
	
	@ManyToOne
    @JoinColumn(name = "memberNo", nullable = false)
    private MemberEntity member;
	
	
	
	@Column(name = "postCode", nullable = false)
    private String postCode;
    @Column(name = "roadAddress")
    private String roadAddress;
    @Column(name = "jibunAddress")
    private String jibunAddress;
    @Column(name = "addressDetail")
    private String addressDetail;
    @Column(name = "addressReference")
    private String addressReference;
    
}
