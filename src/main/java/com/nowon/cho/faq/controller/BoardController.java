package com.nowon.cho.faq.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String boardWritePro(Board board, Model model, MultipartFile file) throws Exception{

       boardService.write(board, file);
       
       model.addAttribute("message","글 작성이 완료되었습니다.");
       model.addAttribute("searchUrl","/member/faq_board" );

        return "faq/message";
    }
    
	@GetMapping("/member/faq_board")
	public String faq_board(Model model,String searchKeyword,@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "3") int limit) {	
		boardService.boardList(model,searchKeyword,page, limit);
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
	public String boardUpdate(@PathVariable("id") Integer id, Board board, Model model, MultipartFile file) throws Exception {
		
		Board boardTemp = boardService.boardView(id);
		boardTemp.setTitle(board.getTitle());
		boardTemp.setContent(board.getContent());
		
		boardService.write(boardTemp, file);
		
		model.addAttribute("message","글 수정이 완료되었습니다.");
	    model.addAttribute("searchUrl","/member/faq_board" );

	    return "faq/message";
		
		
	}
    
}