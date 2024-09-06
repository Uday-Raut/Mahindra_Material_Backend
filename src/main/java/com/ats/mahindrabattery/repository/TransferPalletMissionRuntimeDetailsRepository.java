package com.ats.mahindrabattery.repository;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.mahindrabattery.entity.GenerateManualRetrievalOrderEntity;
import com.ats.mahindrabattery.entity.MasterProductDetailsEntity;
import com.ats.mahindrabattery.entity.TransferPalletMissionRuntimeDetailsEntity;






public interface TransferPalletMissionRuntimeDetailsRepository extends JpaRepository<TransferPalletMissionRuntimeDetailsEntity,Integer> {
	
	@Modifying
	@Query("SELECT u FROM TransferPalletMissionRuntimeDetailsEntity u WHERE u.transferMissionStatus = :transferMissionStatus1  OR u.transferMissionStatus= :transferMissionStatus2")
	List<TransferPalletMissionRuntimeDetailsEntity> findByTransferMissionStatus(String transferMissionStatus1,String transferMissionStatus2);

	
	@Modifying
	@Query("SELECT u FROM TransferPalletMissionRuntimeDetailsEntity u WHERE (u.previousPositionName= :previousPositionName1) and  (u.transferMissionStatus = :transferMissionStatus1  OR u.transferMissionStatus= :transferMissionStatus2)")
	List<TransferPalletMissionRuntimeDetailsEntity> findByPreviousPositionNameAndTransferMissionStatusIn(String previousPositionName1, String transferMissionStatus1,String transferMissionStatus2);
	@Query(value = "select * from ats_wms_transfer_pallet_mission_runtime_details where cdatetime BETWEEN :startDate AND :endDate", nativeQuery = true)
	public List<TransferPalletMissionRuntimeDetailsEntity> getAllGenerateManualRetrievalOrderBetweenDates(String startDate,
			String endDate);
	
	public List<TransferPalletMissionRuntimeDetailsEntity> findBycdatetimeBetween(String startDate, String endDate);
	public List<TransferPalletMissionRuntimeDetailsEntity> findBytransferMissionIsDeleted(int transferMissionIsDeleted);
}

