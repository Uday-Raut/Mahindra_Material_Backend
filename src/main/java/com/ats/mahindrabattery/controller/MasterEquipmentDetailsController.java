package com.ats.mahindrabattery.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.MasterEquipmentDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.MasterEquipmentDetailsServiceImpl;



@CrossOrigin
@RestController
@RequestMapping("/masterEquipmentDetails")
public class MasterEquipmentDetailsController {

	@Autowired
	private MasterEquipmentDetailsServiceImpl masterEquipmentDetailsServiceInstance;

	@GetMapping("/fetchAllMasterEquipmentDetails")
	public List<MasterEquipmentDetailsEntity> fetchAllMasterEquipmentDetails() {
		return masterEquipmentDetailsServiceInstance.findAll();
	}

	@PutMapping("/updateMasterEquipmentDetails")
	public void updateMasterEquipmentDetails(@RequestBody MasterEquipmentDetailsEntity masterEquipmentDetailsEntity) {
		 masterEquipmentDetailsServiceInstance.updateMasterEquipmentDetails(masterEquipmentDetailsEntity);
	}
	
	
	@GetMapping("/fetchByEquipmentDetailsIsDeleted")
	public List<MasterEquipmentDetailsEntity> fetchByEquipmentDetailsIsDeleted(){

		return masterEquipmentDetailsServiceInstance.findEquipmentDetailsByEquipmentIsDeleted(0);
	}

}
