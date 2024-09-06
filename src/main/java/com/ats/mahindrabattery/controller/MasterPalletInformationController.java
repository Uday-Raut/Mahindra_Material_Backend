package com.ats.mahindrabattery.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.MasterPalletInformationEntity;
import com.ats.mahindrabattery.repository.MasterPalletInformationRepository;
import com.ats.mahindrabattery.serviceimpl.CurrentPalletStockDetailsServiceImpl;
import com.ats.mahindrabattery.serviceimpl.MasterPalletInformationServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/masterPalletInformationDetails")
public class MasterPalletInformationController {
	
	@Autowired
	private MasterPalletInformationServiceImpl masterPalletInformationServiceInstance;
	
	@Autowired
	private CurrentPalletStockDetailsServiceImpl CurrentPalletStockDetailsServiceInstance;
	
	@Autowired
	MasterPalletInformationRepository masterPalletInformationRepositoryInstance;

	@GetMapping("/fetchAllMasterPalletInformation")
	public List<MasterPalletInformationEntity> fetchAllMasterPalletInformation() {
		return masterPalletInformationServiceInstance.findAllMasterPalletInformation();
	}
	
	@PostMapping("/addMasterPalletInformationDetails")
	public void addMasterPalletInformationDetails(@RequestBody MasterPalletInformationEntity masterPalletInformationEntity){
		masterPalletInformationServiceInstance.addMasterPalletInformationDetails(masterPalletInformationEntity);
	}

//	@PutMapping("/addMasterPalletInformation")
//	public MasterPalletInformationEntity addMasterPalletInformation(
//			@RequestBody MasterPalletInformationEntity masterPalletInformationEntity) {
//		return masterPalletInformationServiceInstance.addMasterPalletInformation(masterPalletInformationEntity);
//	}
	
	 @PostMapping("/add/{palletCode}/{productVariantCode}")
	    public MasterPalletInformationEntity addMasterPalletInformation(
	            @RequestBody MasterPalletInformationEntity masterPalletInformationDetailsEntityInstance,
	            @PathVariable String palletCode, @PathVariable String productVariantCode) {

	        List<Integer> palletInfoListInDescendingOrder = masterPalletInformationRepositoryInstance
	                .findByPalletCodeAndIsInfeedMissionGeneratedAndIsOutfeedMissionGenerated(palletCode, 0, 0);

	        if (palletInfoListInDescendingOrder.size() == 0) {
	            try {
	                Date dNow = new Date();
	                SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
	                String currentDate = ft.format(dNow);

	                masterPalletInformationDetailsEntityInstance.setPalletCode(palletCode);
	                masterPalletInformationDetailsEntityInstance.setProductVariantCode(productVariantCode);
	                masterPalletInformationDetailsEntityInstance.setSerialNumber(0);
	                masterPalletInformationDetailsEntityInstance.setPalletStatusId(1);
	                masterPalletInformationDetailsEntityInstance.setPalletStatusName("Full");
	                masterPalletInformationDetailsEntityInstance.setIsInfeedMissionGenerated(0);
	                masterPalletInformationDetailsEntityInstance.setIsOutfeedMissionGenerated(0);
	                masterPalletInformationDetailsEntityInstance.setPalletInformationIsDeleted(0);
	                masterPalletInformationDetailsEntityInstance.setCdatetime(currentDate);

	                return masterPalletInformationRepositoryInstance.save(masterPalletInformationDetailsEntityInstance);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return masterPalletInformationDetailsEntityInstance;
	    }
	
	
	@GetMapping("/fetchPalletInformationIdDesc")
	public List<MasterPalletInformationEntity> fetchPalletInformationIdDesc() {
        return masterPalletInformationServiceInstance.findPalletInformationIdDesc();
        		
   }

	@PutMapping("/updatePalletInformationDetailsByPalletStatusName/{palletStatusId}/{palletStatusName}/{palletCode}/{stationWorkdone}")
	public List<MasterPalletInformationEntity> updatePalletInformationDetails(@PathVariable int palletStatusId , @PathVariable String palletStatusName,@PathVariable String palletCode,@PathVariable int stationWorkdone) {
	System.out.println("palletStatusName ::" +palletStatusName);
	System.out.println("palletCode ::" +palletCode);
		return masterPalletInformationServiceInstance.updatePalletInformationDetails(palletStatusId,palletStatusName,palletCode,stationWorkdone);
		
      
	}
	
	@PutMapping("/updatePalletInformationDetailsByStationWorkdone/{stationWorkdone}/{palletCode}")
	public List<MasterPalletInformationEntity>updatePalletInformationDetailsByStationWorkdone(@PathVariable int stationWorkdone,@PathVariable String palletCode){
		return masterPalletInformationServiceInstance.updatePalletInformationDetailsByStationWorkdone( stationWorkdone,palletCode);
	}
	
}
