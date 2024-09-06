package com.ats.mahindrabattery.service;

import java.util.List;

import com.ats.mahindrabattery.entity.MasterFloorDetailsEntity;
import com.ats.mahindrabattery.entity.MasterShiftDetailsEntity;
import com.ats.mahindrabattery.entity.MasterUserDetailsEntity;

public interface MasterShiftDetailsService {
	public List<MasterShiftDetailsEntity> fetchAllMasterShiftDetails();
	
	
}
