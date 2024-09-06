package com.ats.mahindrabattery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.MasterStationTagDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.MasterStationTagDetailsServiceImpl;



@CrossOrigin
@RestController
@RequestMapping("/masterStationTagDetails")
public class MasterStationTagDetailsController {
	@Autowired
	private MasterStationTagDetailsServiceImpl masterStationTagDetailsServiceInstance;

	@GetMapping("/fetchAllStationTagDetails")
	public List<MasterStationTagDetailsEntity> fetchAllStationTagDetails() {
		return masterStationTagDetailsServiceInstance.findAllStationTagDetails();
	}
	@GetMapping("/fetchAllStationDetailsByStationId/{stationId}")
	public List<MasterStationTagDetailsEntity> fetchAllStationDetails(@PathVariable int stationId) {
		return masterStationTagDetailsServiceInstance.fetchAllStationDetailsByStationId(stationId);
	}
	
	@PutMapping("/updateStationTagDetailsTable/{currentValue}")
	public MasterStationTagDetailsEntity updateStationTagDetailsTable(@RequestBody MasterStationTagDetailsEntity masterStationTagDetailsEntity,@PathVariable String currentValue) {
	
		return masterStationTagDetailsServiceInstance.updateStationTagDetailsTable(masterStationTagDetailsEntity ,currentValue);
	}
	
}
