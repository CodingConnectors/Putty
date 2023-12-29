package com.nowon.cho.faq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nowon.cho.faq.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{

}
