package com.ats.mahindrabattery.service;

import java.util.List;

import com.ats.mahindrabattery.entity.EquipmentAlarmTroubleshootDetailsEntity;

public interface EquipmentAlarmTroubleshootDetailsService {
   
	
	public List<EquipmentAlarmTroubleshootDetailsEntity> findAllEquipmentAlarmTroubleshootDetails() ;
	
	public List<EquipmentAlarmTroubleshootDetailsEntity> findByEquipmentAlarmId(int equipmentAlarmId);
	
	public List<EquipmentAlarmTroubleshootDetailsEntity> findAllAlarmResolvedSteps(String alarmResolveSteps);
	
}
