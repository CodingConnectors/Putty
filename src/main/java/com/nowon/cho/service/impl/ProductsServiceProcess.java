package com.nowon.cho.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.nowon.cho.domain.dto.admin.ProductsDTO;
import com.nowon.cho.domain.dto.admin.ProductsImgDTO;
import com.nowon.cho.domain.entity.products.ProductsEntity;
import com.nowon.cho.domain.entity.products.ProductsEntityRepository;
import com.nowon.cho.domain.entity.products.ProductsImgEntity;
import com.nowon.cho.domain.entity.products.ProductsImgEntityRepository;
import com.nowon.cho.service.ProductsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsServiceProcess implements ProductsService {
	
	private final AmazonS3Client amazonS3Client;
	private final ProductsEntityRepository productsEntityRepository;
	private final ProductsImgEntityRepository productsImgEntityRepository;
	
	@Value("${cloud.aws.s3.bucket}")
	private String BUCKET_NAME;
	
	@Value("${cloud.aws.s3.upload-temp}")
	private String TEMP_PATH;
	
	@Value("${cloud.aws.s3.upload-path}")
	private String UPLOAD_PATH;

	@Override
	public Map<String, String> tempUpload(MultipartFile multipartFile) {
		String orgName = multipartFile.getOriginalFilename();
		String bucketKey = TEMP_PATH + orgName;
		
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(multipartFile.getSize());
		objectMetadata.setContentType(multipartFile.getContentType());
		
		PutObjectRequest putObjectRequest = null;
		
		try {
			putObjectRequest = new PutObjectRequest(BUCKET_NAME, bucketKey, multipartFile.getInputStream(), objectMetadata);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		amazonS3Client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead));
		
		Map<String, String> productImg = new HashMap<>();
		productImg.put("url", amazonS3Client.getUrl(BUCKET_NAME, bucketKey).toString().substring(6));
		productImg.put("tempKey", bucketKey);
		productImg.put("orgName", orgName);
		
		return productImg;
	}

	@Override
	public void productSave(ProductsDTO productsDTO, ProductsImgDTO productsImgDTO) {
		ProductsEntity productsEntity = productsEntityRepository.save(productsDTO.toProductsEntity());
		
		for (int i = 0; i < productsImgDTO.getOrgName().length; i++) {
			if (!productsImgDTO.getOrgName()[i].equals("")) {
				String bucketKey = tempToProductImg(productsImgDTO, i);
				String imgNewName = UUID.randomUUID().toString() + productsImgDTO.getOrgName()[i].substring(productsImgDTO.getOrgName()[i].lastIndexOf("."));
				
				productsImgEntityRepository.save(ProductsImgEntity.builder()
						.imgOrgName(productsImgDTO.getOrgName()[i])
						.imgNewName(imgNewName)
						.bucketKey(bucketKey)
						.imgSize(0)
						.imgType(productsImgDTO.getImgType()[i])
						.productsEntity(productsEntity)
						.build());
			}
		}
	}

	private String tempToProductImg(ProductsImgDTO productsImgDTO, int i) {
		String bucketKey = UPLOAD_PATH
				+ UUID.randomUUID().toString()
				+ productsImgDTO.getOrgName()[i].substring(productsImgDTO.getOrgName()[i].lastIndexOf("."));
		
		CopyObjectRequest copyObjectRequest = new CopyObjectRequest(BUCKET_NAME, productsImgDTO.getTempKey()[i], BUCKET_NAME, bucketKey);
		
		amazonS3Client.copyObject(copyObjectRequest.withCannedAccessControlList(CannedAccessControlList.PublicRead));
		amazonS3Client.deleteObject(BUCKET_NAME, productsImgDTO.getTempKey()[i]);
		
		return bucketKey;
	}

	@Override
	public void findProducts(Model model) {
		model.addAttribute("productList", productsEntityRepository.findAll(Sort.by(Sort.Order.desc("createdDate"))).stream()
				.map(ProductsEntity :: toProductListDTO)
				.collect(Collectors.toList()));
	}

	@Override
	public void bestProducts(Model model) {
		List<ProductsEntity> bestProducts = productsEntityRepository.findAll(Sort.by(Sort.Order.desc("saleSum")));
		
		model.addAttribute("bestProducts", bestProducts.stream()
				.map(ProductsEntity :: toProductListDTO)
				.collect(Collectors.toList()));
	}
}