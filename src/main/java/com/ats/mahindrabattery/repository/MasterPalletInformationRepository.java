package com.ats.mahindrabattery.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.mahindrabattery.entity.MasterPalletInformationEntity;

@Transactional
public interface MasterPalletInformationRepository extends JpaRepository<MasterPalletInformationEntity, Integer> {

	// List<MasterPalletInformationEntity> findByPalletInformationId(int
	// palletInformationId);

	List<MasterPalletInformationEntity> findByPalletCode(String palletCode);

	List<MasterPalletInformationEntity> findAllByOrderByPalletInformationIdDesc();

	List<MasterPalletInformationEntity> findByPalletCodeOrderByPalletInformationIdDesc(String palletCode);

	@Modifying
	@Query("update MasterPalletInformationEntity u set u.palletStatusId =:palletStatusId, u.palletStatusName = :palletStatusName, u.stationWorkdone =:stationWorkdone  where u.palletInformationId = :palletInformationId")
	void updatePalletStatusNameBypalletInformationId(@Param(value = "palletStatusId") int palletStatusId,
			@Param(value = "palletStatusName") String palletStatusName,
			@Param(value = "stationWorkdone") int stationWorkdone,
			@Param(value = "palletInformationId") int palletInformationId);

	@Modifying
	@Query("update MasterPalletInformationEntity u set u.stationWorkdone =:stationWorkdone where u.palletInformationId = :palletInformationId ")
	void updatePalletStationWorkdoneBypalletInformationId(@Param(value = "stationWorkdone") int stationWorkdone,
			@Param(value = "palletInformationId") int palletInformationId);

	@Query(value = "SELECT palletInformationId FROM MasterPalletInformationEntity list  where list.palletCode=:palletCode and list.isInfeedMissionGenerated=:i and list.isOutfeedMissionGenerated=:j ORDER BY list.palletInformationId desc")
	List<Integer> findByPalletCodeAndIsInfeedMissionGeneratedAndIsOutfeedMissionGenerated(String palletCode, int i,
			int j);

	@Modifying
	@Query("update MasterPalletInformationEntity m set m.palletCode =:palletCode,serialNumber =:serialNumber,m.productId =:productId,m.productName =:productName,m.productVariantId =:productVariantId,m.productVariantName =:productVariantName,m.productVariantCode =:productVariantCode,m.quantity =:quantity,m.palletStatusId =:palletStatusId,m.palletStatusName =:palletStatusName,m.qualityStatus =:qualityStatus,m.isInfeedMissionGenerated =:isInfeedMissionGenerated,m.isOutfeedMissionGenerated =:isOutfeedMissionGenerated,m.isTransferManagementMissionGenerated =:isTransferManagementMissionGenerated,m.stationWorkdone =:stationWorkdone,m.cdatetime =:cdatetime,m.palletInformationIsDeleted =:palletInformationIsDeleted where m.palletInformationId =:palletInformationId")
	void updateMasterPalletInformation(int palletInformationId, String palletCode, int serialNumber, int productId,
			String productName, int productVariantId, String productVariantName, String productVariantCode,
			int quantity, int palletStatusId, String palletStatusName, String qualityStatus,
			int isInfeedMissionGenerated, int isOutfeedMissionGenerated, int isTransferManagementMissionGenerated,
			int stationWorkdone, String cdatetime, int palletInformationIsDeleted);

	public MasterPalletInformationEntity findByPalletInformationId(int palletInformationId);

	public MasterPalletInformationEntity findTopByOrderByPalletInformationIdDesc();
}
