package com.ats.mahindrabattery.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.ats.mahindrabattery.entity.MasterProductVariantDetailsEntity;

public interface MasterProductVariantDetailsService {

	public ResponseEntity<Object> createmasterMasterProductVariantDetails(
			MasterProductVariantDetailsEntity masterProductVariantDetailsEntity);

	public List<MasterProductVariantDetailsEntity> getAllMasterProductVariantDetails();

	public MasterProductVariantDetailsEntity findMasterProductVariantDetailsById(int id);

	public ResponseEntity<Object> updateMasterProductVariantDetails(
			MasterProductVariantDetailsEntity masterProductVariantDetailsEntity);
}
