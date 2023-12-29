package com.nowon.cho.faq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.cho.faq.entity.Board;
import com.nowon.cho.faq.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

    @GetMapping("/board/write") //localhost:8080/board/write
    public String boardWriteForm() {	

        return "faq/boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board){

       boardService.write(board);

        return "";
    }
    
	@GetMapping("/faq/faq_board")
	public String faq_board(Model model) {	
		
		//리스트
		model.addAttribute( "list", boardService.boardList());
		
		
		
		return "faq/faq_board";
	}
	
	@GetMapping("/board/view") //localhost:8080/board/view?id=1
	public String boardView(Model model, Integer id) {
		
		model.addAttribute("board",boardService.boardView(id));
		
		return "faq/boardview";
		
	}
	
	
    
    
}