package com.ats.mahindrabattery.controller;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.metrics.buffering.StartupTimeline;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import com.ats.mahindrabattery.entity.CurrentPalletStockDetailsEntity;
import com.ats.mahindrabattery.entity.GenerateManualRetrievalOrderEntity;
import com.ats.mahindrabattery.entity.MasterPalletInformationEntity;
import com.ats.mahindrabattery.entity.MasterPositionDetailsEntity;
import com.ats.mahindrabattery.repository.CurrentPalletStockDetailsRepository;
import com.ats.mahindrabattery.repository.MasterPalletInformationRepository;
import com.ats.mahindrabattery.repository.MasterPositionDetailsRepository;
import com.ats.mahindrabattery.response.ResponseHandler;
import com.ats.mahindrabattery.serviceimpl.CurrentPalletStockDetailsServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/currentPalletStockDetails")
//@EnableCaching
public class CurrentPalletStockDetailsController {

	@Autowired
	private CurrentPalletStockDetailsServiceImpl CurrentPalletStockDetailsServiceInstance;

	@Autowired
	private CurrentPalletStockDetailsRepository currentStockDetailsRepository;

	@Autowired
	private MasterPositionDetailsRepository masterPositionDetailsRepositoryInstance;

	@Autowired
	MasterPalletInformationRepository masterPalletInformationRepositoryInstance;

//	@GetMapping("/fetchAllCurrentPalletStockDetails")
//	// @Cacheable(cacheNames = "allCurrentPalletStockDetailsCache")
//	public List<CurrentPalletStockDetailsEntity> fetchAllCurrentPalletStockDetails() {
//		long startTime = System.nanoTime();
//
//		List<CurrentPalletStockDetailsEntity> result = CurrentPalletStockDetailsServiceInstance
//				.findAllCurrentPalletStockDetails();
//
//		long endTime = System.nanoTime();
//		long totalTime = endTime - startTime;
//		System.out.println("Total Time taken by process is ::  " + totalTime);
//		return result;
//	}

	@GetMapping("/data")
	public Page<CurrentPalletStockDetailsEntity> fetchAllCurrentPalletStockDetails(
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return CurrentPalletStockDetailsServiceInstance.findAllByPalletCodeNotNA(pageable);

	}

	@PostMapping("/addOrUpdateMasterPalletInformation")
	public ResponseEntity<Object> addOrUpdateMasterPalletInformation(
			@RequestBody CurrentPalletStockDetailsEntity currentPalletStockDetailsEntity) {
		return CurrentPalletStockDetailsServiceInstance
				.addOrUpdateMasterPalletInformation(currentPalletStockDetailsEntity);
	}

	@PutMapping("/addCurrentStockDataByPositionId")
	public ResponseEntity<CurrentPalletStockDetailsEntity> addCurrentStockDataByPositionEmpty(
			@RequestBody CurrentPalletStockDetailsEntity currentStockDetailsEntityInstance) {

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		String currentDate = ft.format(dNow);
		System.out.println("currentStockDetailsEntityInstance");
		CurrentPalletStockDetailsEntity currentStockDetailsEntityInsrt = new CurrentPalletStockDetailsEntity();

		List<MasterPalletInformationEntity> palletInfoDescOrderList = null;
		System.out.println("palletInfoDescOrderList=1");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		palletInfoDescOrderList = masterPalletInformationRepositoryInstance
				.findByPalletCodeOrderByPalletInformationIdDesc(currentStockDetailsEntityInstance.getPalletCode());
		System.out.println(palletInfoDescOrderList.size());

		int palletinfoID = palletInfoDescOrderList.get(0).getPalletInformationId();

		String productvariantCodeStrChr = currentStockDetailsEntityInstance.getProductVariantCode();

		System.out.println("palletInfoDescOrderList");
		System.out.println(palletInfoDescOrderList.get(0).getPalletInformationId());
		currentStockDetailsEntityInsrt = currentStockDetailsRepository
				.getByPositionId(currentStockDetailsEntityInstance.getPositionId());
		if (currentStockDetailsEntityInsrt != null) {
			currentStockDetailsEntityInstance.setIsInfeedMissionGenerated(1);
			currentStockDetailsEntityInstance.setModelNumber("NA");
			currentStockDetailsEntityInstance.setIsOutfeedMissionGenerated(0);
			currentStockDetailsEntityInstance.setQualityStatus("NA");
			currentStockDetailsEntityInstance.setPalletStatusname("Full");
			currentStockDetailsEntityInstance.setPalletStatusId(1);
			currentStockDetailsEntityInstance.setAgeingDays(0);
			currentStockDetailsEntityInstance.setLocation("NA");
			currentStockDetailsEntityInstance.setSerialNumber(0);
			currentStockDetailsEntityInstance.setLoadDatetime(currentDate);
			currentStockDetailsEntityInstance
					.setPalletInformationId(palletInfoDescOrderList.get(0).getPalletInformationId());

			return new ResponseEntity<CurrentPalletStockDetailsEntity>(
					currentStockDetailsRepository.save(currentStockDetailsEntityInstance), HttpStatus.OK);
		} else {
			return new ResponseEntity<CurrentPalletStockDetailsEntity>(new CurrentPalletStockDetailsEntity(),
					HttpStatus.ALREADY_REPORTED);
		}

	}

