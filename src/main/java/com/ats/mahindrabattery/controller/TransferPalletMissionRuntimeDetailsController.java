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

import com.ats.mahindrabattery.entity.GenerateManualRetrievalOrderEntity;
import com.ats.mahindrabattery.entity.TransferPalletMissionRuntimeDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.TransferPalletMissionRuntimeDetailsServiceImpl;


@RestController
@CrossOrigin
@RequestMapping("/transferPalletmissionruntimedetails")
public class TransferPalletMissionRuntimeDetailsController {
	
	@Autowired
	private TransferPalletMissionRuntimeDetailsServiceImpl transferPalletMissionRuntimeDetailsService;
	
	@GetMapping("/fetchTransferPalletMissionStatusRuntimeDetails")
	public List<TransferPalletMissionRuntimeDetailsEntity> fetchTransferPalletMissionRuntimeDetails() {
		
		return transferPalletMissionRuntimeDetailsService.fetchTransferPalletMissionRuntimeDetails();
	}
	
	
	@PutMapping("/updatePalletMissionRuntimeDetailsDetails")
	public void updatePalletMissionRuntimeDetailsDetails(@RequestBody TransferPalletMissionRuntimeDetailsEntity transferPalletMissionRuntimeDetailsEntity) {
		
		transferPalletMissionRuntimeDetailsService.updatePalletMissionRuntimeDetails(transferPalletMissionRuntimeDetailsEntity);
	}
	
	@GetMapping("/getAllTransferPalletMissionRuntimeDetails")
	public List<TransferPalletMissionRuntimeDetailsEntity> getAllTransferPalletMissionRuntimeDetails() {
		List<TransferPalletMissionRuntimeDetailsEntity> getAllTransferPalletMissionRuntimeDetails = transferPalletMissionRuntimeDetailsService
				.getAllTransferPalletMissionRuntimeDetails();
		return getAllTransferPalletMissionRuntimeDetails;
	}
	
	
@GetMapping("/fetchAllTransferPalletMissionDetails")
	
	public List<TransferPalletMissionRuntimeDetailsEntity> fetchAllTransferPalletMissionDetails(
			  ) {
		List<TransferPalletMissionRuntimeDetailsEntity> transferPalletMissionDetails = transferPalletMissionRuntimeDetailsService
				.getAllTransferPalletMissionDetailsDetails();
		return transferPalletMissionDetails;
	}
	@GetMapping("/fetchTransferPalletMissionRuntimeDetailsByAllFilters/{cDatetimeStart}/{cDatetimeEnd}/{palletCode}/{productVariantCode}")
	public List<TransferPalletMissionRuntimeDetailsEntity> findByAllFiltersDetails(@PathVariable String cDatetimeStart,
			@PathVariable String cDatetimeEnd, @PathVariable String palletCode,
			@PathVariable String productVariantCode) {
		return transferPalletMissionRuntimeDetailsService.findByAllFilters(cDatetimeStart, cDatetimeEnd, palletCode,
				productVariantCode);
	}
}
