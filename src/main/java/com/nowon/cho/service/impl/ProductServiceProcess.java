package com.nowon.cho.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.nowon.cho.domain.dto.ProductDTO;
import com.nowon.cho.domain.dto.ProductImgDTO;
import com.nowon.cho.domain.entity.ProductEntity;
import com.nowon.cho.domain.entity.ProductEntityRepository;
import com.nowon.cho.domain.entity.ProductImgEntity;
import com.nowon.cho.domain.entity.ProductImgRepository;
import com.nowon.cho.service.ProductService;

@Service
public class ProductServiceProcess implements ProductService {


	@Autowired
	private AmazonS3Client client;
	@Autowired
	ProductEntityRepository productRepo;
	@Autowired
	ProductImgRepository productImgRepo;
	
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
	public void save(ProductDTO productDTO, ProductImgDTO productImgDTO) {
		ProductEntity productEn = productRepo.save(productDTO.toEntity());
		
		for(int i=0; i<productImgDTO.getOrgName().length; i++) {
			if(!productImgDTO.getOrgName()[i].equals("")) {
				String uploadKey=s3FromTempToImages(productImgDTO, i);
				productImgRepo.save(ProductImgEntity.builder()
										.bucketKey(uploadKey)
										.orgName(productImgDTO.getOrgName()[i])
										.productEn(productEn)
										.build());
			}
		}
	}
	private String s3FromTempToImages(ProductImgDTO productImgDTO, int i) {
		String uploadKey = uploadPath
							+ UUID.randomUUID().toString()
							+ productImgDTO.getOrgName()[i].substring(productImgDTO.getOrgName()[i].lastIndexOf("."));
		
		
		CopyObjectRequest cor=new CopyObjectRequest(bucketName, productImgDTO.getTempKey()[i], bucketName, uploadKey);
		client.copyObject(cor.withCannedAccessControlList(CannedAccessControlList.PublicRead));
		client.deleteObject(bucketName,productImgDTO.getTempKey()[i]);
		return uploadKey;
	}

	@Override
	public void findAll(Model model) {
		model.addAttribute("list",productRepo.findAll().stream().map(ProductEntity::toListDTO).collect(Collectors.toList()));
	}
}
