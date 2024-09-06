package com.ats.mahindrabattery.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ats.mahindrabattery.entity.TransferPalletMissionDetailsEntity;
import com.ats.mahindrabattery.entity.TransferPalletMissionRuntimeDetailsEntity;

public interface TransferPalletMissionRuntimeDetailsService {

	public List<TransferPalletMissionRuntimeDetailsEntity> fetchTransferPalletMissionRuntimeDetails();
	
	public void updatePalletMissionRuntimeDetails(TransferPalletMissionRuntimeDetailsEntity transferPalletMissionRuntimeDetailsEntity);
	
	
}
