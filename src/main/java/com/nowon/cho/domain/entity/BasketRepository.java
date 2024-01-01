package com.nowon.cho.domain.entity;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowon.cho.domain.entity.compisitekey.BasketEntityId;

public interface BasketRepository extends JpaRepository<BasketEntity, BasketEntityId>{

	List<BasketEntity> findByMemberNo(MemberEntity memberNo);
}
