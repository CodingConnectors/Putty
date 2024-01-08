package com.nowon.cho.domain.dto;

import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.faq.entity.Board;

import lombok.Setter;

@Setter
public class BoardSaveDTO {
	private String title;
    private String content;
    

	public Board toEntity(MemberEntity member, String fileName, String filePath) {
		return Board.builder()
    			.title(title)
    			.content(content)
    			.member(member)//작성자 역할을 하는 객체
    			.filename(fileName)
    			.filepath(filePath)
    			.build();
	}
}
