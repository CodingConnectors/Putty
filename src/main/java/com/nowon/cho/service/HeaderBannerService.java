package com.nowon.cho.service;

import java.util.List;

import com.nowon.cho.domain.dto.HeaderBannerDTO;

public interface HeaderBannerService {

	void save(HeaderBannerDTO headerBannerDTO);

	List<String> find();

}
