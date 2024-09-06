package com.ats.mahindrabattery.service;

import java.util.List;

import com.ats.mahindrabattery.entity.MasterEquipmentSensorDetailsEntity;

public interface MasterEquipmentSensorDetailsService {

	public List<MasterEquipmentSensorDetailsEntity> findAll();
	
	public List<MasterEquipmentSensorDetailsEntity> findAllSensorDiagramView(String sensorDiagramView) ;
	
	
}
