package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.mahindrabattery.entity.MasterFloorDetailsEntity;
 

public interface MasterFloorDetailsRepository extends JpaRepository<MasterFloorDetailsEntity, Integer>{
	
	List<MasterFloorDetailsEntity> findByFloorId(int floorId);

}
