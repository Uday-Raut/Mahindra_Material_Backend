package com.ats.mahindrabattery.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.MasterSensorDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.MasterSensorDetailsServiceImpl;




@CrossOrigin
@RestController
@RequestMapping("/masterSensor")
public class MasterSensorDetailsController {

	@Autowired
	private MasterSensorDetailsServiceImpl masterSensorDetailsServiceImpl;
	
	
	private static final Logger logger = LoggerFactory.getLogger(MasterSensorDetailsController.class);
	
	@GetMapping("/fetchAllMasterSensorDetails")
	public List<MasterSensorDetailsEntity> findAll(){
	return masterSensorDetailsServiceImpl.findAll();
	}
	
	
	
	@GetMapping("/fetchByWmsEquipmentId/{wmsEquipmentId}")
	public List<MasterSensorDetailsEntity> fetchByEquipmentAlarmId(@PathVariable int wmsEquipmentId){
		
		System.out.println("in controller");
		
		return masterSensorDetailsServiceImpl.findByWmsEquipmentId(wmsEquipmentId);
	}
	
	@GetMapping("/fetchAllByEquipmentSensorStatus")
	public List<MasterSensorDetailsEntity> findAllByEquipmentSensorStatus() {
		return masterSensorDetailsServiceImpl.findAllByEquipmentSensorStatus(1);
	}
	
//	@GetMapping("/findAllByIsBabySensor")
//	public List<MasterSensorDetailsEntity> findAllByIsBabySensor() {
//		return masterSensorDetailsServiceImpl.findAllByIsBabySensor(1);
//	}
	
	
//	@GetMapping("fetchByWmsEquipmentIdAndIsBabySensor/{wmsEquipmentId}/{isBabySensor}")
//	public List<MasterSensorDetailsEntity>fetchByWmsEquipmentIdAndIsBabySensor(){
//		return masterSensorDetailsServiceImpl.fetchByWmsEquipmentIdAndIsBabySensor(wmsEquipmentId,isBabySensor);
//	}
	
}
