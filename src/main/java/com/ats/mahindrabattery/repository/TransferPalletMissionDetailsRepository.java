package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.mahindrabattery.entity.TransferPalletMissionDetailsEntity;


public interface TransferPalletMissionDetailsRepository extends JpaRepository<TransferPalletMissionDetailsEntity,Integer>{


	List<TransferPalletMissionDetailsEntity> findByPalletCodeAndPalletInformationId(String palletCode,
			int palletInformationId);

	List<TransferPalletMissionDetailsEntity> findByPalletCodeAndPalletInformationIdAndPositionIdAndPositionNameAndTransferPositionIdAndTransferPositionName(
			String palletCode, int palletInformationId, int positionId, String positionName, int transferPositionId,
			String transferPositionName);

	List<TransferPalletMissionDetailsEntity> findByTransferPositionNameAndIsMissionGenerated(String destinationPositionName, int i);
	List<TransferPalletMissionDetailsEntity> findByPositionNameAndIsMissionGenerated(String PositionName, int i);


	List<TransferPalletMissionDetailsEntity> findTransferPalletMissionBycDateTime(String currentDateTime);
	
	
	@Query(value = "select * from ats_wms_transfer_pallet_mission_details where cDateTime BETWEEN :startDate AND :endDate",nativeQuery = true)
	List<TransferPalletMissionDetailsEntity> findTransferPalletDetailsBetweenDates(String startDate, String endDate);

	
	@Query(value = "select * from ats_wms_transfer_pallet_mission_details where cast(CDateTime as date) =cast(:startDate as date) ",nativeQuery = true)
	List<TransferPalletMissionDetailsEntity> findByCDatetime(String startDate);

	//List<TransferPalletMissionDetailsEntity> findBycDatetimeBetween(String string, String string2);


	


}
