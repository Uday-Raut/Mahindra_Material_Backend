package com.ats.mahindrabattery.service;

import java.util.List;

import com.ats.mahindrabattery.entity.MasterSensorDetailsEntity;




public interface IMasterSensorDetailsService {
	List<MasterSensorDetailsEntity> findAll();
	List<MasterSensorDetailsEntity> findByWmsEquipmentId(int wmsEquipmentId);
	List<MasterSensorDetailsEntity> findAllByEquipmentSensorStatus(int equipmentSensorStatus);
	//List<MasterSensorDetailsEntity> findAllByIsBabySensor(int isBabySensor);
//	List<MasterSensorDetailsEntity> fetchByWmsEquipmentIdAndIsBabySensor(int wmsEquipmentId, int isBabySensor);
	
}
