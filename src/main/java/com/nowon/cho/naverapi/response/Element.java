package com.nowon.cho.naverapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true) //json 속성을 모두 매핑하려고하는데 만약에 없으면  무시하세요
@Getter
public class Element {
	
	//private String tenantId;                // "0b8e2fc5-79b0-406a-9ea2-d29fc8bb4b0a",
    //private String companyId;                // "0b8e2fc5-79b0-406a-9ea2-d29fc8bb4b0a",
    private String externalKey;                // "dept0000",
    private String name;                // "테스트회사",
    //private Object i18nNames;                // {},
    //private String parentDeptExternalKey;                // null,
    //private String deptLeaderExternalKey;                // null,
    //private String deptEmailAddress;                // "my00010277@mymytesttest.by-works.com",
    //private String externalEmailReceiveYn;                // false,
    private String dispOrd;                // "1.0",
    private String deptNo;                // "0b8e2fc5-79b0-406a-9ea2-d29fc8bb4b0a",
    private String parentDeptNo;                // "#",
    private String deptLeaderEmpId;                // null

}
