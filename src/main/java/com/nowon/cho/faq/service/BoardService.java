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
	
	public void write(Board board) {
		
		boardRepository.save(board);
		
	}
	
	
	//리스트 메소드 생성
	public List<Board> boardList(){
		
		return boardRepository.findAll();
		
	}
	
	

}
