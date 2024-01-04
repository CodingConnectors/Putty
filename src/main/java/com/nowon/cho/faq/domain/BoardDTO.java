package com.nowon.cho.faq.domain;

import java.time.LocalDateTime;


import com.nowon.cho.domain.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Builder
public class BoardDTO {
	
	private Integer id;
    private String filename;
    private String filepath;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String author; // 작성자 정보
	
}
