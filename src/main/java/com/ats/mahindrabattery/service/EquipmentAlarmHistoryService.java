package com.ats.mahindrabattery.service;

import java.util.List;
import java.util.Optional;

import com.ats.mahindrabattery.entity.EquipmentAlarmHistoryEntity;

public interface EquipmentAlarmHistoryService  {
    
	public List<EquipmentAlarmHistoryEntity> findAllEquipmentAlarmHistory() ;
	
	public List<EquipmentAlarmHistoryEntity> findAllEquipmentAlaramStatus();
	
	public List<EquipmentAlarmHistoryEntity> findByWmsEquipmentAlarmHistoryId(int equipmentAlarmHistoryId) ;
	
	public List<EquipmentAlarmHistoryEntity> findAllEquipmentByAlarmOccuredAndResolvedDateIsNull();
	
	public List<EquipmentAlarmHistoryEntity> findAllEquipmentByAlarmOccuredAndResolvedDateIsNullByStacker(
			int equipmentId);
	
	public List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmHistoryId(int alarmId) ;
	
	public List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmName(String alarmName);
	
	public List<EquipmentAlarmHistoryEntity> findByEquipmentName(String equipmentName);
	
	public List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmOccuredDateTimeBetween(
			String alarmOccuredStartDateTime, String alarmOccuredEndDateTime);
	
	public List<EquipmentAlarmHistoryEntity> findByHistoryAlarmResolvedDateBetween(String alarmResolvedDatetimeStart,
			String alarmResolvedDatetimeEnd);
	
	public List<EquipmentAlarmHistoryEntity> findByEquipmentNameAndEquipmentAlarmOccurredDatetimeBetween(
			String equipmentName, String alarmOccuredDatetimeStart, String alarmOccuredDatetimeEnd);
	
	public List<EquipmentAlarmHistoryEntity> findByEquipmentNameAndAlarmResolvedDateBetween(String equipmentName,
			String alarmResolveDatetimeStart, String alarmResolveDatetimeEnd);
	
	public Optional<EquipmentAlarmHistoryEntity> findHistoryAlarmHistoryDetailsById(int alarmHistoryId) ;
	
	public List<EquipmentAlarmHistoryEntity> findByAllFilters(String alarmOccuredDatetimeStart,
			String alarmOccuredDatetimeEnd, String equipmentName);
	
	public List<EquipmentAlarmHistoryEntity> findByCDatetimeEquipmentAlarmHistory();
	
	public List<EquipmentAlarmHistoryEntity> findByAllFiltersDetails(String startDate, String endDate,
			String equipmentName) ;
	
	public List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmOccurredDatetimeOrderByEquipmentAlarmResolvedDatetimeAsc();
	
	public List<EquipmentAlarmHistoryEntity> findAllEquipmentAlarmOccuredAndResolvedDateIsNullByEquipmentId();
	
	public List<EquipmentAlarmHistoryEntity> findByAlarmZone1();
	
	public List<EquipmentAlarmHistoryEntity> findByAlarmZone2();
	
	public List<EquipmentAlarmHistoryEntity> findByAlarmZone3();
}
