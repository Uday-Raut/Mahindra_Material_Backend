package com.ats.mahindrabattery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.MasterFloorDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.MasterFloorDetailsServiceImpl;



@RestController
@CrossOrigin
@RequestMapping("/masterFloorDetails")
public class MasterFloorDetailsController {

	@Autowired
	private MasterFloorDetailsServiceImpl masterFloorDetailsService;
	
	@GetMapping("/fetchallmasterfloordetails")
	public List<MasterFloorDetailsEntity> fetchAllMasterFloorDetails() {
		List<MasterFloorDetailsEntity> fetchAllMasterFloorDetails = masterFloorDetailsService.fetchAllMasterFloorDetails();
		return fetchAllMasterFloorDetails;
	}
	
}
