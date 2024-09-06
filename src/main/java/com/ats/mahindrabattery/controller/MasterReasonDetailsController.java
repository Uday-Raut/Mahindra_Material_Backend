package com.ats.mahindrabattery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.MasterReasonDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.MasterReasonDetailsServiceImpl;


@RestController
@CrossOrigin
@RequestMapping("/masterReasonDetails")
@EnableCaching
public class MasterReasonDetailsController {
	
	@Autowired
	private MasterReasonDetailsServiceImpl masterReasonDetailsService;
	
	@PostMapping("/addMasterReasonDetails")
//	@CacheEvict(value = "allMasterReasonDetailsCache", allEntries = true)
	public ResponseEntity<Object> addMasterReasonDetails(@RequestBody MasterReasonDetailsEntity masterReasonDetailsEntity) {
		ResponseEntity<Object> masterReasonDetails = masterReasonDetailsService.addMasterReasonDetails(masterReasonDetailsEntity);
		
		if (masterReasonDetails != null) {
	        System.out.println("Data fetched from cache for addmasterReasonDetails");
	    } else {
	        System.out.println("Data not found in cache for addmasterReasonDetails");
	    }
		
		return masterReasonDetails;
	}
	
	@GetMapping("/fetchAllMasterReasonDetails")
//	@Cacheable(cacheNames = "allMasterReasonDetailsCache")
	public List<MasterReasonDetailsEntity> fetchAllMasterReasonDetails(MasterReasonDetailsEntity masterReasonDetailsEntity) {
		
		List<MasterReasonDetailsEntity> allMasterReasonDetails = masterReasonDetailsService.fetchAllMasterReasonDetails(masterReasonDetailsEntity);
		return allMasterReasonDetails;
	}
	
	@GetMapping("/fetchByReasonId/{reasonId}")
//	@Cacheable(cacheNames = "masterReasonDetailsCache", key = "#reasonId", unless = "#result == null")
	public MasterReasonDetailsEntity fetchByReasonId(@PathVariable int reasonId) {
		MasterReasonDetailsEntity masterReasonDetails = masterReasonDetailsService.findMasterReasonDetailsById(reasonId);
		return masterReasonDetails;
	}
	
	@PutMapping("/updateMasterReasonDetails/{reasonId}")
//	@CacheEvict(value = "allMasterReasonDetailsCache", allEntries = true)
	public ResponseEntity<Object> updateMasterReasonDetails(@RequestBody MasterReasonDetailsEntity masterReasonDetailsEntity, @PathVariable int reasonId) {
		ResponseEntity<Object> masterReasonDetails = masterReasonDetailsService.updateMasterReasonDetails(reasonId, masterReasonDetailsEntity);
		
		  if (masterReasonDetails != null) {
		        System.out.println("Data fetched from cache for userId: " + reasonId);
		    } else {
		        System.out.println("Data not found in cache for userId: " + reasonId);
		    }

		return masterReasonDetails;
	}
	
//	@Caching(evict = {
//		    @CacheEvict(value = "allMasterReasonDetailsCache", allEntries = true),
//		    @CacheEvict(value = "masterReasonDetailsCache", key = "#userId")
//		})
	@PostMapping("/deleteMasterReasonDetails/{reasonId}")
	public ResponseEntity<Object> deleteMasterReasonDetails(@PathVariable int reasonId){
		ResponseEntity<Object>reasonDetails=masterReasonDetailsService.deleteMasterReasonDetails(reasonId);
		if (reasonDetails != null) {
	        System.out.println("Data fetched from cache for deleteMasterUserDetails");
	    } else {
	        System.out.println("Data not found in cache for deleteMasterUserDetails");
	    }
		return reasonDetails;
		
	}
}
