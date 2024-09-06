package com.ats.mahindrabattery.controller;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.EquipmentAlarmHistoryEntity;
import com.ats.mahindrabattery.serviceimpl.EquipmentAlarmHistoryServiceImpl;



@CrossOrigin
@RestController
@RequestMapping("/equipmentAlarmHistory")
public class EquipmentAlarmHistoryController {

	@Autowired
	private EquipmentAlarmHistoryServiceImpl equipmentAlarmHistoryServiceInstance;
	
	@GetMapping("/fetchAllEquipmentAlarmHistory")
	public List<EquipmentAlarmHistoryEntity> findAllEqupmentAlarmHistoryDetails() {
		return equipmentAlarmHistoryServiceInstance.findAllEquipmentAlarmHistory();
	}


	
	@GetMapping("/fetchAllEquipmentAlaramStatus")
	public List<EquipmentAlarmHistoryEntity> findAllEquipmentAlaramStatus() {
		return equipmentAlarmHistoryServiceInstance.findAllEquipmentAlaramStatus();
	}

	
	@GetMapping("/fetchByWmsEquipmentAlarmHistoryId/{equipmentAlarmHistoryId}")
	public List<EquipmentAlarmHistoryEntity> findByWmsEquipmentAlarmHistoryId(@PathVariable int equipmentAlarmHistoryId){
		
		return equipmentAlarmHistoryServiceInstance.findByWmsEquipmentAlarmHistoryId(equipmentAlarmHistoryId);
	} 
	
	
	@GetMapping("/findAllEquipmentByAlarmOccuredAndResolvedDateIsNull")
	public List<EquipmentAlarmHistoryEntity> findAllEquipmentByAlarmOccuredAndResolvedDateIsNull() {
		return equipmentAlarmHistoryServiceInstance.findAllEquipmentByAlarmOccuredAndResolvedDateIsNull();
	}
	

	 
	@GetMapping("/findAllEquipmentByAlarmOccuredAndResolvedDateIsNullByStacker/{equipmentId}")
	public List<EquipmentAlarmHistoryEntity> findAllEquipmentByAlarmOccuredAndResolvedDateIsNullByStacker(@PathVariable int equipmentId) {
		System.out.println("findAllEquipmentByAlarmOccuredAndResolvedDateIsNullByStacker ::"+equipmentId);
		return equipmentAlarmHistoryServiceInstance.findAllEquipmentByAlarmOccuredAndResolvedDateIsNullByStacker(equipmentId);
			}
	
	@GetMapping("/findByEquipmentAlarmHistoryId/{alarmId}")
	public List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmHistoryId(@PathVariable int alarmId) {
		return equipmentAlarmHistoryServiceInstance.findByEquipmentAlarmHistoryId(alarmId);
	}

	@GetMapping("/findByEquipmentAlarmName/{alarmName}")
	public List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmNameDetails(@PathVariable String alarmName) {
		return equipmentAlarmHistoryServiceInstance.findByEquipmentAlarmName(alarmName);
	}
	
	

	@GetMapping("/findByEquipmentName/{equipmentName}")
	public List<EquipmentAlarmHistoryEntity> findByEquipmentNameDetails(@PathVariable String equipmentName) {
		return equipmentAlarmHistoryServiceInstance.findByEquipmentName(equipmentName);
	}

//	@GetMapping("/findByEquipmentAlarmOccurredDatetime/{alarmOccuredDateTime}")
//	public List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmOccurredDatetimeDetails(
//			@PathVariable String alarmOccuredDateTime) {
//		String startDateTime =  alarmOccuredDateTime.toString().replace("T", " ");
//	
//		return equipmentAlarmHistoryRepository.findByEquipmentAlarmOccurredDatetime(startDateTime);
//	}

	@GetMapping("/findByEquipmentAlarmOccuredDateTimeBetween/{alarmOccuredStartDateTime}/{alarmOccuredEndDateTime}")
	public List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmOccurredDatetimeBetweenDetails(
			@PathVariable String alarmOccuredStartDateTime, @PathVariable String alarmOccuredEndDateTime) {
		
		return equipmentAlarmHistoryServiceInstance.findByEquipmentAlarmOccuredDateTimeBetween(alarmOccuredStartDateTime, alarmOccuredEndDateTime);
	}



