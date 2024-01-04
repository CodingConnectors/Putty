package com.nowon.cho.naverapi.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class DeptSaveDTO{
	
	//@JsonIgnore //json으로 변환시 무시됨
	private String deptExternalKey;                // "dept0000",
    private String name;                // "테스트회사",
    private String parentDeptExternalKey;                // null,
    private String dispOrd;                // "1.0",

}
