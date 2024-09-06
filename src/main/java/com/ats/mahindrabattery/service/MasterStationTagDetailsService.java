package com.ats.mahindrabattery.service;

import java.util.List;

import com.ats.mahindrabattery.entity.MasterStationTagDetailsEntity;

public interface MasterStationTagDetailsService {

	public List<MasterStationTagDetailsEntity> findAllStationTagDetails() ;
	
	public List<MasterStationTagDetailsEntity> fetchAllStationDetailsByStationId(int stationId) ;
	
	public MasterStationTagDetailsEntity updateStationTagDetailsTable(
			MasterStationTagDetailsEntity masterStationTagDetailsEntity, String currentValue) ;
	
	
}