	// delete data from current stock table against position id
	@PutMapping("/deleteCurrentStockDetailsByPositionId/{positionId}")
	public ResponseEntity<Object> deletCurrentStockDetailsByPalletCode(@PathVariable int positionId) {

//		CurrentPalletStockDetailsEntity currentStockDetailsEntityInsrt = new CurrentPalletStockDetailsEntity();
		CurrentPalletStockDetailsEntity	currentStockDetailsEntityInsrt = currentStockDetailsRepository.getByPositionId(positionId);
		if (currentStockDetailsEntityInsrt != null) {
			currentStockDetailsEntityInsrt.setPalletCode("NA");
			currentStockDetailsEntityInsrt.setProductName("NA");
			currentStockDetailsEntityInsrt.setPalletInformationId(0);
			currentStockDetailsEntityInsrt.setProductVariantCode("NA");
			currentStockDetailsEntityInsrt.setProductId(0);
			currentStockDetailsEntityInsrt.setProductVariantId(0);
			currentStockDetailsEntityInsrt.setProductVariantName("NA");
			currentStockDetailsEntityInsrt.setPalletStatusId(0);
			currentStockDetailsEntityInsrt.setSerialNumber(0);
			currentStockDetailsEntityInsrt.setPalletStatusname("NA");
			currentStockDetailsEntityInsrt.setQualityStatus("NA");
			currentStockDetailsEntityInsrt.setAgeingDays(0);
			currentStockDetailsEntityInsrt.setBatchNumber("NA");
			currentStockDetailsEntityInsrt.setModelNumber("NA");
			currentStockDetailsEntityInsrt.setLocation("NA");

			currentStockDetailsEntityInsrt.setIsInfeedMissionGenerated(0);
			currentStockDetailsEntityInsrt.setIsOutfeedMissionGenerated(0);
			currentStockDetailsEntityInsrt.setQuantity(0);

			currentStockDetailsEntityInsrt.setProductId(0);
			currentStockDetailsEntityInsrt.setUserId(0);
			currentStockDetailsEntityInsrt.setUserName("NA");

			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
			String date = ft.format(dNow);

			currentStockDetailsEntityInsrt.setLoadDatetime(date);
			currentStockDetailsRepository.save(currentStockDetailsEntityInsrt);

			// Retrieve MasterPositionDetailsEntity
			MasterPositionDetailsEntity masterPositionDetailsEntity = masterPositionDetailsRepositoryInstance
					.findByPositionId(positionId);

			if (masterPositionDetailsEntity != null) {
				// Update fields in MasterPositionDetailsEntity
				// masterPositionDetailsEntity.setPositionIsActive(1);
				masterPositionDetailsEntity.setEmptyPalletPosition(1);
				masterPositionDetailsEntity.setIsManualDispatch(0);
				masterPositionDetailsEntity.setPositionIsAllocated(0);

				// Save the updated MasterPositionDetailsEntity
				masterPositionDetailsRepositoryInstance.save(masterPositionDetailsEntity);

				MasterPalletInformationEntity findByPalletInformationId = masterPalletInformationRepositoryInstance
						.findByPalletInformationId(currentStockDetailsEntityInsrt.getPalletInformationId());
				findByPalletInformationId.setIsInfeedMissionGenerated(0);
				findByPalletInformationId.setIsOutfeedMissionGenerated(0);
				MasterPalletInformationEntity save = masterPalletInformationRepositoryInstance
						.save(findByPalletInformationId);
				System.out.println("save::" + save);
				currentStockDetailsRepository.save(currentStockDetailsEntityInsrt);

				return ResponseHandler.generateResponse("Pallet Code deleted Successfully.", HttpStatus.OK,
						currentStockDetailsEntityInsrt);
			} else {
				return ResponseHandler.generateResponse("Pallet Code not deleted.", HttpStatus.ALREADY_REPORTED, null);

			}
		}
		return null;
	}

//	@Cacheable(cacheNames = "CurrentPalletStockDetailsCache", key = "#currentPalletStockDetailsId", unless = "#result == null")
	@GetMapping("/findByCurrentPalletStockDetailsId/{currentPalletStockDetailsId}")
	public CurrentPalletStockDetailsEntity findByCurrentPalletStockDetailsId(
			@PathVariable int currentPalletStockDetailsId) {
		return CurrentPalletStockDetailsServiceInstance.findbycurrentPalletStockDetailsId(currentPalletStockDetailsId);
	}

//	@Cacheable(cacheNames = "CurrentPalletStockDetailsCache", key = "#palletCode", unless = "#result == null")
	@GetMapping("/fetchAllCurrentPalletStockDetailsByPalletCode/{palletCode}")
	public List<CurrentPalletStockDetailsEntity> findByPalletCode(@PathVariable String palletCode) {
		return CurrentPalletStockDetailsServiceInstance.findBypalletCode(palletCode);
	}

//	@Cacheable(cacheNames = "CurrentPalletStockDetailsCache", key = "#quantity", unless = "#result == null")
	@GetMapping("/fetchRemainingAndPartialQtyByQuantity/{quantity}")
	public List<CurrentPalletStockDetailsEntity> fetchRemainingAndPartialQtyByQuantity(@PathVariable int quantity) {
		return CurrentPalletStockDetailsServiceInstance.findByQuantity(quantity);
	}

//	@Cacheable(cacheNames = "CurrentPalletStockDetailsCache", key = "#positionName", unless = "#result == null")
	@GetMapping("/fetchCurrentPalletStockDetailsByPositionName/{positionName}")
	public List<CurrentPalletStockDetailsEntity> fetchCurrentPalletStockDetailsByPositionName(
			@PathVariable String positionName) {
		return CurrentPalletStockDetailsServiceInstance.findAllByPositionName(positionName);
	}

