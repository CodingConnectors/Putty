package com.nowon.cho.faq.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.cho.domain.dto.BoardSaveDTO;
import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.faq.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{

	Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
	
	
}
