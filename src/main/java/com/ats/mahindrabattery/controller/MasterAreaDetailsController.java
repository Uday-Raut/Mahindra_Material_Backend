package com.ats.mahindrabattery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.MasterAreaDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.MasterAreaDetailsServiceImpl;


@CrossOrigin
@RestController
@RequestMapping("/masterAreaDetails")
public class MasterAreaDetailsController {
	@Autowired
	private MasterAreaDetailsServiceImpl masterAreaDetailsServiceInstance;

	@GetMapping("/fetchAllAreaDetails")
	public List<MasterAreaDetailsEntity> fetchAllAreaDetailsDetails() {
		return masterAreaDetailsServiceInstance.findAll();
	}

	@GetMapping("/fetchAllAreaDetailsDetailsByAreaId/{areaId}")
	public List<MasterAreaDetailsEntity> fetchAllAreaDetailsDetailsByAreaId(@PathVariable int areaId) {
		return masterAreaDetailsServiceInstance.findByAreaId(areaId);
	}
}