	@PostMapping("/updateCurrentPalletStockDetailsUnloadingOperation/{palletCode}/{prodVariantCode}/{qty}/{binLocation}/{userId}/{userName}")
	public ResponseEntity<List<CurrentPalletStockDetailsEntity>> unloadingOperationDetails(
			@PathVariable String palletCode, @PathVariable String prodVariantCode, @PathVariable int qty,
			@PathVariable String binLocation, @PathVariable int userId, @PathVariable String userName) {

		return CurrentPalletStockDetailsServiceInstance.unloadingOperationDetails(palletCode, prodVariantCode, qty,
				binLocation, userId, userName);

	}

	@PutMapping("/deleteCurrentPalletStockDetailsUnloadingOperationByProductVariantCodeAndPalletCodeAndCurrentPalletStockDetailsId/{productVariantCode}/{palletCode}/{currentPalletStockDetailsId}")
	public void deleteCurrentPalletStockDetailsUnloadingOperationByCurrentPalletStockDetailsId(
			@PathVariable String productVariantCode, @PathVariable String palletCode,
			@PathVariable int currentPalletStockDetailsId) {

		CurrentPalletStockDetailsServiceInstance.deleteByProductVariantCodeAndPalletCodeAndCurrentPalletStockDetailsId(
				productVariantCode, palletCode, currentPalletStockDetailsId);

	}

//	@Cacheable(cacheNames = "CurrentPalletStockDetailsCache", key = "#areaName", unless = "#result == null")
	@GetMapping("/fetchAllCurrentPalletStockDetailsbyAreaName/{areaName}")
	public List<CurrentPalletStockDetailsEntity> fetchAllCurrentPalletStockDetailsByAreaName(
			@PathVariable String areaName) {
		return CurrentPalletStockDetailsServiceInstance.findByAreaName(areaName);
	}

//	@Cacheable(cacheNames = "CurrentPalletStockDetailsCache", key = "#positionId", unless = "#result == null")
	@GetMapping("/fetchByPositionId/{positionId}")
	public List<CurrentPalletStockDetailsEntity> fetchByPositionId(@PathVariable int positionId) {
		return CurrentPalletStockDetailsServiceInstance.findByPositionId(positionId);
	}

