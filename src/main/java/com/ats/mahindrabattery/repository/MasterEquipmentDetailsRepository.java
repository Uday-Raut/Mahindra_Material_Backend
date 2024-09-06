package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.mahindrabattery.entity.MasterEquipmentDetailsEntity;
 

public interface MasterEquipmentDetailsRepository extends JpaRepository<MasterEquipmentDetailsEntity,Integer> {

	List<MasterEquipmentDetailsEntity> findByEquipmentIsDeleted(int stackerIsDeleted);



	

}