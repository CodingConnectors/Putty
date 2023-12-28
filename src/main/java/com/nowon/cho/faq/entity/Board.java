package com.nowon.cho.faq.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
 //   private int member_no;
    
   
    
 //   private String password;

    private String title;

    private String content;
    
//    private int viewcount;
    
   // private LocalDateTime createdDate;
    
 //   private LocalDateTime updatedDate;
    
    
}