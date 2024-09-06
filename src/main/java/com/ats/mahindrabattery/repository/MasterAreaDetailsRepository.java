package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.mahindrabattery.entity.MasterAreaDetailsEntity;
 

public interface MasterAreaDetailsRepository extends JpaRepository<MasterAreaDetailsEntity, Integer>{
	List<MasterAreaDetailsEntity> findByAreaId(int areaId);

	List<MasterAreaDetailsEntity> findByAreaIsDeleted(int i);
}
