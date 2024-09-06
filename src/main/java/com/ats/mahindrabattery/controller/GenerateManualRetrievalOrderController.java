package com.ats.mahindrabattery.controller;

import java.util.List;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

import com.ats.mahindrabattery.entity.GenerateManualRetrievalOrderEntity;
import com.ats.mahindrabattery.entity.InfeedMissionRuntimeDetailsEntity;
import com.ats.mahindrabattery.entity.MasterProductVariantDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.GenerateManualRetrievalOrderServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/generateManualRetrievalOrder")
public class GenerateManualRetrievalOrderController {

	@Autowired
	private GenerateManualRetrievalOrderServiceImpl generateManualRetrievalOrderService;

	@PostMapping("/addMannualRetrivalOrder")
	public ResponseEntity<Object> addMannualRetrivalDetails(
			@RequestBody GenerateManualRetrievalOrderEntity generateManualRetrievalOrderEntity) {

		ResponseEntity<Object> mannualRetrivalDetails = generateManualRetrievalOrderService
				.createMannualRetrivalDetails(generateManualRetrievalOrderEntity);

		return mannualRetrivalDetails;

	}

	@PostMapping("/addMannualRetrivalOrder1")
	public ResponseEntity<Object> addMannualRetrivalDetails1(
			@RequestBody GenerateManualRetrievalOrderEntity generateManualRetrievalOrderEntity) {

		ResponseEntity<Object> mannualRetrivalDetails = generateManualRetrievalOrderService
				.createMannualRetrivalDetails1(generateManualRetrievalOrderEntity);

		return mannualRetrivalDetails;

	}

	@GetMapping("/fetchAllDispatchOrderNumberDetails")

	public List<GenerateManualRetrievalOrderEntity> fetchAllDispatchOrderNumberDetails() {
		List<GenerateManualRetrievalOrderEntity> dispatchOrderNumber = generateManualRetrievalOrderService
				.getAllDispatchOrdernumberDetails();
		return dispatchOrderNumber;
	}

	@GetMapping("/getAllMannualDispatchOrderOfCurrentDate")
	public List<GenerateManualRetrievalOrderEntity> getAllMannualDispatchOrder() {
		List<GenerateManualRetrievalOrderEntity> allMannualDispatchOrder = generateManualRetrievalOrderService
				.getAllMannualRetrivalDetails();
		return allMannualDispatchOrder;
	}
	
//	@GetMapping("/getAllMannualDispatchOrderOfCurrentDate")
//    public List<GenerateManualRetrievalOrderEntity> getAllMannualDispatchOrder(
//            @RequestParam(defaultValue = "0") int page, 
//            @RequestParam(defaultValue = "10") int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<GenerateManualRetrievalOrderEntity> allMannualDispatchOrder = generateManualRetrievalOrderService.getAllMannualRetrivalDetails(pageable);
//        return allMannualDispatchOrder.getContent();
//    }

	@GetMapping("/findbetweendates/{startDate}/{endDate}")
	public List<GenerateManualRetrievalOrderEntity> findAllMannualDispatchOrderBetweenDates(
			@PathVariable String startDate, @PathVariable String endDate) {
		List<GenerateManualRetrievalOrderEntity> findAllMannualDispatchOrderBetweenDates = generateManualRetrievalOrderService
				.findAllMannualDispatchOrdersByDate(startDate, endDate);
		return findAllMannualDispatchOrderBetweenDates;
	}

	@GetMapping("/findbydispatchOrderNumber/{dispatchOrderNumber}")
	public List<GenerateManualRetrievalOrderEntity> findByDispatchOrderNumber(
			@PathVariable String dispatchOrderNumber) {
		List<GenerateManualRetrievalOrderEntity> findByDispatchOrderNumber = generateManualRetrievalOrderService
				.findByMannualDispatchNumber(dispatchOrderNumber);
		return findByDispatchOrderNumber;
	}

	@GetMapping("/fetchMannualDispatchDetailsByAllFilters/{cDatetimeStart}/{cDatetimeEnd}/{dispatchOrderNumber}/{productVariantCode}/{shiftName}")
	public List<GenerateManualRetrievalOrderEntity> findByAllFiltersDetails(@PathVariable String cDatetimeStart,
			@PathVariable String cDatetimeEnd, @PathVariable String dispatchOrderNumber,
			@PathVariable String productVariantCode, @PathVariable String shiftName) {
		return generateManualRetrievalOrderService.findByAllFilters(cDatetimeStart, cDatetimeEnd, dispatchOrderNumber,
				productVariantCode, shiftName);
	}

	@PutMapping("/updateDispatchButton")
	public ResponseEntity<String> updateDispatchStart(@RequestBody GenerateManualRetrievalOrderEntity data) {
		// Assuming dispatchService can handle the update
		generateManualRetrievalOrderService.updateDispatchStart(data);
		return new ResponseEntity<>("Updated", HttpStatus.OK);
	}

	@GetMapping("/createMannualRetrivalDetailsBySerialNumber")
	public ResponseEntity<Object> createMannualRetrivalDetailsBySerialNumber(
			@RequestBody List<GenerateManualRetrievalOrderEntity> generateManualRetrievalOrderEntity) {
		ResponseEntity<Object> createMannualRetrivalDetailsBySerialNumber = generateManualRetrievalOrderService
				.createMannualRetrivalDetailsBySerialNumber(generateManualRetrievalOrderEntity);
		return createMannualRetrivalDetailsBySerialNumber;
	}

//	@PostMapping("/createMannualRetrivalDetailsBySerialNumber")
//    public ResponseEntity<Object> createMannualRetrivalDetailsBySerialNumber(
//            @RequestParam int serialNumber1,
//            @RequestParam int serialNumber2) {
//        ResponseEntity<Object> response = generateManualRetrievalOrderService.createMannualRetrivalDetailsBySerialNumber(serialNumber1, serialNumber2);
//        return response;
//    }

	@PutMapping(value = "/updateOrderCancelledStatus/{dispatchHistoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateOrderCancelledStatus(@PathVariable int dispatchHistoryId,
			@RequestBody int isOrderCancelled) {
		generateManualRetrievalOrderService.updateOrderCancelledStatus(dispatchHistoryId, isOrderCancelled);
		return ResponseEntity.ok().body("{\"message\": \"Order Cancelled Status Updated Successfully\"}");
	}

	@PutMapping(value = "/updateOrderIsDeleted/{dispatchHistoryId}")
	public ResponseEntity<String> updateOrderIsDeleted(@PathVariable int dispatchHistoryId) {
		generateManualRetrievalOrderService.updateOrderIsDeleted(dispatchHistoryId);
		return ResponseEntity.ok().body(
				"{\"message\": \"Order Deleted Status Updated Successfully\" : \"The deleted order will not be retrieved. Are you sure you want to delete?\"}");
	}

}
