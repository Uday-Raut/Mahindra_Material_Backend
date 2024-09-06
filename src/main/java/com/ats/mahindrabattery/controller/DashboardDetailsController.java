package com.ats.mahindrabattery.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.CurrentPalletStockDetailsEntity;
import com.ats.mahindrabattery.entity.DashboardDetailsEntity;
import com.ats.mahindrabattery.entity.InfeedMissionRuntimeDetailsEntity;
import com.ats.mahindrabattery.entity.OutfeedMissionRuntimeDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.DashboardDetailsServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/dashboardDetails")
public class DashboardDetailsController {

	private static final String String = null;
	@Autowired
	private DashboardDetailsServiceImpl DashboardDetailsServiceInstance;

	@GetMapping("/getbydate")
	public List<CurrentPalletStockDetailsEntity> getByDate() {
		List<CurrentPalletStockDetailsEntity> getByDate = DashboardDetailsServiceInstance.getByDate();
		return getByDate;
	}

//	@GetMapping("/findCurrentStockCountByCurrentDate")
//	public int findCurrectStockCountByCDatetime() {
//		int findCurrentStockCountByCurrentDate=DashboardDetailsServiceInstance.findCurrentStockCountByCurrentDatetime();
//		return findCurrentStockCountByCurrentDate;
//	}

//	 @Autowired
//		private DashboardDetailsRepository dashboardDetailsRepositoryInstance;
//	   
//	    

//	@Scheduled(fixedRate = 60000)
	@GetMapping("/fetchAllDashboardDetails")
	public Optional<DashboardDetailsEntity> fetchAlDashboardDetails() {
		return DashboardDetailsServiceInstance.findAllDashboarsDetails();
	}

//	@GetMapping("/addDashboardDetails")
//	public ResponseEntity<String> addDashboardDetails() {
//		return DashboardDetailsServiceInstance.addDashboardDetails();
//	}

	@GetMapping("/fetchDateTime")
	public List<String> fetchDateTime() throws ParseException {
		return DashboardDetailsServiceInstance.fetchFormattedDates();
	}

	@GetMapping("/fetchProductionTrendDetails")
	public List<DashboardDetailsEntity> fetchProductionTrendDetails() {
		return DashboardDetailsServiceInstance.fetchProductionTrendDetails();
	}

//	@GetMapping("/getbydate")
//	public List<InfeedMissionRuntimeDetailsEntity> getByDate(){
//		List<InfeedMissionRuntimeDetailsEntity> getByDate = infeedMissionRuntimeDetailsService.getByDate();
//		return getByDate;
//	}
	@GetMapping("/getBevOutfeedDetailsByDate") // this method is for current details finding
	public List<OutfeedMissionRuntimeDetailsEntity> getBevOutfeedDetailsByDate() {
		List<OutfeedMissionRuntimeDetailsEntity> getBevOutfeedDetailsByDate = DashboardDetailsServiceInstance
				.getBevOuteedDetailsByDate();
		return getBevOutfeedDetailsByDate;
	}

	@GetMapping("/getS230OutfeedDetailsByDate") // this method is for current details finding
	public List<OutfeedMissionRuntimeDetailsEntity> getS230OutfeedDetailsByDate() {
		List<OutfeedMissionRuntimeDetailsEntity> getS230OutfeedDetailsByDate = DashboardDetailsServiceInstance
				.getS230OuteedDetailsByDate();
		return getS230OutfeedDetailsByDate;
	}

	@GetMapping("/findbevOutfeedDetailsByCurrentDate")
	public int findbevOutfeedDetailsByCurrentDate() {
		int findbevOutfeedDetailsByCurrentDate = DashboardDetailsServiceInstance.findbevOutfeedDetailsByCurrentDate();
		return findbevOutfeedDetailsByCurrentDate;
	}

	@GetMapping("/findS230OutfeedDetailsByCurrentDate")
	public int findS230OutfeedDetailsByCurrentDate() {
		int finds230OutfeedDetailsByCurrentDate = DashboardDetailsServiceInstance.finds230OutfeedDetailsByCurrentDate();
		return finds230OutfeedDetailsByCurrentDate;
	}

