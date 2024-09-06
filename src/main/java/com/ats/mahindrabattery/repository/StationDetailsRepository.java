package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.mahindrabattery.entity.StationDetailsEntity;
 

public interface StationDetailsRepository extends JpaRepository<StationDetailsEntity,Integer> {

	List<StationDetailsEntity> findByWmsStationId(int wmsStationId);

}
