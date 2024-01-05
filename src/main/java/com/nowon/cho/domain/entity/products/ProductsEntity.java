package com.nowon.cho.domain.entity.products;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nowon.cho.domain.dto.admin.FindProductsDTO;
import com.nowon.cho.domain.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@SequenceGenerator(name = "gen_products_seq", sequenceName = "products_seq", allocationSize = 1)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductsEntity extends BaseEntity {
	
	@Id
	@GeneratedValue(generator = "gen_products_seq", strategy = GenerationType.SEQUENCE)
	private long productNo;
	
	@Column(nullable = false)
	private String productName;
	
	@Column(nullable = false)
	private long price;
	
	private long saleDiscount;
	
	@Column(nullable = false)
	private long productStock;
	
	@Column(nullable = false)
	private String productCategory;
	
	@Lob
	private String productContent;
	
	private long saleSum;
	private long wishlistCnt;
	
	@OneToMany(mappedBy = "productsEntity")
	private List<ProductsImgEntity> imgs;
	
	public FindProductsDTO findProducts() {
		ProductsImgEntity mainImg = imgs.stream()
				.filter(img -> img.isImgType() == true)
				.findFirst()
				.get();
		
		return FindProductsDTO.builder()
				.productNo(productNo)
				.productName(productName)
				.price(price)
				.saleDiscount(saleDiscount)
				.mainImgUrl("https://0idealisawsbucket.s3.ap-northeast-2.amazonaws.com/" + mainImg.getBucketKey())
				.build();
	}
	
	public FindProductsDTO findProductsByCategory() {
		return null;
	}
}