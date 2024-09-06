package com.ats.mahindrabattery.service;

import java.util.List;

import com.ats.mahindrabattery.entity.OutfeedMissionRuntimeDetailsEntity;

public interface OutfeedMissionRuntimeDetailsService {

	public List<OutfeedMissionRuntimeDetailsEntity> getAllOutfeedMissionRuntimeDetails();
	
	public List<OutfeedMissionRuntimeDetailsEntity> findByCreatedDateTime(String createdDatetime);
	
	public List<OutfeedMissionRuntimeDetailsEntity> findOutfeedNissionRuntimeDetailsBetweenDates(String startDate,
			String endDate);
	
	public List<OutfeedMissionRuntimeDetailsEntity> getByDate() ;
	
	public List<OutfeedMissionRuntimeDetailsEntity> fetchOutfeedMissionRuntimeDetails();
	
	public void updateOutfeedMissionRuntimeDetails(
			OutfeedMissionRuntimeDetailsEntity OutfeedMissionRuntimeDetailsEntity);
	
	public List<OutfeedMissionRuntimeDetailsEntity> findByAllFilters(String floorName, String areaName,
			String outfeedMissionStatus, String outfeedMissionCdatetimeStart, String outfeedMissionCdatetimeEnd);
	
	
	
	
}
