package com.nowon.cho.faq.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.faq.domain.BoardDTO;

import lombok.Data;

@Entity
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
 //   private int member_no;
    private String filename;
    private String filepath;
 //   private String password;
    private String title;
    private String content;
    
//    private int viewcount;
    
   // private LocalDateTime createdDate;
    
 //   private LocalDateTime updatedDate;
    
    @ManyToOne
    @JoinColumn(name = "member_no")
    private MemberEntity member;
    
    
    //엔티티->BoardDTO로 매핑하기위한 편의메서드
    public BoardDTO toBoardDTO() {
    	return BoardDTO.builder()
    			.id(id).filename(filename).filepath(filepath).title(title).content(content)
    			.author(member!=null?member.getName():"테스트")//작성자
    			.build();
    }
    
    
}