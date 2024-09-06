package com.ats.mahindrabattery.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ats.mahindrabattery.entity.MasterUserDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.MasterUserDetailsServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/masterUserDetails")
//@EnableCaching
public class MasterUserDetailsController {

	@Autowired
	private MasterUserDetailsServiceImpl masterUserDetailsService;

	@PostMapping("/addMasterUserDetails")
//	@CacheEvict(value = "allMasterUserDetailsCache", allEntries = true)
	public ResponseEntity<Object> addMasterUserDetails(@RequestParam MultipartFile file,

			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("userTitle") String userTitle, @RequestParam("userName") String userName,
			@RequestParam("userPassword") String userPassword, @RequestParam("contactNumber") String contactNumber,
			@RequestParam("emailId") String emailId, @RequestParam("gender") String gender,
			@RequestParam("roleName") String roleName) throws IOException {
		ResponseEntity<Object> masterUserDetails = masterUserDetailsService.createMasterUserDetails(file, firstName,
				lastName, userTitle, userName, userPassword, contactNumber, emailId, gender, roleName);
		   if (masterUserDetails != null) {
		        System.out.println("Data fetched from cache for addMasterUserDetails");
		    } else {
		        System.out.println("Data not found in cache for addMasterUserDetails");
		    }
		return masterUserDetails;
	}

	@GetMapping("/fetchAllMasterUserDetails")
//	@Cacheable(cacheNames = "allMasterUserDetailsCache")
	public List<MasterUserDetailsEntity> fetchAllMasterUserDetails() {
		List<MasterUserDetailsEntity> masterUserDetails = masterUserDetailsService
				.findAllMasterUserDetails();
		return masterUserDetails;
	}                    

	@GetMapping("/findMasterUserDetailsById/{userId}")
//	@Cacheable(cacheNames = "masterUserCache", key = "#userId", unless = "#result == null")
	public MasterUserDetailsEntity findMasterUserDetailsById(@PathVariable int userId) {
		MasterUserDetailsEntity masterUserDetails = masterUserDetailsService.findMasterUserDetailsByid(userId);
		return masterUserDetails;
	}

//	@PutMapping("/updateMasterUserDetails/{userId}")

//	public ResponseEntity<Object> updateMasterUserDetails(
//			@PathVariable int userId,@RequestParam(required=false) MultipartFile file,
//			@RequestParam String firstName, @RequestParam String lastName, @RequestParam String userTitle,
//			@RequestParam String userName, @RequestParam String contactNumber,
//			@RequestParam String emailId, @RequestParam String gender,
//			@RequestParam String roleName) throws IOException {
//		ResponseEntity<Object> masterUserDetails = masterUserDetailsService
//				.updateMasterUserDetails(userId,file,firstName,lastName,userTitle,userName,contactNumber,
//						emailId,gender,roleName);
//		//System.out.println("Data cached for userId: " + userId);
//		 
//		return masterUserDetails;
//	}
	
	@PutMapping("/updateMasterUserDetails/{userId}")
//    @CacheEvict(value = "allMasterUserDetailsCache", allEntries = true)
	
	public ResponseEntity<Object> updateMasterUserDetails(
	        @PathVariable int userId, @RequestParam(required = false) MultipartFile file,
	        @RequestParam String firstName, @RequestParam String lastName, @RequestParam String userTitle,
	        @RequestParam String userName, @RequestParam String contactNumber,
	        @RequestParam String emailId, @RequestParam String gender,
	        @RequestParam String roleName) throws IOException {
	    ResponseEntity<Object> masterUserDetails = masterUserDetailsService
	            .updateMasterUserDetails(userId, file, firstName, lastName, userTitle, userName, contactNumber,
	                    emailId, gender, roleName);

	    
	    if (masterUserDetails != null) {
	        System.out.println("Data fetched from cache for userId: " + userId);
	    } else {
	        System.out.println("Data not found in cache for userId: " + userId);
	    }
        
	    return masterUserDetails;
	}


	
	
	
	
	@PutMapping("/deleteMasterUserDetails/{userId}")
//	@Caching(evict = {
//		    @CacheEvict(value = "allMasterUserDetailsCache", allEntries = true),
//		    @CacheEvict(value = "masterUserCache", key = "#userId")
//		})
	public ResponseEntity<Object> deleteMasterUserDetails(@PathVariable Integer userId) {
		ResponseEntity<Object>userDetails=masterUserDetailsService.deleteMasterUserDetails(userId);
		 if (userDetails != null) {
		        System.out.println("Data fetched from cache for deleteMasterUserDetails");
		    } else {
		        System.out.println("Data not found in cache for deleteMasterUserDetails");
		    }
		return userDetails;
	}

	
}
