package com.ats.mahindrabattery.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.MasterProductDetailsEntity;
import com.ats.mahindrabattery.entity.MasterProductVariantDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.MasterProductDetailsServiceImpl;



@RestController
@CrossOrigin
@RequestMapping("/masterProductDetails")
public class MasterProductDetailsController {

	@Autowired
	private MasterProductDetailsServiceImpl masterProductDetailsService;

//	@PostMapping("/create")
//	public MasterProductDetailsEntity createMasterProductDetails(
//			 @RequestBody MasterProductDetailsEntity masterProductDetailsEntity) {
//		MasterProductDetailsEntity masterProductDetails = masterProductDetailsService
//				.createMasterProductDetails(masterProductDetailsEntity);
//		return masterProductDetails;
//	}
	@PostMapping("/create")
	public ResponseEntity<Object> addMasterProductVariantDetails(
			 @RequestBody MasterProductDetailsEntity masterProductDetailsEntity) {
		
		ResponseEntity<Object> masterProductDetails = masterProductDetailsService
				.createmasterMasterProductDetails(masterProductDetailsEntity);
		  if (masterProductDetails != null) {
		        System.out.println("Data fetched from cache for addMasterUserDetails");
		    } else {
		        System.out.println("Data not found in cache for addMasterUserDetails");
		    }
		return masterProductDetails;
		
	}

//	@GetMapping("/getall")
//	public List<MasterProductDetailsEntity> getAllMasterProductDetails(
//			MasterProductDetailsEntity masterProductDetailsEntity) {
//		List<MasterProductDetailsEntity> allMasterProductDetails = masterProductDetailsService
//				.getAllMasterProductDetails(masterProductDetailsEntity);
//		return allMasterProductDetails;
//	}
	@GetMapping("/fetchAllMasterProductDetails")
	
	public List<MasterProductDetailsEntity> fetchAllMasterProductDetails(
			  ) {
		List<MasterProductDetailsEntity> masterProductDetails = masterProductDetailsService
				.getAllMasterProductDetails();
		return masterProductDetails;
	}

	@GetMapping("/getbyid/{id}")
	public MasterProductDetailsEntity findMasterProductDetailsById(@PathVariable int id) {
		MasterProductDetailsEntity masterProductDetails = masterProductDetailsService.findMasterProductDetailsById(id);
		return masterProductDetails;
	}

//	@PutMapping("/update/{id}")
//	public MasterProductDetailsEntity updateMasterProductDetails( @PathVariable int id,
//			@RequestBody MasterProductDetailsEntity masterProductDetailsEntity) {
//		MasterProductDetailsEntity masterProductDetails = masterProductDetailsService.updateMasterProductDetails(id,
//				masterProductDetailsEntity);
//		return masterProductDetails;
//	}
	
	@PutMapping("/updateMasterProductDetails")
	
	public ResponseEntity<Object> updateMasterProductDetails(@RequestBody MasterProductDetailsEntity masterProductDetailsEntity) {
		ResponseEntity<Object> masterProductDetails = masterProductDetailsService.updateMasterProductDetails(masterProductDetailsEntity);
		
		  if (masterProductDetails != null) {
		        System.out.println("Data fetched from cache for userId: ");
		    } else {
		        System.out.println("Data not found in cache for userId: ");
		    }
		return masterProductDetails;
	}

//	@PostMapping("/delete/{id}")
//	public ResponseEntity<HttpStatus> deleteMasterProductDetails(@PathVariable int id) {
//		masterProductDetailsService.deleteMasterProductDetails(id);
//		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
//	}
	
	@PutMapping("/deleteMasterProductDetails/{productId}")
	
	public ResponseEntity<Object> deleteMasterProductDetails(@PathVariable int productId){
		System.out.println("productId ::"+productId );
		ResponseEntity<Object> masterProductDetails=masterProductDetailsService.deleteMasterProductDetails(productId);
		if (masterProductDetails != null) {
	        System.out.println("Data fetched from cache for deleteMasterUserDetails");
	    } else {
	        System.out.println("Data not found in cache for deleteMasterUserDetails");
	    }
		return masterProductDetails;
	
	}
	
@GetMapping("/fetchByProductIsDeleted")
	
	public List<MasterProductDetailsEntity> fetchByProductIsDeleted(){
		List<MasterProductDetailsEntity> findByProductIsDeleted = masterProductDetailsService.findByProductIsDeleted();
		return findByProductIsDeleted;
	}
}
