package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.mahindrabattery.entity.EquipmentAlarmHistoryEntity;




public interface EquipmentAlarmHistoryRepository extends JpaRepository <EquipmentAlarmHistoryEntity,Integer>{

	List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmOccurredDatetimeBetween(String string, String string2);

	List<EquipmentAlarmHistoryEntity> findAllByEquipmentAlarmStatus(int i);

	List<EquipmentAlarmHistoryEntity> findByWmsEquipmentAlarmHistoryId(int equipmentAlarmHistoryId);

	List<EquipmentAlarmHistoryEntity> findAllByEquipmentAlarmResolvedDatetime(String object);

	List<EquipmentAlarmHistoryEntity> findAllByEquipmentAlarmResolvedDatetimeAndEquipmentName(Object object,
			String equipmentId);

	List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmId(int alarmId);

	List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmName(String alarmName);

	List<EquipmentAlarmHistoryEntity> findByEquipmentName(String equipmentName);

	List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmResolvedDatetimeBetween(String startDateTime,
			String endDateTime);

	List<EquipmentAlarmHistoryEntity> findByEquipmentNameAndEquipmentAlarmOccurredDatetimeBetween(String equipmentName,
			String startDateTime, String endDateTime);

	List<EquipmentAlarmHistoryEntity> findByEquipmentNameAndEquipmentAlarmResolvedDatetimeBetween(String equipmentName,
			String startDateTime, String endDateTime);

	@Query(value = "select * from ats_wms_equipment_alarm_history where Equipment_Alarm_Occurred_DateTime BETWEEN :startDate AND :endDate", nativeQuery = true)
	public List<EquipmentAlarmHistoryEntity> getEquipmentAlarmHistoryBetweenDates(String startDate, String endDate);

	List<EquipmentAlarmHistoryEntity> findByWmsEquipmentAlarmHistoryIdAndEquipmentAlarmResolvedDatetime(
			int equipmentAlarmHistoryId, Object object);

	

	
	

	List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmOccurredDatetimeOrderByEquipmentAlarmResolvedDatetimeAsc(
			String equipmentAlarmOccurredDatetime);

	List<EquipmentAlarmHistoryEntity> findAllByEquipmentAlarmResolvedDatetimeAndEquipmentId(Object object,
			int equipmentId);
	
	public List<EquipmentAlarmHistoryEntity> findByAlarmZone(String alarmZone);

//	List<EquipmentAlarmHistoryEntity> findByequipmentAlarmOccurredDatetimeOrderByEquipmentAlarmResolvedDatetimeAsc();

	
}
