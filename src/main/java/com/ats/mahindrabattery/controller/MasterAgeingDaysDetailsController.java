package com.ats.mahindrabattery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.MasterAgeingDaysDetailsEntity;
import com.ats.mahindrabattery.entity.MasterProductVariantDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.MasterAgeingDaysDetailsServiceImpl;
@CrossOrigin
@RestController
@RequestMapping("/masterAgeingDaysDetails")
public class MasterAgeingDaysDetailsController {

	@Autowired
	private MasterAgeingDaysDetailsServiceImpl masterAgeingDaysDetailsServiceImpl;
	
	@GetMapping("/findAllAgeingDaysDetais")
	public List<MasterAgeingDaysDetailsEntity> findAllAgeingDaysDetais(){
		List<MasterAgeingDaysDetailsEntity> findAllAgeingDaysDetais = masterAgeingDaysDetailsServiceImpl.findAllAgeingDaysDetais();
		return findAllAgeingDaysDetais;
	}
	
	
	
	
	@PutMapping("/updateMasterAgeingDaysDetails")

	public ResponseEntity<Object> updateMasterAgeingDaysDetails(
			@RequestBody MasterAgeingDaysDetailsEntity masterAgeingDaysDetailsEntity) {
		ResponseEntity<Object> masterAgeingdaysDetails = masterAgeingDaysDetailsServiceImpl.updateMasterAgeingdaysDetails(masterAgeingDaysDetailsEntity);


		return masterAgeingdaysDetails;
	}
	
}
