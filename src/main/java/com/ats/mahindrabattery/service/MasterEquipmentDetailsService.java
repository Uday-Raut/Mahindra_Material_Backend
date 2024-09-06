package com.ats.mahindrabattery.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.ats.mahindrabattery.entity.MasterEquipmentDetailsEntity;

public interface MasterEquipmentDetailsService {

	
	public List<MasterEquipmentDetailsEntity>findAll();
	
	public List<MasterEquipmentDetailsEntity>findEquipmentDetailsByEquipmentIsDeleted(int equipmentIsDeleted);
	
	public void updateMasterEquipmentDetails( @RequestBody MasterEquipmentDetailsEntity masterEquipmentDetailsEntity);
	
	
}