	@GetMapping("/getS230InfeedDetailsByDate") // this method is for details finding
	public List<InfeedMissionRuntimeDetailsEntity> getS230InfeedDetailsByDate() {
		List<InfeedMissionRuntimeDetailsEntity> getS230InfeedDetailsByDate = DashboardDetailsServiceInstance
				.getS230InfeedDetailsByDate();
		return getS230InfeedDetailsByDate;
	}

	@GetMapping("/findS230InfeedDetailsByCurrentDate")
	public int finds230InfeedDetailsByCurrentDate() {
		int finds230InfeedDetailsByCurrentDate = DashboardDetailsServiceInstance.finds230InfeedDetailsByCurrentDate();
		return finds230InfeedDetailsByCurrentDate;
	}

	@GetMapping("/getBevInfeedDetailsByDate") // this method is for details finding
	public List<InfeedMissionRuntimeDetailsEntity> getBevInfeedDetailsByDate() {
		List<InfeedMissionRuntimeDetailsEntity> getBevInfeedDetailsByDate = DashboardDetailsServiceInstance
				.getBevInfeedDetailsByDate();
		return getBevInfeedDetailsByDate;
	}

	@GetMapping("/findBEVInfeedDetailsByCurrentDate") // this method is for count finding
	public int findBEVInfeedDetailsByCurrentDate() {
		int findBEVInfeedDetailsByCurrentDate = DashboardDetailsServiceInstance.findBEVInfeedDetailsByCurrentDate();
		return findBEVInfeedDetailsByCurrentDate;
	}

	@GetMapping("/getOkCurrentStockDetails") // this method is for details finding
	public List<CurrentPalletStockDetailsEntity> getOkCurrentStockDetailsByDate() {
		List<CurrentPalletStockDetailsEntity> getOkCurrentStockDetailsByDate = DashboardDetailsServiceInstance
				.getOkMaterialCurrentStockDetailsByDate();
		return getOkCurrentStockDetailsByDate;
	}
//	@GetMapping("/getOkCurrentStockDetails")
//	public Page<CurrentPalletStockDetailsEntity> getOkCurrentStockDetailsByDate(
//	        @RequestParam(defaultValue = "0") int page,
//	        @RequestParam(defaultValue = "10") int size) {
//	    Pageable pageable = PageRequest.of(page, size);
//	    return DashboardDetailsServiceInstance.getOkMaterialCurrentStockDetailsPage(pageable);
//	}

	@GetMapping("/getNOkCurrentStockDetails") // this method is for details finding
	public List<CurrentPalletStockDetailsEntity> getNOkCurrentStockDetailsByDate() {
		List<CurrentPalletStockDetailsEntity> getNOkCurrentStockDetailsByDate = DashboardDetailsServiceInstance
				.getNOkMaterialCurrentStockDetailsByDate();
		return getNOkCurrentStockDetailsByDate;
	}

	@GetMapping("/findOkCurrentStockByCurrentDate") // total currentstock of current datecount by status OK
	public int findOkCurrentStockByCurrentDate() {
		int findOkCurrentStockByCurrentDate = DashboardDetailsServiceInstance.findOkCurrentStockByCurrentDate();
		return findOkCurrentStockByCurrentDate;
	}

	@GetMapping("/findNOkCurrentStockByCurrentDate") // total currentstock of current datecount by status NOK
	public int findNOkCurrentStockByCurrentDate() {
		int findNokCurrentStockByCurrentDate = DashboardDetailsServiceInstance.findNokCurrentStockByCurrentDate();
		return findNokCurrentStockByCurrentDate;
	}

	@GetMapping("/findEquipmentAlarmHistoryByCurrentDate")
	public int findEquipmentAlarmHistoryByCDatetime() {
		int findEquipmentAlarmHistoryByCurrentDate = DashboardDetailsServiceInstance
				.findEquipmentAlarmHistoryByCDatetime();
		return findEquipmentAlarmHistoryByCurrentDate;
	}

//	@GetMapping("/findAllPickListDetails")
//	public void findAllPickListDetails() {
//		DashboardDetailsServiceInstance.findAllPickListDetails();
//	}

