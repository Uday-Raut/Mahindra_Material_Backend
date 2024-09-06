package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ats.mahindrabattery.entity.MasterSensorDetailsEntity;
import com.ats.mahindrabattery.entity.StackerSensorDetailsEntity;

@Transactional
public interface StackerSensorDetailsRepository extends JpaRepository<StackerSensorDetailsEntity, Integer> {
	
	 List<StackerSensorDetailsEntity> findByStackerSensorIsDeleted(int stackerSensorIsDeleted);
	 
	 
	 List<StackerSensorDetailsEntity> findByStackerId(int stackerId);

	

}
