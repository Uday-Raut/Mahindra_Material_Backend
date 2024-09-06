package com.ats.mahindrabattery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.MasterShiftDetailsEntity;
import com.ats.mahindrabattery.entity.MasterUserDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.MasterShiftDetailsServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/masterShiftDetails")
public class MasterShiftDetailsController {
	
	@Autowired
	private MasterShiftDetailsServiceImpl masterShiftDetailsService;
	
	@GetMapping("/fetchallmastershiftdetails")
	public List<MasterShiftDetailsEntity> fetchAllMasterShiftDetails() {
		List<MasterShiftDetailsEntity> fetchAllMasterShiftDetails = masterShiftDetailsService.fetchAllMasterShiftDetails();
		return fetchAllMasterShiftDetails;
	}

	
	
//	@GetMapping("/fetchallmastershiftdetails")
//	public List<MasterShiftDetailsEntity> fetchAllMasterShiftDetails() {
//		List<MasterShiftDetailsEntity> fetchAllMasterShiftDetails = masterShiftDetailsService.fetchAllMasterShiftDetails();
//		return fetchAllMasterShiftDetails;
//	}
}
