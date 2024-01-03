package com.nowon.cho.faq.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.cho.faq.entity.Board;
import com.nowon.cho.faq.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	
	//글 작성
	public void write(Board board, MultipartFile file) throws Exception{
		
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
		
		UUID uuid = UUID.randomUUID();
		
		
		String fileName = uuid + "_" + file.getOriginalFilename();
		
		
		File saveFile = new File(projectPath, fileName);
	
		file.transferTo(saveFile);
		
		board.setFilename(fileName);
		board.setFilepath("/files/" + fileName);
		
		boardRepository.save(board);
		
	}
	
	
	//리스트 메소드 생성 //게시글 리스트 처리
	public Page<Board> boardList(Pageable pageable){
		
		return boardRepository.findAll(pageable);
		
	}
	
	public Page<Board> boardSearchList(String SearchKeyword, Pageable pageable){
		
		return boardRepository.findByTitleContaining(SearchKeyword, pageable);
	}
	
	
	// 특정 게시글 불러오기
	public Board boardView(Integer id) {
		
		return boardRepository.findById(id).get();
		
	}
	
	// 특정 게시글 삭제
	public void boardDelete(Integer id) {
		
		boardRepository.deleteById(id);
		
	}
	
	

}
