package com.ats.mahindrabattery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.YokogawaTemperatureDetailsEntity;
import com.ats.mahindrabattery.repository.YokogawaTemperatureDetailsRepository;
import com.ats.mahindrabattery.service.YokogawaTemperatureDetailsService;

@RestController
@CrossOrigin
@RequestMapping("/yokogawaTempDetails")
public class YokogawaTemperatureDetailsController {

	@Autowired
	private YokogawaTemperatureDetailsRepository yokogawaTemperatureDetailsRepository;

	@Autowired
	private YokogawaTemperatureDetailsService yokogawaTemperatureDetailsService;

	@GetMapping("/fetchTempDetails")
	public List<YokogawaTemperatureDetailsEntity> fetchAllTemperatureDetails() {
		List<YokogawaTemperatureDetailsEntity> findAll = yokogawaTemperatureDetailsRepository.findAll();
		return findAll;
	}

	
	 @GetMapping("/currentDateTemp")
	    public YokogawaTemperatureDetailsEntity fetchTemperatureByCurrentDate() {
	        return yokogawaTemperatureDetailsService.getTemperatureDetailsOfCurrentDate();
	    }

}