	@GetMapping("/findByHistoryAlarmResolvedDateBetween/{alarmResolvedDatetimeStart}/{alarmResolvedDatetimeEnd}")
	public List<EquipmentAlarmHistoryEntity> findByHistoryAlarmResolvedDateBetweenDetails(
			@PathVariable String alarmResolvedDatetimeStart, @PathVariable String alarmResolvedDatetimeEnd) {
		
		return equipmentAlarmHistoryServiceInstance.findByHistoryAlarmResolvedDateBetween(alarmResolvedDatetimeStart, alarmResolvedDatetimeEnd);
				
	}

	@GetMapping("/findByEquipmentNameAndEquipmentAlarmOccurredDatetimeBetween/{equipmentName}/{alarmOccuredDatetimeStart}/{alarmOccuredDatetimeEnd}")
	public List<EquipmentAlarmHistoryEntity> findByEquipmentNameAndAlarmOccuredDateBetweenDetails(
			@PathVariable String equipmentName, @PathVariable String alarmOccuredDatetimeStart,
			@PathVariable String alarmOccuredDatetimeEnd) {
		return equipmentAlarmHistoryServiceInstance.findByEquipmentNameAndEquipmentAlarmOccurredDatetimeBetween(equipmentName, alarmOccuredDatetimeStart, alarmOccuredDatetimeEnd);
	}

	@GetMapping("/findByEquipmentNameAndAlarmResolvedDateBetween/{equipmentName}/{alarmResolveDatetimeStart}/{alarmResolveDatetimeEnd}")
	public List<EquipmentAlarmHistoryEntity> findByEquipmentNameAndAlarmResolvedDateBetween(
			@PathVariable String equipmentName, @PathVariable String alarmResolveDatetimeStart,
			@PathVariable String alarmResolveDatetimeEnd) {
		
		return equipmentAlarmHistoryServiceInstance.findByEquipmentNameAndEquipmentAlarmOccurredDatetimeBetween(equipmentName, alarmResolveDatetimeStart, alarmResolveDatetimeEnd);
	}

	
	@GetMapping("/fetchHistoryAlarmHistoryDetailsById/{alarmHistoryId}")
	public Optional<EquipmentAlarmHistoryEntity> fetchHistoryAlarmHistoryDetailsById(@PathVariable int alarmHistoryId) {
		return equipmentAlarmHistoryServiceInstance.findHistoryAlarmHistoryDetailsById(alarmHistoryId);
	}

	@GetMapping("/findByAllFiltersDetails/{alarmOccuredDatetimeStart}/{alarmOccuredDatetimeEnd}/{equipmentName}")
	public List<EquipmentAlarmHistoryEntity> findByAllFiltersDetails(
			@PathVariable String alarmOccuredDatetimeStart, @PathVariable String alarmOccuredDatetimeEnd,
 @PathVariable String equipmentName) {
		return equipmentAlarmHistoryServiceInstance.findByAllFilters(alarmOccuredDatetimeStart, alarmOccuredDatetimeEnd,equipmentName);
	}
	
//	@GetMapping("/findByCdatetime")
//	public List<EquipmentAlarmHistoryEntity> findByCDateTimeEquipmentAlarmHistory(){
//		List<EquipmentAlarmHistoryEntity> equipmentAlarmHistoryDetailsByDate = equipmentAlarmHistoryServiceInstance.findByCDatetimeEquipmentAlarmHistory();
//		
//		return equipmentAlarmHistoryDetailsByDate;
//	}
//	
	@GetMapping("/findByEquipmentAlarmOccurredDatetime")
	public List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmOccurredDatetime(){
		return  equipmentAlarmHistoryServiceInstance.findByEquipmentAlarmOccurredDatetimeOrderByEquipmentAlarmResolvedDatetimeAsc();
	}
	
	@GetMapping("/findByAlarmZone1")
	public List<EquipmentAlarmHistoryEntity> findByAlarmZone1() {
		return equipmentAlarmHistoryServiceInstance.findByAlarmZone1();
	}
	
	@GetMapping("/findByAlarmZone2")
	public List<EquipmentAlarmHistoryEntity> findByAlarmZone2() {
		return equipmentAlarmHistoryServiceInstance.findByAlarmZone2();
	}
	
	@GetMapping("/findByAlarmZone3")
	public List<EquipmentAlarmHistoryEntity> findByAlarmZone3() {
		return equipmentAlarmHistoryServiceInstance.findByAlarmZone3();
	}

}
