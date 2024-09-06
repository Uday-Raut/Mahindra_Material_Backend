package com.ats.mahindrabattery.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.EquipmentAlarmTroubleshootDetailsEntity;
import com.ats.mahindrabattery.repository.EquipmentAlarmTroubleshootDetailsRepository;
import com.ats.mahindrabattery.service.EquipmentAlarmTroubleshootDetailsService;



@Service
public class EquipmentAlarmTroubleshootDetailsServiceImpl implements EquipmentAlarmTroubleshootDetailsService {

	@Autowired
	private EquipmentAlarmTroubleshootDetailsRepository equipmentAlarmTroubleshootDetailsRepositoryInstance;

	public List<EquipmentAlarmTroubleshootDetailsEntity> findAllEquipmentAlarmTroubleshootDetails() {
		return equipmentAlarmTroubleshootDetailsRepositoryInstance.findAll();
	}

	public List<EquipmentAlarmTroubleshootDetailsEntity> findByEquipmentAlarmId(int equipmentAlarmId) {
		try {
			return equipmentAlarmTroubleshootDetailsRepositoryInstance.findByEquipmentAlarmId(equipmentAlarmId);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	public List<EquipmentAlarmTroubleshootDetailsEntity> findAllAlarmResolvedSteps(String alarmResolveSteps) {
		try {
			return equipmentAlarmTroubleshootDetailsRepositoryInstance.findAllByalarmResolveSteps(alarmResolveSteps);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