	@PutMapping("/updateCurrentStockDetailsByPositionId/{quantity}/{positionId}/{palletCode}/{productVariantCode}")
	public CurrentPalletStockDetailsEntity updateCurrentStockDetails(
			@RequestBody CurrentPalletStockDetailsEntity currentPalletStockDetailsEntity, @PathVariable int quantity,
			@PathVariable int positionId, @PathVariable String palletCode, @PathVariable String productVariantCode) {
		return CurrentPalletStockDetailsServiceInstance.updateQuantityAndPalletCodeAndproductVariantCodeByPositionId(
				currentPalletStockDetailsEntity, quantity, positionId, palletCode, productVariantCode);

	}

	@PutMapping("/deleteCurrentPalletStockDetailsByCurrentStockId/{currentPalletStockDetailsId}")
	public void deleteCurrentPalletStockDetailsByCurrentStockId(@PathVariable int currentPalletStockDetailsId) {

		CurrentPalletStockDetailsServiceInstance.deleteByCurrentPalletStockDetailsId(currentPalletStockDetailsId);

	}
//	@PutMapping("/deleteCurrentStockDetailsByPositionId/{positionId}")
//	public ResponseEntity<Object> deletCurrentStockDetailsByPalletCode(
//		@PathVariable int positionId){
//			return CurrentPalletStockDetailsServiceInstance.deletCurrentStockDetailsByPalletCode(positionId);
//	}

//	@GetMapping("/findByAllFiltersDetails/{startDate}/{endDate}/{productVariantCode}/{floor}/{area}/{productName}")
//	public List<CurrentPalletStockDetailsEntity> findByAllFiltersDetails(@PathVariable String startDate,
//			@PathVariable String endDate, @PathVariable String productVariantCode, @PathVariable String floor,
//			@PathVariable String area, @PathVariable String productName) {
//
//		return CurrentPalletStockDetailsServiceInstance.findByAllFilters(startDate, endDate, productVariantCode, floor,
//				area, productName);
//	}

//	@GetMapping("/findByAllFiltersDetails/{startDate}/{endDate}/{productVariantCode}/{floor}/{area}/{productName}")
//	public Page<CurrentPalletStockDetailsEntity> findByAllFiltersDetails(
//			@PathVariable String startDate,
//			@PathVariable String endDate, @PathVariable String productVariantCode, @PathVariable String floor,
//			@PathVariable String area, @PathVariable String productName,
//	        @RequestParam(defaultValue = "0") int page,
//	        @RequestParam(defaultValue = "10") int size) {
//
//	    Pageable pageable = PageRequest.of(page, size);
//	    return CurrentPalletStockDetailsServiceInstance.findByAllFilters(startDate, endDate, productVariantCode, floor, area, productName, pageable);
//	}

//	@GetMapping("/findByAllFiltersDetails/{startDate}/{endDate}/{productVariantCode}/{floor}/{area}/{productName}/{palletStatusname}")
//	public List<CurrentPalletStockDetailsEntity> findByAllFiltersDetails(
//			@PathVariable String startDate,
//			@PathVariable String endDate, @PathVariable String productVariantCode, @PathVariable String floor,
//			@PathVariable String area, @PathVariable String productName ,@PathVariable String palletStatusname
//	      ) {
//
//	    return CurrentPalletStockDetailsServiceInstance.findByAllFilters(startDate, endDate, productVariantCode, floor, area, productName,palletStatusname);
//	}
	@GetMapping("/findByAllFiltersDetails/{startDate}/{endDate}/{productVariantCode}/{floor}/{area}/{productName}/{palletStatusname}")
	public List<CurrentPalletStockDetailsEntity> findByAllFiltersDetails(@PathVariable String startDate,
			@PathVariable String endDate, @PathVariable String productVariantCode, @PathVariable String floor,
			@PathVariable String area, @PathVariable String productName, @PathVariable String palletStatusname) {
		return CurrentPalletStockDetailsServiceInstance.findByAllFilters(startDate, endDate, productVariantCode, floor,
				area, productName, palletStatusname);
	}

