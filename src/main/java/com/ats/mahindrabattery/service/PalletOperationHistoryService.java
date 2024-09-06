package com.ats.mahindrabattery.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ats.mahindrabattery.entity.PalletOperationHistoryEntity;

public interface PalletOperationHistoryService {

	public List<PalletOperationHistoryEntity> fetchAllPalletOperationHistory(
			PalletOperationHistoryEntity palletOperationHistoryEntity);
	
	public List<PalletOperationHistoryEntity> findByloadDateTimeAndOperationInfeedMissionDetails();
	
	public List<PalletOperationHistoryEntity> findByloadDateTimeAndOperationOutfeedMissionDetails();
	
	public List<PalletOperationHistoryEntity> findBetweenStartDateandEndDate(String startDateTime, String endDateTime);
	
	public List<PalletOperationHistoryEntity> findByproductVariantCode(String productVariantCode);
	
	public List<PalletOperationHistoryEntity> findByAllFilters(String startDate, String endDate,
			String productVariantCode, String areaName, String floorName, String operation);
	
	public List<PalletOperationHistoryEntity> findByOperation(String operation);
	
	public List<PalletOperationHistoryEntity> findAll() ;
	
	public ResponseEntity<PalletOperationHistoryEntity> updatePalletOperationHistory(
			PalletOperationHistoryEntity palletOperationHistoryEntity, String palletCode, String productVariantCode);
	
	public PalletOperationHistoryEntity addManualOutfeedMissionDetails(
			PalletOperationHistoryEntity palletOperationHistoryEntity);
	
	public ResponseEntity<String> addCurrentPalletStockDataInPalletOperationHistoryDetails(
			PalletOperationHistoryEntity palletOperationHistoryEntity);
	
	public List<PalletOperationHistoryEntity> findByWmsTransferOrderIdOrderByPalletRetrievalHistoryIdDesc(
			String wmsTransferOrderId);
	
	
}
