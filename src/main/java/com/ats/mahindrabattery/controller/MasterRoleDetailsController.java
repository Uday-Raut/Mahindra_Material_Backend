package com.ats.mahindrabattery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.MasterRoleDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.MasterRoleDetailsServiceImpl;


@RestController
@CrossOrigin
@RequestMapping("/masterRoleDetails")
public class MasterRoleDetailsController {

	@Autowired
	private MasterRoleDetailsServiceImpl masterRoleDetailsService;
	
	@GetMapping("/fetchAllMasterRoleDetails")
	public List<MasterRoleDetailsEntity> fetchAllMasterRoleDetails(){
		List<MasterRoleDetailsEntity> fetchAllMasterRoleDetails = masterRoleDetailsService.fetchAllMasterRoleDetails();
		return fetchAllMasterRoleDetails;
	}
}
