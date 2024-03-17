package com.nowon.cho.service.impl;

import java.util.List;
import java.util.UUID;import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.nowon.cho.domain.dto.HeaderBannerDTO;
import com.nowon.cho.domain.entity.HeaderBannerEntity;
import com.nowon.cho.domain.entity.HeaderBannerRepository;
import com.nowon.cho.service.HeaderBannerService;

@Service
public class HeaderBannerProcess implements HeaderBannerService {

	@Autowired
	HeaderBannerRepository hbRepo;
	@Autowired
	AmazonS3Client client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	@Value("${cloud.aws.s3.upload-path}")
	private String uploadPath;
	
	@Override
	public void save(HeaderBannerDTO headerBannerDTO) {
		for(int i=0; i<headerBannerDTO.getOrgName().length; i++) {
			if(!headerBannerDTO.getOrgName()[i].equals("")) {
				String uploadKey=s3FromTempToImages(headerBannerDTO, i);
				hbRepo.save(HeaderBannerEntity.builder()
						.bucketKey(uploadKey)
						.orgName(headerBannerDTO.getOrgName()[i])
						.build());
			}
		}
	}

	private String s3FromTempToImages(HeaderBannerDTO headerBannerDTO, int i) {
		String uploadKey = uploadPath
				+ UUID.randomUUID().toString()
				+ headerBannerDTO.getOrgName()[i].substring(headerBannerDTO.getOrgName()[i].lastIndexOf("."));


		CopyObjectRequest cor=new CopyObjectRequest(bucketName, headerBannerDTO.getTempKey()[i], bucketName, uploadKey);
		client.copyObject(cor.withCannedAccessControlList(CannedAccessControlList.PublicRead));
		client.deleteObject(bucketName,headerBannerDTO.getTempKey()[i]);
		return uploadKey;
	}

	@Override
	public List<String> find() {
		return hbRepo.findAll().stream().map(headerBannerEn->client.getUrl(bucketName, headerBannerEn.getBucketKey()).toString())
				.collect(Collectors.toList());
	}

}
