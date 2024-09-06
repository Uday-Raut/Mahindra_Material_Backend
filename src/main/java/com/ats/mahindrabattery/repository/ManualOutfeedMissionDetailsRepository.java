package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.mahindrabattery.entity.ManualOutfeedMissionDetailsEntity;
 

public interface ManualOutfeedMissionDetailsRepository extends JpaRepository<ManualOutfeedMissionDetailsEntity,Integer> {

	List<ManualOutfeedMissionDetailsEntity> findAll();


	List<ManualOutfeedMissionDetailsEntity> findByPalletInformationDetailsId(int palletInformationDetailsId);


	List<ManualOutfeedMissionDetailsEntity> findByPalletCodeAndIsMissionGenerated(String palletCode, int i);


	ManualOutfeedMissionDetailsEntity findByPalletInformationDetailsIdAndIsMissionGenerated(int palletInformationDetailsId, int i);



}
