package com.nowon.cho.faq.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.nowon.cho.domain.dto.admin.FindProductsDTO;
import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.domain.entity.products.ProductsImgEntity;
import com.nowon.cho.faq.domain.BoardDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    
    //private int member_no;
    
    private String filename;
    private String filepath;
    //   private String password;
    private String title;
    private String content;
    
    // 	 private int viewcount;
    @CreationTimestamp
    @Column(columnDefinition = "timestamp(6) null")
    private LocalDateTime createdDate;
    
    @UpdateTimestamp
    @Column(columnDefinition = "timestamp(6) null")
    private LocalDateTime updatedDate;
    
    @ManyToOne
    @JoinColumn(name = "member_no")
    private MemberEntity member; //작성자-회원(회원번호,이메일,이름)
    
    

  

  
    
    
    //엔티티->BoardDTO로 매핑하기위한 편의메서드
    public BoardDTO toBoardDTO() {
    	return BoardDTO.builder()
    			.id(id).filename(filename).filepath(filepath).title(title).content(content)
    			.authorEmail(member!=null?member.getEmail():"테스트") // authorEmail 필드를 사용하도록 수정
    			.authorName(member!=null?member.getName():"테스트")
    			.build();
    }



    //수정 제목, 글

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
 
    //수정 파일
    
    private String fileUuid; // 파일의 UUID
    private String originalFileName; // 원래 파일 이름
    
    public void setFileUuid(String fileUuid) {
        this.fileUuid = fileUuid;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }
    
   
    
}