	@PutMapping("/update/{currentPalletStockDetailsId}")
	public CurrentPalletStockDetailsEntity updateCurrentPalletStockDetails(
			@PathVariable int currentPalletStockDetailsId,
			@RequestBody CurrentPalletStockDetailsEntity currentPalletStockDetailsEntity) {
		return CurrentPalletStockDetailsServiceInstance.updateCurrentPalletStockDetails(currentPalletStockDetailsId,
				currentPalletStockDetailsEntity);

	}

//	@Cacheable(cacheNames = "loadDatetimeCache")
	@GetMapping("/findByLoadDatetime")
	public List<CurrentPalletStockDetailsEntity> findByLoadDatetime() {

		return CurrentPalletStockDetailsServiceInstance.findByLoadDateTime();
	}

	@PostMapping("/addCurrentPalletStockDetails")
	public ResponseEntity<Object> addCurrentPalletStockDetails(
			@RequestBody CurrentPalletStockDetailsEntity currentPalletStockDetailsEntity) {

		ResponseEntity<Object> currentPalletDetails = CurrentPalletStockDetailsServiceInstance
				.addCurrentPalletStockDetails(currentPalletStockDetailsEntity);
		return currentPalletDetails;

	}

	@PutMapping("/updateCurrentStockDetails")
	public ResponseEntity<Object> updateCurrentStockDetails(
			@RequestBody CurrentPalletStockDetailsEntity currentPalletStockDetailsEntity) {

		ResponseEntity<Object> updateCurrentStockDetails = CurrentPalletStockDetailsServiceInstance
				.updateCurrentStockDetails(currentPalletStockDetailsEntity);
		return updateCurrentStockDetails;
	}

	@GetMapping("/findBEVCurrentStockDetails")
	public int findBEVCurrentStockDetails() {
		return CurrentPalletStockDetailsServiceInstance.findBEVCurrentStockDetails();
	}

	@GetMapping("/findS230CurrentStockDetails")
	public int findS230CurrentStockDetails() {
		return CurrentPalletStockDetailsServiceInstance.findS230CurrentStockDetails();
	}
//	
//	
//	@GetMapping("/findEmptyPalletCountCurrentStockDetails")
//	public int findBevEmptyPalletCountCurrentStockDetails() {
//		return CurrentPalletStockDetailsServiceInstance.findBevEmptyPalletCountCurrentStockDetails();
//	}

//	@GetMapping("/currentStockNOKBySerialNumber/{dispatchOrderNumber}/{startSerialNumber}/{endSerialNumber}")
//	public List<CurrentPalletStockDetailsEntity> dispatchNokCurrentStock(
//			@RequestParam(name = "dispatchOrderNumber", required = false) String dispatchOrderNumber,
//			@RequestParam String startSerialNumber, @PathVariable String endSerialNumber) {
//		List<CurrentPalletStockDetailsEntity> dispatchNokCurrentStock = CurrentPalletStockDetailsServiceInstance
//				.dispatchNokCurrentStock(dispatchOrderNumber, startSerialNumber, endSerialNumber);
//		return dispatchNokCurrentStock;
//	}

	@GetMapping("/findByBEV")
	public Page<CurrentPalletStockDetailsEntity> findByBEV(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return CurrentPalletStockDetailsServiceInstance.findByBEV(pageable);
	}

