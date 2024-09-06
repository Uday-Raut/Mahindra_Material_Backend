package com.ats.mahindrabattery.service;

import java.util.List;

import com.ats.mahindrabattery.entity.StackerSensorDetailsEntity;

public interface StackerSensorDetailsService {
	List<StackerSensorDetailsEntity> fetchAllStackerSensorDetails(int stackerSensorIsDeleted);
	
 List<StackerSensorDetailsEntity> findByStackerId(int stackerId);
	
	
}
