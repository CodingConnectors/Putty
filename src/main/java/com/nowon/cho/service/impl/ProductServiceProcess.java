package com.nowon.cho.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.nowon.cho.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceProcess implements ProductService {


	private final AmazonS3Client client;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	@Value("${cloud.aws.s3.upload-path}")
	private String uploadPath;
	@Value("${cloud.aws.s3.upload-temp}")
	private String tempPath;
	
	@Override
	public Map<String, String> tempUpload(MultipartFile productImg) {
		
		String orgName = productImg.getOriginalFilename();
		String bucketKey = tempPath+orgName;
		
		ObjectMetadata om = new ObjectMetadata();
		om.setContentLength(productImg.getSize());
		om.setContentType(productImg.getContentType());
		
		PutObjectRequest por = null;
		try {
			por = new PutObjectRequest(bucketName, bucketKey, productImg.getInputStream(), om);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		client.putObject(por.withCannedAcl(CannedAccessControlList.PublicRead));
		
		Map<String, String> map = new HashMap<>();
		map.put("url", client.getUrl(bucketName, bucketKey).toString().substring(6));
		map.put("tempKey", bucketKey);
		map.put("orgName", orgName);
		return map;
	}

	@Override
	public void findAll(Model model) {
//		model.addAttribute("list",productRepo.findAll().stream().map(ProductEntity::toListDTO).collect(Collectors.toList()));
	}
}
