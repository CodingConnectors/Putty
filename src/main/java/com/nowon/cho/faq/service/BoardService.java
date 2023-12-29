package com.nowon.cho.faq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowon.cho.faq.entity.Board;
import com.nowon.cho.faq.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	
	//글 작성
	public void write(Board board) {
		
		boardRepository.save(board);
		
	}
	
	
	//리스트 메소드 생성 //게시글 리스트 처리
	public List<Board> boardList(){
		
		return boardRepository.findAll();
		
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
