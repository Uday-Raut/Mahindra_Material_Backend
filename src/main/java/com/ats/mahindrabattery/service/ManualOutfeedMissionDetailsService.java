package com.ats.mahindrabattery.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.ats.mahindrabattery.entity.ManualOutfeedMissionDetailsEntity;

public interface ManualOutfeedMissionDetailsService {

	
	public List<ManualOutfeedMissionDetailsEntity>findAll();
	
	public ResponseEntity<ManualOutfeedMissionDetailsEntity> addCurrentPalletStockDetailsInManualOutfeed(
			 ManualOutfeedMissionDetailsEntity manualOutfeedMissionDetailsEntity);
	
	
}
