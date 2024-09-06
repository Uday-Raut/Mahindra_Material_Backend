package com.ats.mahindrabattery.service;

import java.util.List;

import com.ats.mahindrabattery.entity.InfeedMissionRuntimeDetailsEntity;

public interface InfeedMissionRuntimeDetailsService {

	
	public List<InfeedMissionRuntimeDetailsEntity> getAllInfeedMissionRuntimeDetails() ;
	
	
	public List<InfeedMissionRuntimeDetailsEntity> findByCreatedDateTime(String createdDatetime);
	
	public List<InfeedMissionRuntimeDetailsEntity> findAllInfeedMissionRuntimeDetailsByDate(String startDate,
			String endDate);
	
	public List<InfeedMissionRuntimeDetailsEntity> getByDate() ;
	
	public List<InfeedMissionRuntimeDetailsEntity> findByInfeedMissionId(int id);
	
	public List<InfeedMissionRuntimeDetailsEntity> fetchInfeedMissionRuntimeDetails() ;
	
	public void updateInfeedMissionRuntimeDetails(InfeedMissionRuntimeDetailsEntity infeedMissionRuntimeDetailsEntity);
	
	public List<InfeedMissionRuntimeDetailsEntity> findByAllFilters(String floorName, String areaName,
			String infeedMissionStatus, String infeedMissionStartCdatetime, String infeedMissionEndCdatetime);
	
	
}