	@GetMapping("/getInfeedDetailsByDate")
	public int getInfeedDetailsByDate() {
		int infeedDetailsByDate = DashboardDetailsServiceInstance.getInfeedDetailsByDate();
		return infeedDetailsByDate;
	}

	@GetMapping("/getOutfeedDetailsByDate")
	public int getOutfeedDetailsByDate() {
		int outfeedDetailsByDate = DashboardDetailsServiceInstance.getOutfeedDetailsByDate();
		return outfeedDetailsByDate;
	}

//	@GetMapping("/findTotalCurrentStockDetails")  //total  currentstock count by pallet not NA
//	public int findTotalCurrentStockDetails() {
//		int findTotalCurrentStockDetails = DashboardDetailsServiceInstance.findTotalCurrentStockDetails();
//		return findTotalCurrentStockDetails;
//	}

	@GetMapping("/getNOkBEVMaterialCurrentStockDetails") // total currentstock NOK BEV count
	public int getNOkBEVMaterialCurrentStockDetails() {
		int nOkMaterialCurrentStockDetails = DashboardDetailsServiceInstance.getNOkBEVMaterialCurrentStockDetails();
		return nOkMaterialCurrentStockDetails;
	}

	@GetMapping("/getOkBEVMaterialCurrentStockDetails") // total currentstock OK BEV count
	public int getOkBEVMaterialCurrentStockDetails() {
		int OkMaterialCurrentStockDetails = DashboardDetailsServiceInstance.getOkBEVMaterialCurrentStockDetails();
		return OkMaterialCurrentStockDetails;
	}

	@GetMapping("/getNOkS230MaterialCurrentStockDetails") // total currentstock NOK S230 count
	public int getNOkS230MaterialCurrentStockDetails() {
		int nOkMaterialCurrentStockDetails = DashboardDetailsServiceInstance.getNOkS230MaterialCurrentStockDetails();
		return nOkMaterialCurrentStockDetails;
	}

	@GetMapping("/getOkS230MaterialCurrentStockDetails") // total currentstock OK S230 count
	public int getOkS230MaterialCurrentStockDetails() {
		int OkMaterialCurrentStockDetails = DashboardDetailsServiceInstance.getOkS230MaterialCurrentStockDetails();
		return OkMaterialCurrentStockDetails;
	}

	@GetMapping("/OkMaterialByPage")
	public Page<CurrentPalletStockDetailsEntity> findByQualityStatus(
			@RequestParam(defaultValue = "OK") String qualityStatus, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		System.out.println(
				"data getting..." + DashboardDetailsServiceInstance.findByQualityStatus(qualityStatus, pageable));
		return DashboardDetailsServiceInstance.findByQualityStatus(qualityStatus, pageable);
	}

	@GetMapping("/NOkMaterialByPage")
	public Page<CurrentPalletStockDetailsEntity> findByQualityStatusNok(
			@RequestParam(defaultValue = "NOK") String qualityStatus, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		System.out.println(
				"data getting..." + DashboardDetailsServiceInstance.findByQualityStatus(qualityStatus, pageable));
		return DashboardDetailsServiceInstance.findByQualityStatus(qualityStatus, pageable);
	}

	@GetMapping("/totalOrdersCount")
	public int fetchTotalOrderDetailsByCurrentDate() {
		return DashboardDetailsServiceInstance.findTotalOrderDetailsByCurrentDate();
	}

	@GetMapping("/executedOrder")
	public int findExecutedOrder() {
		return DashboardDetailsServiceInstance.findExecutedOrder();
	}

	@GetMapping("/openOrders")
	public int fetchdispatchStatus() {
		return DashboardDetailsServiceInstance.openOrders();

	}

	@GetMapping("/percentageOrders")
	public String percentageOrders() {
		return DashboardDetailsServiceInstance.percentageOrders();
	}

	@GetMapping("/findAllDashboard")
	public DashboardDetailsEntity findAllDashboard() {
		return DashboardDetailsServiceInstance.findAllDashboard();
	}

}