package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.mahindrabattery.entity.MasterRackDetailsEntity;
 

public interface MasterRackDetailsRepository extends JpaRepository<MasterRackDetailsEntity,Integer> {

	
	//List<MasterRackDetailsEntity> findByAreaIdAndFloorIdOrderByRackName(int areaId,int floorId);
	List<MasterRackDetailsEntity> findByAreaIdAndFloorIdOrderByRackId(int areaId,int floorId);
}
