package com.ats.mahindrabattery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.AccessMatrixEntity;
import com.ats.mahindrabattery.service.AccessMatrixService;
import com.ats.mahindrabattery.serviceimpl.AccessMatrixServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/accessMatrix")
public class AccessMatrixController {

	@Autowired
	private AccessMatrixService accessMatrixService;

	@GetMapping("/fetchAllAccessMatrixDetails")
	public List<AccessMatrixEntity> getAllAccessMatrixDetails() {
		return accessMatrixService.getAllAccessMatrixDetails();
	}



	@PutMapping("/update")
	public AccessMatrixEntity updateAccessMatrix(@RequestBody AccessMatrixEntity accessMatrix) {
		AccessMatrixEntity updateAccessMatrix = accessMatrixService.updateAccessMatrix(accessMatrix);

		return updateAccessMatrix;
	}

}
