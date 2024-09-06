package com.ats.mahindrabattery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ats.mahindrabattery.entity.StackerSensorDetailsEntity;
import com.ats.mahindrabattery.service.StackerSensorDetailsService;

@CrossOrigin
@RestController
@RequestMapping("/masterStackerSensorDetails")
public class StackerSensorDetailsController {

	@Autowired
	private StackerSensorDetailsService sensorDetailsService;
	
	
	@GetMapping("/fetchStackerSensorDetails")
	List<StackerSensorDetailsEntity> fetchStackerSensorDetails(){
		return sensorDetailsService.fetchAllStackerSensorDetails(0);
	}
	
	
	
	@GetMapping("/fetchByStackerId/{stackerId}")
	public List<StackerSensorDetailsEntity> fetchByStackerId(@PathVariable int stackerId){
		return sensorDetailsService.findByStackerId(stackerId);
	}
}
