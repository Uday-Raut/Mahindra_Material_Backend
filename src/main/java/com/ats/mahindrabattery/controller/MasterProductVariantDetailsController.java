package com.ats.mahindrabattery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.MasterProductVariantDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.MasterProductVariantDetailsServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/masterProductVariantDetails")
//@EnableCaching
public class MasterProductVariantDetailsController {

	@Autowired
	private MasterProductVariantDetailsServiceImpl masterProductVariantDetailsService;

	@PostMapping("/addMasterProductVariantDetails")
//	@CacheEvict(value = "allMasterProductVariantDetailsCache", allEntries = true)
	public ResponseEntity<Object> addMasterProductVariantDetails(
			@RequestBody MasterProductVariantDetailsEntity masterProductVariantDetailsEntity) {

		ResponseEntity<Object> masterProductVariantDetails = masterProductVariantDetailsService
				.createmasterMasterProductVariantDetails(masterProductVariantDetailsEntity);
//		  if (masterProductVariantDetails != null) {
//		        System.out.println("Data fetched from cache for addMasterUserDetails");
//		    } else {
//		        System.out.println("Data not found in cache for addMasterUserDetails");
//		    }
		return masterProductVariantDetails;

	}

	@GetMapping("/fetchAllMasterProductVariantDetails")

	public List<MasterProductVariantDetailsEntity> fetchAllMasterProductVarientDetails() {
		List<MasterProductVariantDetailsEntity> masterProductVariantDetails = masterProductVariantDetailsService
				.getAllMasterProductVariantDetails();
		return masterProductVariantDetails;
	}
	
	
	//@GetMapping("/fetchAllMasterProductVariantDetails")
//	@Cacheable(cacheNames = "allMasterProductVariantDetailsCache")
//	public Page<MasterProductVariantDetailsEntity> fetchAllMasterProductVarientDetails(
//			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
//		Pageable pageable = PageRequest.of(page, size);
//		Page<MasterProductVariantDetailsEntity> masterProductVariantDetails = masterProductVariantDetailsService
//				.getAllMasterProductVariantDetails(pageable);
//		return masterProductVariantDetails;
//	}

	@GetMapping("/fetchByProductVariantId/{productVariantId}")
//	@Cacheable(cacheNames = "masterProductVariantDetailsCache", key = "#productVariantId", unless = "#result == null")
	public MasterProductVariantDetailsEntity fetchByProductVariantId(@PathVariable int productVariantId,
			MasterProductVariantDetailsEntity masterProductVariantDetailsEntity) {
		MasterProductVariantDetailsEntity masterProductVariantDetails = masterProductVariantDetailsService
				.findMasterProductVariantDetailsById(productVariantId);
		return masterProductVariantDetails;
	}

	@PutMapping("/updateMasterProductVariantDetails")
//	@CacheEvict(value = "allMasterProductVariantDetailsCache", allEntries = true)
	public ResponseEntity<Object> updateMasterProductVariantDetails(
			@RequestBody MasterProductVariantDetailsEntity masterProductVariantDetailsEntity) {
		ResponseEntity<Object> masterProductVariantDetails = masterProductVariantDetailsService
				.updateMasterProductVariantDetails(masterProductVariantDetailsEntity);

//		  if (masterProductVariantDetails != null) {
//		        System.out.println("Data fetched from cache for userId: ");
//		    } else {
//		        System.out.println("Data not found in cache for userId: ");
//		    }
		return masterProductVariantDetails;
	}

	@PutMapping("/deleteMasterProductVariantDetails/{productVariantId}")
//	@Caching(evict = {
//		    @CacheEvict(value = "allMasterProductVariantDetailsCache", allEntries = true),
//		    @CacheEvict(value = "masterProductVariantDetailsCache", key = "#productVariantId")
//		})
	public ResponseEntity<Object> deleteMasterProductVariantDetails(@PathVariable int productVariantId) {
		System.out.println("productVaraintId ::" + productVariantId);
		ResponseEntity<Object> masterProductVariantDetails = masterProductVariantDetailsService
				.deleteMasterProductVariantDetails(productVariantId);
//		if (masterProductVariantDetails != null) {
//	        System.out.println("Data fetched from cache for deleteMasterUserDetails");
//	    } else {
//	        System.out.println("Data not found in cache for deleteMasterUserDetails");
//	    }
		return masterProductVariantDetails;

	}

	@GetMapping("/fetchByProductVariantIsDeleted")

	public List<MasterProductVariantDetailsEntity> fetchByProductVariantIsDeleted() {
		List<MasterProductVariantDetailsEntity> findByProductVariantIsDeleted = masterProductVariantDetailsService
				.findByProductVariantIsDeleted();
		return findByProductVariantIsDeleted;
	}
}
