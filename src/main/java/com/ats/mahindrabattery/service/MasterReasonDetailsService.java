package com.ats.mahindrabattery.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ats.mahindrabattery.entity.MasterReasonDetailsEntity;

public interface MasterReasonDetailsService {

	
	public ResponseEntity<Object> addMasterReasonDetails(MasterReasonDetailsEntity masterReasonDetailsEntity);
	
	public List<MasterReasonDetailsEntity> fetchAllMasterReasonDetails(
			MasterReasonDetailsEntity masterReasonDetailsEntity);
	
	public MasterReasonDetailsEntity findMasterReasonDetailsById(int id) ;
	
	public ResponseEntity<Object> updateMasterReasonDetails(int id,
			MasterReasonDetailsEntity masterReasonDetailsEntity);
	
	public ResponseEntity<Object>  deleteMasterReasonDetails(int id) ;
	
	
}
