package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.mahindrabattery.entity.MasterSensorDetailsEntity;



public interface IMasterSensorDetailsRepository extends JpaRepository<MasterSensorDetailsEntity,Integer>{
	List<MasterSensorDetailsEntity> findAll();
	List<MasterSensorDetailsEntity> findByWmsEquipmentId(int wmsEquipmentId);
	List<MasterSensorDetailsEntity> findAllByEquipmentSensorStatus(int equipmentSensorStatus);
	//List<MasterSensorDetailsEntity> findAllByIsBabySensor(int isBabySensor);
//	List<MasterSensorDetailsEntity> fetchByWmsEquipmentIdAndIsBabySensor(int wmsEquipmentId, int isBabySensor);
}