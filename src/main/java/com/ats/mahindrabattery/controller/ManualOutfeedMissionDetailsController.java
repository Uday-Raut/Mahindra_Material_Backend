package com.ats.mahindrabattery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.ManualOutfeedMissionDetailsEntity;
import com.ats.mahindrabattery.service.ManualOutfeedMissionDetailsService;

@CrossOrigin
@RestController
@RequestMapping("/manualOutfeedMissionDetails")
public class ManualOutfeedMissionDetailsController {
	@Autowired
	private ManualOutfeedMissionDetailsService manualOutfeedMissionDetailsServiceInstance;

	@GetMapping("/fetchAllManualOutfeedMissionDetails")
	public List<ManualOutfeedMissionDetailsEntity> fetchAllManualOutfeedMissionDetails() {
		return manualOutfeedMissionDetailsServiceInstance.findAll();
	}

	
	@PostMapping("/addCurrentPalletStockdetailsInManualOutfeedMissionDetails")
	public ResponseEntity<ManualOutfeedMissionDetailsEntity> addCurrentPalletStockDetailsInManualOutfeed(
			@RequestBody ManualOutfeedMissionDetailsEntity manualOutfeedMissionDetailsEntity) {
		return manualOutfeedMissionDetailsServiceInstance.addCurrentPalletStockDetailsInManualOutfeed(manualOutfeedMissionDetailsEntity);
	}
}
