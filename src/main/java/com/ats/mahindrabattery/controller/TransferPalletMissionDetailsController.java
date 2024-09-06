package com.ats.mahindrabattery.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.TransferPalletMissionDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.TransferPalletMissionDetailsServiceImpl;



@CrossOrigin
@RestController
@RequestMapping("/transferPalletMissionDetails")
public class TransferPalletMissionDetailsController {
	@Autowired
	TransferPalletMissionDetailsServiceImpl transferPalletMissionDetailsServiceInstance;
	
	@GetMapping("/fetchAllTranferPalletMissionDetails")
	public List<TransferPalletMissionDetailsEntity> fetchAllTranferPalletMissionDetails() {
		return transferPalletMissionDetailsServiceInstance.findAll();
	}

	@GetMapping("/findByCdatetime")
	public List<TransferPalletMissionDetailsEntity> findTransferPalletMissionByCDateTime(){
		List<TransferPalletMissionDetailsEntity>findTransferPalletMissionByCDateTime=transferPalletMissionDetailsServiceInstance.findByCDatetime();
		return findTransferPalletMissionByCDateTime;
	}
	
	@PostMapping("/addPalletMovementDetailsInTransferPalletMissionDetails/{currentPositionName}/{destinationPositionName}/{userId}/{userName}")
	public ResponseEntity<Object> addPalletMovementDetailsInTransferPalletMissionDetails(
				@PathVariable String currentPositionName,@PathVariable String destinationPositionName, @PathVariable int userId ,@PathVariable String userName) {
				return transferPalletMissionDetailsServiceInstance.addPalletMovementDetailsInTransferPalletMissionDetails(currentPositionName,destinationPositionName, userId, userName);

		

}
	
	
	@GetMapping("/findByAllFilters/{startDate}/{endDate}")
	public List<TransferPalletMissionDetailsEntity> findByAllFilters(@PathVariable String startDate,
			@PathVariable String endDate) {
		List<TransferPalletMissionDetailsEntity> findByAllFilters = transferPalletMissionDetailsServiceInstance
				.findByAllFilters(startDate, endDate);
		return findByAllFilters;
	}

}