	@GetMapping("/findByS230")
	public Page<CurrentPalletStockDetailsEntity> findByS230(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return CurrentPalletStockDetailsServiceInstance.findByS230(pageable);
	}

//	@GetMapping("/findByOkAndBev")
//	public List<CurrentPalletStockDetailsEntity> findByOkAndBev(@RequestParam(defaultValue = "0") int page,
//			@RequestParam(defaultValue = "10") int size) {
//		Pageable pageable = PageRequest.of(page, size);
//		List<CurrentPalletStockDetailsEntity> findByOkAndBev = CurrentPalletStockDetailsServiceInstance
//				.findByOkAndBev(pageable);
//		return findByOkAndBev;
//	}
//
//	@GetMapping("/findByOkAndS230")
//	public List<CurrentPalletStockDetailsEntity> findByOkAndS230(@RequestParam(defaultValue = "0") int page,
//			@RequestParam(defaultValue = "10") int size) {
//		Pageable pageable = PageRequest.of(page, size);
//		List<CurrentPalletStockDetailsEntity> findByOkAndS230 = CurrentPalletStockDetailsServiceInstance
//				.findByOkAndS230(pageable);
//		return findByOkAndS230;
//	}

//	@GetMapping("/findByNOkAndBEV")
//	public List<CurrentPalletStockDetailsEntity> findByNOkAndBEV(@RequestParam(defaultValue = "0") int page,
//			@RequestParam(defaultValue = "10") int size) {
//		Pageable pageable = PageRequest.of(page, size);
//	 List<CurrentPalletStockDetailsEntity> findByNOkAndBEV = CurrentPalletStockDetailsServiceInstance.findByNOkAndBEV(pageable);
//	 return findByNOkAndBEV;
////		return CurrentPalletStockDetailsServiceInstance.findByNOkAndBEV(pageable);
//
//	}
//
//	@GetMapping("/findByNOkAndS230")
//	public List<CurrentPalletStockDetailsEntity> findByNOkAndS230(@RequestParam(defaultValue = "0") int page,
//			@RequestParam(defaultValue = "10") int size) {
//		Pageable pageable = PageRequest.of(page, size);
//		return CurrentPalletStockDetailsServiceInstance.findByNOkAndS230(pageable);
//	}

	@GetMapping("/BevOkMaterialByPage")
	public Page<CurrentPalletStockDetailsEntity> BevOkMaterialByPage(
			@RequestParam(defaultValue = "OK") String qualityStatus, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		System.out.println("data getting..."
				+ CurrentPalletStockDetailsServiceInstance.findByQualityStatus(qualityStatus, pageable));
		return CurrentPalletStockDetailsServiceInstance.findByQualityStatus(qualityStatus, pageable);
	}

	@GetMapping("/BevNOkMaterialByPage")
	public Page<CurrentPalletStockDetailsEntity> BevNOkMaterialByPage(
			@RequestParam(defaultValue = "NOK") String qualityStatus, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		System.out.println("data getting..."
				+ CurrentPalletStockDetailsServiceInstance.findByQualityStatus(qualityStatus, pageable));
		return CurrentPalletStockDetailsServiceInstance.findByQualityStatus(qualityStatus, pageable);
	}

	@GetMapping("/S230OkMaterialByPage")
	public Page<CurrentPalletStockDetailsEntity> S230OkMaterialByPage(
			@RequestParam(defaultValue = "OK") String qualityStatus, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		System.out.println("data getting..."
				+ CurrentPalletStockDetailsServiceInstance.findByQualityStatusS230(qualityStatus, pageable));
		return CurrentPalletStockDetailsServiceInstance.findByQualityStatusS230(qualityStatus, pageable);
	}

	@GetMapping("/S230NOkMaterialByPage")
	public Page<CurrentPalletStockDetailsEntity> S230NOkMaterialByPage(
			@RequestParam(defaultValue = "NOK") String qualityStatus, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		System.out.println("data getting..."
				+ CurrentPalletStockDetailsServiceInstance.findByQualityStatusS230(qualityStatus, pageable));
		return CurrentPalletStockDetailsServiceInstance.findByQualityStatusS230(qualityStatus, pageable);
	}

