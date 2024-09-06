package com.ats.mahindrabattery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.QualityInspectionStatusEntity;
import com.ats.mahindrabattery.service.QualityInspectionStatusService;

@CrossOrigin
@RestController
@RequestMapping("/qualityInspectionStatus")
public class QualityInspectionStatusController {
	@Autowired
	private QualityInspectionStatusService qualityInspectionStatusService;
	
	

//	@GetMapping("/fetchAllQualityInspectionStatusDetails")
//	public List<QualityInspectionStatusEntity> fetchAllQualityInspectionStatusDetails() {
//		return qualityInspectionStatusService.fetchAllQualityInspectionStatusDetails();
//	}
}
