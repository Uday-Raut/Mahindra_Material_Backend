package com.ats.mahindrabattery.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.AuditTrailDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.AuditTrailDetailsServiceImpl;


@RestController
@CrossOrigin
@RequestMapping("/auditTrailDetails")
public class AuditTrailDetailsController {

	@Autowired
	private AuditTrailDetailsServiceImpl auditTrailDetailsServiceInstance;

	@GetMapping("/findByCdatetime")
	public List<AuditTrailDetailsEntity> findAuditTrailDetailsByCDateTime() {
	
		List<AuditTrailDetailsEntity> findAuditTrailDetailsByCDateTime = auditTrailDetailsServiceInstance
				.findByDatetimeCOrderByAuditIdDesc();
		return findAuditTrailDetailsByCDateTime;
	}

	@GetMapping("/findByAllFilters/{startDate}/{endDate}/{username}")
	public List<AuditTrailDetailsEntity> findByAllFiltersDetails(@PathVariable String startDate,
			@PathVariable String endDate, @PathVariable String username) {
		List<AuditTrailDetailsEntity> findByAllFiltersDetails = auditTrailDetailsServiceInstance
				.findByAllFiltersDetails(startDate, endDate, username);
		return findByAllFiltersDetails;
	}
	
	
	@PostMapping("/addPositionLockUnLockDeleteReasonInUserAuditTrailReport/{operatorsAction}/{positionId}/{reason}/{field}/{beforeValue}/{afterValue}/{userName}")
	public AuditTrailDetailsEntity addPositionLockReasonInUserAuditTrail(AuditTrailDetailsEntity AuditTrailDetailsEntity,  @PathVariable String operatorsAction,@PathVariable int positionId,@PathVariable String reason,@PathVariable String field,@PathVariable int beforeValue,@PathVariable int afterValue
			,@PathVariable String userName) 
	{
		return auditTrailDetailsServiceInstance.addPositionLockReasonInUserAuditTrail(AuditTrailDetailsEntity,operatorsAction,positionId, reason, field,beforeValue,afterValue,userName);
		
	}
	
	@GetMapping("/fetchAllUserAuditTrailDetails")
	public List<AuditTrailDetailsEntity> fetchAllUserAuditTrailDetails() {
		return auditTrailDetailsServiceInstance.findAll();
	}
}
