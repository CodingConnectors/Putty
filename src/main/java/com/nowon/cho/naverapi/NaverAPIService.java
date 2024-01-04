package com.nowon.cho.naverapi;




import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowon.cho.naverapi.request.DeptSaveDTO;
import com.nowon.cho.naverapi.response.Element;
import com.nowon.cho.naverapi.response.ResponseDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NaverAPIService {
	
	private final OpenApiUtil naverApi;
	
	@Value("${naver.ncp.access-key}")
	private String accessKey;
	@Value("${naver.ncp.secret-key}")
	private String secretKey;
	@Value("${naver.ncp.company-id}")
	private String companyId;
	
	/**
	 * 
	 * @param method  		"GET","POST"
	 * @param url 			호스트주소빼고 나머지만 ex) "/organization/apigw/v2/company/"+companyId+"/department";
	 * @param timestamp     Long.toString(System.currentTimeMillis())
	 * @return
	 */
	private String makeSignature(String method,String url,String timestamp){
		String space = " ";					// one space
		String newLine = "\n";					// new line
		//String method = _method;					// method
		//String url = _url;	// url (include query string)
		//String timestamp = _timestamp; 			// current timestamp (epoch)
		//String accessKey = accessKey;			// access key id (from portal or Sub Account)
		//String secretKey = secretKey;
		//System.out.println("url:"+url);
		String message = new StringBuilder()
			.append(method)
			.append(space)
			.append(url)
			.append(newLine)
			.append(timestamp)
			.append(newLine)
			.append(accessKey)
			.toString();
		
		SecretKeySpec signingKey;
		String encodeBase64String=null;
		try {
			signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);

			byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
			encodeBase64String = Base64.encodeBase64String(rawHmac);
			//encodeBase64String = java.util.Base64.getEncoder().encodeToString(rawHmac);
			//System.out.println("encodeBase64String1:"+encodeBase64String1);
			//System.out.println("encodeBase64String:"+encodeBase64String);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	  return encodeBase64String;
	}

	//requestHeaders 생성하는 메서드
	private Map<String, String> createNaverWorkspaceRequestHeaders(String method, String url) {
		String timestamp=Long.toString(System.currentTimeMillis()); 
		
		//signature 생성하는 메서드
		String signature=makeSignature(method, url, timestamp);
		System.out.println(signature);
		//요청할떄 필요한 헤더정보
		Map<String, String> requestHeaders=new HashMap<>();
		requestHeaders.put("x-ncp-apigw-timestamp", timestamp);
		requestHeaders.put("x-ncp-iam-access-key", accessKey);
		requestHeaders.put("x-ncp-apigw-signature-v2", signature);
		
		return requestHeaders;
		
	}
	
	
	public void deptList(Model model) {
		String method="GET";
		String url="/organization/apigw/v2/company/"+companyId+"/department";
		String host="https://workplace.apigw.ntruss.com";
		
		String jsonFormatedString=naverApi.request(host+url, createNaverWorkspaceRequestHeaders(method,url), method, null);
		//System.out.println(">>>:"+jsonFormatedString);
		
		ObjectMapper objectMapper=new ObjectMapper();
		ResponseDTO result=null;
		try {
			result=objectMapper.readValue(jsonFormatedString, ResponseDTO.class);
		} catch (Exception e) {
			System.out.println(">>> jsonToJava mapping error!!");
		}
		List<Element> elements=result.getElements().stream()
		        .sorted(Comparator.comparing(Element::getExternalKey))
		        .collect(Collectors.toList());
		
		model.addAttribute("list", elements);
		
	}
	

	public void saveDept(DeptSaveDTO dto) {
		String method="POST";
		String url="/organization/apigw/v2/company/"+companyId+"/department/"+dto.getDeptExternalKey();
		String host="https://workplace.apigw.ntruss.com";
		
		ObjectMapper mapper=new ObjectMapper();
		String body=null;
		try {
			body=mapper.writeValueAsString(dto);
			System.out.println("JSON>>>:"+body);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Map<String, String> requestHeaders=createNaverWorkspaceRequestHeaders(method,url);
		//body:JSON data 추가시 header에 추가
		requestHeaders.put("Content-Type", "application/json");
		
		String jsonFormatedString=naverApi.request(host+url, requestHeaders, method, body);
		System.out.println("<<<<<:"+jsonFormatedString);
	}

}
