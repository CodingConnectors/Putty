package com.nowon.cho.faq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.cho.faq.entity.Board;
import com.nowon.cho.faq.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

    @GetMapping("/member/board/write") //localhost:8080/board/write
    public String boardWriteForm() {	

        return "faq/boardwrite";
    }

    @PostMapping("/member/board/write")
    public String boardWritePro(Board board){

       boardService.write(board);

        return "redirect:/member/faq_board";
    }
    
	@GetMapping("/member/faq_board")
	public String faq_board(Model model) {	
		
		//리스트
		model.addAttribute( "list", boardService.boardList());
		
		return "faq/faq_board";
	}
	
	@GetMapping("/member/board/view") //localhost:8080/board/view?id=1
	public String boardView(Model model, Integer id) {
		
		model.addAttribute("board",boardService.boardView(id));
		
		return "/faq/boardview";
		
	}
	
	@GetMapping("/member/board/delete")
	public String boardDelete(Integer id) {
		
		boardService.boardDelete(id);
		
		return "redirect:/member/faq_board";
	}
	
	@GetMapping("/member/board/modify/{id}")
	public String boardModify(@PathVariable("id") Integer id, Model model) {
		
		model.addAttribute("board",boardService.boardView(id));
		
		return "/faq/boardmodify";
		
	}
    
	@PostMapping("/member/board/update/{id}")
	public String boardUpdate(@PathVariable("id") Integer id, Board board) {
		
		Board boardTemp = boardService.boardView(id);
		boardTemp.setTitle(board.getTitle());
		boardTemp.setContent(board.getContent());
		
		boardService.write(boardTemp);
		
		return "redirect:/member/faq_board";
		
	}
    
}