	@GetMapping("/findEmptyPalletList")
	public Page<CurrentPalletStockDetailsEntity> findEmptyPalletList(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		System.out.println("data getting..." + CurrentPalletStockDetailsServiceInstance.findEmptyPalletList(pageable));
		return CurrentPalletStockDetailsServiceInstance.findEmptyPalletList(pageable);
	}

//	@GetMapping("/findByserialNumberBetween/{serialNumber1}/{serialNumber2}/{dispatchOrderNumber}/{shiftId}/{shiftName}")
//	public ResponseEntity<Object> findByserialNumberBetween(@PathVariable int serialNumber1,
//			@PathVariable int serialNumber2,@PathVariable String dispatchOrderNumber,@PathVariable int shiftId,@PathVariable String shiftName) {
//		ResponseEntity<Object> findByserialNumberBetween = CurrentPalletStockDetailsServiceInstance
//				.findByserialNumberBetween(serialNumber1, serialNumber2,dispatchOrderNumber,shiftId,shiftName);
//		return findByserialNumberBetween;
//		
//	}

//	@GetMapping("/findByserialNumberBetween1")
//	public ResponseEntity<Object> findByserialNumberBetween1(@RequestBody CurrentPalletStockDetailsEntity currentPalletStockDetailsEntity) {
//		ResponseEntity<Object> findByserialNumberBetween = CurrentPalletStockDetailsServiceInstance
//				.findByserialNumberBetween(currentPalletStockDetailsEntity);
//		return findByserialNumberBetween;	
//	}
//	
//	
//	@GetMapping("/findByserialNumberBetween2")
//	public ResponseEntity<Object> findByserialNumberBetween2(@RequestBody CurrentPalletStockDetailsEntity currentPalletStockDetailsEntity) {
//		 ResponseEntity<Object> findByserialNumberBetween1 = CurrentPalletStockDetailsServiceInstance
//				.findByserialNumberBetween1(currentPalletStockDetailsEntity);
//		return findByserialNumberBetween1;
//	}
//	

	private Page<CurrentPalletStockDetailsEntity> CurrentPalletStockDetailsServiceInstancefindByProductVariantCodeAndQuantityAndpalletStatusIdAndPalletCodeNot(
			String string, int i, int j, String string2, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@GetMapping("/findByserialNumberBetween/{serialNumber1}/{serialNumber2}/{dispatchOrderNumber}/{shiftId}/{shiftName}")
	public ResponseEntity<Object> findByserialNumberBetween(@PathVariable int serialNumber1,
			@PathVariable int serialNumber2, @PathVariable String dispatchOrderNumber, @PathVariable int shiftId,
			@PathVariable String shiftName) {
		ResponseEntity<Object> findByserialNumberBetween = CurrentPalletStockDetailsServiceInstance
				.findByserialNumberBetween(serialNumber1, serialNumber2, dispatchOrderNumber, shiftId, shiftName);
		return findByserialNumberBetween;
	}

	@GetMapping("/findByserialNumberBetween1/{serialNumber1}/{serialNumber2}/{dispatchOrderNumber}/{shiftId}/{shiftName}")
	public ResponseEntity<Object> findByserialNumberBetween1(@PathVariable int serialNumber1,
			@PathVariable int serialNumber2, @PathVariable String dispatchOrderNumber, @PathVariable int shiftId,
			@PathVariable String shiftName) {
		ResponseEntity<Object> findByserialNumberBetween = CurrentPalletStockDetailsServiceInstance
				.findByserialNumberBetween1(serialNumber1, serialNumber2, dispatchOrderNumber, shiftId, shiftName);
		return findByserialNumberBetween;
	}

	@GetMapping("/getPalletCode/{palletCode}")
	public ResponseEntity<List<CurrentPalletStockDetailsEntity>> findBypalletCode1(@PathVariable String palletCode) {
		ResponseEntity<List<CurrentPalletStockDetailsEntity>> response = CurrentPalletStockDetailsServiceInstance
				.findBypalletCode1(palletCode);
		return response;
	}

//	@GetMapping("/findBEVCurrentStockDetails1")
//	public int findBEVCurrentStockDetails1() {
//		 int findBEVCurrentStockDetails = CurrentPalletStockDetailsServiceInstance.findBEVCurrentStockDetails();
//		 return findBEVCurrentStockDetails;
//	}

}
