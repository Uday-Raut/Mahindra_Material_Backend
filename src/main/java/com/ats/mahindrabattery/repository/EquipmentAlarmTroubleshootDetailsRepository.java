package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.mahindrabattery.entity.EquipmentAlarmTroubleshootDetailsEntity;


public interface EquipmentAlarmTroubleshootDetailsRepository extends JpaRepository <EquipmentAlarmTroubleshootDetailsEntity,Integer> {

	List<EquipmentAlarmTroubleshootDetailsEntity> findByEquipmentAlarmId(int equipmentAlarmId);

	List<EquipmentAlarmTroubleshootDetailsEntity> findAllByalarmResolveSteps(String alarmResolveSteps);

}
