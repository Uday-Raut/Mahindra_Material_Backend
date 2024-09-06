package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.mahindrabattery.entity.MasterEquipmentSensorDetailsEntity;


public interface MasterEquipmentSensorDetailsRepository extends JpaRepository < MasterEquipmentSensorDetailsEntity,Integer>{

	List<MasterEquipmentSensorDetailsEntity> findAllByEquipmentSensorIsDeleted(int i);

	List<MasterEquipmentSensorDetailsEntity> findBySensorDiagramView(String sensorDiagramView);

}
