package com.ats.mahindrabattery.service;

import java.util.List;

import com.ats.mahindrabattery.entity.MasterEquipmentDetailsEntity;
import com.ats.mahindrabattery.entity.MasterStackerDetailsEntity;

public interface MasterStackerDetailsService {

	
	 public List<MasterStackerDetailsEntity> fetchAllStackerDetailsbyStackerIsDeleted(int stackerIsDeleted);
}
