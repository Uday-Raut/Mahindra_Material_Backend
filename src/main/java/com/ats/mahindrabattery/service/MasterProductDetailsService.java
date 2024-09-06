package com.ats.mahindrabattery.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ats.mahindrabattery.entity.MasterProductDetailsEntity;
import com.ats.mahindrabattery.entity.MasterProductVariantDetailsEntity;

public interface MasterProductDetailsService {


	public MasterProductDetailsEntity findMasterProductDetailsById(Integer id);
	

	public List<MasterProductDetailsEntity> getAllMasterProductDetails(
			);
	
	public ResponseEntity<Object> createmasterMasterProductDetails(
			MasterProductDetailsEntity masterProductDetailsEntity);
	
	
	
	
	public ResponseEntity<Object> updateMasterProductDetails(
			MasterProductDetailsEntity masterProductDetailsEntity) ;
	
	
	
}
