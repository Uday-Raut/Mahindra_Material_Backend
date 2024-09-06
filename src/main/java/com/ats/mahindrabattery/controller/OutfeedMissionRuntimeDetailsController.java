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

import com.ats.mahindrabattery.entity.OutfeedMissionRuntimeDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.OutfeedMissionRuntimeDetailsServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/outfeedmissionruntimedetails")
public class OutfeedMissionRuntimeDetailsController {

	@Autowired
	private OutfeedMissionRuntimeDetailsServiceImpl outfeedMissionRuntimeDetailsService;

	@GetMapping("/getall")
	public List<OutfeedMissionRuntimeDetailsEntity> getAllOutfeedMissionRuntimeDetails() {
		List<OutfeedMissionRuntimeDetailsEntity> findAllOutfeedMissionRuntimeDetails = outfeedMissionRuntimeDetailsService
				.getAllOutfeedMissionRuntimeDetails();
		return findAllOutfeedMissionRuntimeDetails;
	}
	
	@GetMapping("/findbydatetime/{outfeedMissionCreatedDateTime}")
	public List<OutfeedMissionRuntimeDetailsEntity> findBCreatedDateTime(@PathVariable String outfeedMissionCreatedDateTime){
		List<OutfeedMissionRuntimeDetailsEntity> findByoutfeedMissionCreatedDateTime = outfeedMissionRuntimeDetailsService.findByCreatedDateTime(outfeedMissionCreatedDateTime);
		return findByoutfeedMissionCreatedDateTime;
	}
	
	@GetMapping("/findbetweendates/{startDate}/{endDate}")
	public List<OutfeedMissionRuntimeDetailsEntity> findOutfeedMissionRuntimeDetailsBetweenDates(@PathVariable String startDate,@PathVariable String endDate){
		List<OutfeedMissionRuntimeDetailsEntity> findOutfeedNissionRuntimeDetailsBetweenDates = outfeedMissionRuntimeDetailsService.findOutfeedNissionRuntimeDetailsBetweenDates(startDate, endDate);
		return findOutfeedNissionRuntimeDetailsBetweenDates;
	}
	
	@GetMapping("/getbydate")
	public List<OutfeedMissionRuntimeDetailsEntity> getByDate(){
		List<OutfeedMissionRuntimeDetailsEntity> getByDate = outfeedMissionRuntimeDetailsService.getByDate();
		return getByDate;
	}
	@GetMapping("/fetchOutfeedMissionRuntimeDetails")
	public List<OutfeedMissionRuntimeDetailsEntity> fetchOutfeedMissionRuntimeDetails() {
		
		return outfeedMissionRuntimeDetailsService.fetchOutfeedMissionRuntimeDetails();
	}

	



	@PutMapping("/updateOutfeedMissionRuntimeDetailsDetails")
	public void updateOutfeedMissionRuntimeDetailsDetails(@RequestBody OutfeedMissionRuntimeDetailsEntity outfeedMissionRuntimeDetailsEntity) {
		
		outfeedMissionRuntimeDetailsService.updateOutfeedMissionRuntimeDetails(outfeedMissionRuntimeDetailsEntity);
}
	
	
	@GetMapping("/fetchOutfeedMissionRuntimeDetailsByAllFilters/{floorName}/{areaName}/{outfeedMissionStatus}/{outfeedMissionCdatetimeStart}/{outfeedMissionCdatetimeEnd}")
	public List<OutfeedMissionRuntimeDetailsEntity> fetchOutfeedMissionRuntimeDetailsByAllFilters(
			 @PathVariable String floorName,
			@PathVariable String areaName, @PathVariable String outfeedMissionStatus,
			@PathVariable String outfeedMissionCdatetimeStart, @PathVariable String outfeedMissionCdatetimeEnd) {
	
	List<OutfeedMissionRuntimeDetailsEntity> fetchOutfeedMissionRuntimeDetailsByAllFilters = outfeedMissionRuntimeDetailsService
			.findByAllFilters( floorName,areaName,outfeedMissionStatus,outfeedMissionCdatetimeStart,outfeedMissionCdatetimeEnd);
	return fetchOutfeedMissionRuntimeDetailsByAllFilters;
	
	
	}

}
