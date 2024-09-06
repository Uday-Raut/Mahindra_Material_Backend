package com.ats.mahindrabattery.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.mahindrabattery.entity.MasterStationTagDetailsEntity;
 

public interface MasterStationTagDetailsRepository  extends JpaRepository<MasterStationTagDetailsEntity,Integer> {

	List<MasterStationTagDetailsEntity> findByStationId(int stationId);

	Optional<MasterStationTagDetailsEntity>findBycurrentValue(String currentValue);

	

}
