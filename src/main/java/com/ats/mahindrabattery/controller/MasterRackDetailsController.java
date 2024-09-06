package com.ats.mahindrabattery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.MasterRackDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.MasterRackDetailsServiceImpl;


@RestController
@CrossOrigin
@RequestMapping("/masterRackDetails")
public class MasterRackDetailsController {
	@Autowired
	private MasterRackDetailsServiceImpl masterRackDetailsServiceInstance;

	@GetMapping("/fetchByRackDetailsByAreaIdAndFloorId/{areaId}/{floorId}")
	public List<MasterRackDetailsEntity> fetchMasterRackDetailsByAreaIdAndFloorId(@PathVariable int areaId,@PathVariable int floorId ){
		try {
			return masterRackDetailsServiceInstance.getRackDetailsByAreaIdAndFloorId(areaId, floorId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
