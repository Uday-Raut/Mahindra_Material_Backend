package com.ats.mahindrabattery.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ats.mahindrabattery.entity.TransferPalletMissionDetailsEntity;

public interface TransferPalletMissionDetailsService {

	public List<TransferPalletMissionDetailsEntity> findAll();
	
	public ResponseEntity<Object> addPalletMovementDetailsInTransferPalletMissionDetails(String currentPositionName,
			String destinationPositionName, int userId, String userName);
	
	public List<TransferPalletMissionDetailsEntity> findByCDatetime();
	
	public List<TransferPalletMissionDetailsEntity> findByAllFilters(String startDate, String endDate);
	
	
}
