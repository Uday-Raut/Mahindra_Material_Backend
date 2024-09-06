package com.ats.mahindrabattery.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.MasterSensorDetailsEntity;
import com.ats.mahindrabattery.repository.IMasterSensorDetailsRepository;
import com.ats.mahindrabattery.service.IMasterSensorDetailsService;


@Service
public class MasterSensorDetailsServiceImpl implements IMasterSensorDetailsService{

	@Autowired
	IMasterSensorDetailsRepository iMasterSensorDetailsRepository;
	
	@Override
	public List<MasterSensorDetailsEntity> findAll() {
		return iMasterSensorDetailsRepository.findAll();
	}
	
	
	

	@Override
	public List<MasterSensorDetailsEntity> findByWmsEquipmentId(int wmsEquipmentId) {
		return iMasterSensorDetailsRepository.findByWmsEquipmentId(wmsEquipmentId);
	}
	
//	@Override
//	public List<MasterSensorDetailsEntity> fetchByWmsEquipmentIdAndIsBabySensor(int wmsEquipmentId,int isBabySensor){
//		return iMasterSensorDetailsRepository.fetchByWmsEquipmentIdAndIsBabySensor(wmsEquipmentId,isBabySensor);
//	}

	@Override
	public List<MasterSensorDetailsEntity> findAllByEquipmentSensorStatus(int equipmentSensorStatus) {
		return iMasterSensorDetailsRepository.findAllByEquipmentSensorStatus(1);
	}

//	@Override
//	public List<MasterSensorDetailsEntity> findAllByIsBabySensor(int isBabySensor) {
//	return iMasterSensorDetailsRepository.findAllByIsBabySensor(1);
//	}

}
