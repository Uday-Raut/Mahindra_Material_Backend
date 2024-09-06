package com.ats.mahindrabattery.service;

import java.util.List;

import com.ats.mahindrabattery.entity.MasterPalletInformationEntity;

public interface MasterPalletInformationService {

	public List<MasterPalletInformationEntity> findAllMasterPalletInformation();
	
	public void addMasterPalletInformationDetails(MasterPalletInformationEntity masterPalletInformationEntity);
	
	public List<MasterPalletInformationEntity> findPalletInformationIdDesc() ;
	
	public List<MasterPalletInformationEntity> updatePalletInformationDetails(int palletStatusId,
			String palletStatusName, String palletCode, int stationWorkdone);
	
	public List<MasterPalletInformationEntity> updatePalletInformationDetailsByStationWorkdone(int stationWorkdone,
			String palletCode) ;
	
	
}
