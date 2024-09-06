package com.ats.mahindrabattery.service;

import java.util.List;

import com.ats.mahindrabattery.entity.MasterRackDetailsEntity;

public interface MasterRackDetailsService {

	
	public List<MasterRackDetailsEntity> getRackDetailsByAreaIdAndFloorId(int areaId, int floorId);
}
