package com.ats.mahindrabattery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.MappingFloorAreaDetailsEntity;
import com.ats.mahindrabattery.repository.MappingFloorAreaDetailsRepository;
import com.ats.mahindrabattery.serviceimpl.MappingFloorAreaDetailsServiceImpl;



@CrossOrigin
@RestController
@RequestMapping("/mappingFloorAreaDetails")
public class MappingFloorAreaDetailsController {
	@Autowired
	private MappingFloorAreaDetailsServiceImpl mappingFloorAreaDetailsServiceInstance;
	
	@Autowired
	MappingFloorAreaDetailsRepository mappingFloorAreaDetailsRepositoryInstance;

	@GetMapping("/fetchAllMappingFloorAreaDetails")
	public List<MappingFloorAreaDetailsEntity> fetchAllMappingFloorAreaDetails() {
		return mappingFloorAreaDetailsServiceInstance.findAll();
	}

	@GetMapping("/fetchMappingFloorAreaByAreaId/{areaId}")
	public List<MappingFloorAreaDetailsEntity> fetchMappingFloorAreaByAreaId(@PathVariable int areaId) {
		return mappingFloorAreaDetailsServiceInstance.findByAreaId(areaId);
	}

	@PutMapping("/updateMappingFloorAreaDetails")
	public void updateMappingFloorAreaDetails(@RequestBody MappingFloorAreaDetailsEntity mappingMappingFloorAreaDetailsEntity) {
	
		mappingFloorAreaDetailsServiceInstance.updateMappingFloorAreaDetails(mappingMappingFloorAreaDetailsEntity);
         }
	
	
	@PutMapping("/updateMappingFloorAreaDetailsByareaId/{infeedIsActive}/{outfeedIsActive}/{areaId}")
	public void updateMappingFloorAreaDetailsByareaId(@PathVariable int infeedIsActive,@PathVariable int outfeedIsActive, @PathVariable int areaId  ) {
	
		mappingFloorAreaDetailsServiceInstance.updateMappingFloorAreaDetailsByareaId(infeedIsActive,outfeedIsActive,areaId);
         }
	
	
	@GetMapping("/fetchMappingFloorAreaDetailsByAreaIdAndFloorName/{areaId}/{floorName}")
	public MappingFloorAreaDetailsEntity fetchMappingFloorAreaDetailsByAreaIdAndFloorName(@PathVariable int areaId,@PathVariable String floorName) {
		return mappingFloorAreaDetailsRepositoryInstance.getByAreaIdAndFloorName(areaId,floorName);

	}

}
