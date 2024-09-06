package com.ats.mahindrabattery.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.MasterEquipmentSensorDetailsEntity;
import com.ats.mahindrabattery.repository.MasterEquipmentSensorDetailsRepository;



@Service
public class MasterEquipmentSensorDetailsServiceImpl {

	@Autowired
	private MasterEquipmentSensorDetailsRepository masterEquipmentSensorDetailsRepositoryInstance;

	public List<MasterEquipmentSensorDetailsEntity> findAll() {
		try {
			return masterEquipmentSensorDetailsRepositoryInstance.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<MasterEquipmentSensorDetailsEntity> findAllSensorDiagramView(String sensorDiagramView) {
		try {
			return masterEquipmentSensorDetailsRepositoryInstance.findBySensorDiagramView(sensorDiagramView);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}
}
