package com.ats.mahindrabattery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.StationDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.StationDetailsServicempl;


@CrossOrigin
@RestController
@RequestMapping("/stationDetails")
public class StationDetailsController {
	@Autowired
	StationDetailsServicempl stationDetailsServiceInstance;
	
	@GetMapping("/fetchAllStationDetailsByWmsStationId/{wmsStationId}")
	public List<StationDetailsEntity> fetchAllStationDetailsByWmsStationId(@PathVariable int wmsStationId) {
		return stationDetailsServiceInstance.findAllStationDetailsByWmsStationId(wmsStationId);
	}
}
