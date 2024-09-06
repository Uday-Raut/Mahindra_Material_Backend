package com.ats.mahindrabattery.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.ats.mahindrabattery.entity.MappingFloorAreaDetailsEntity;

public interface MappingFloorAreaDetailsService {

	public List<MappingFloorAreaDetailsEntity> findAll();
	
	public List<MappingFloorAreaDetailsEntity> findByAreaId(int areaId);
	
	public void updateMappingFloorAreaDetails(
			@RequestBody MappingFloorAreaDetailsEntity mappingMappingFloorAreaDetailsEntity);
	
	public void updateMappingFloorAreaDetailsByareaId(int infeedIsActive, int outfeedIsActive, int areaId) ;
	
	List<MappingFloorAreaDetailsEntity> fetchMappingFloorAreaDetailsByAreaIdAndFloorName(int areaId, String floorName);
	
	
}
