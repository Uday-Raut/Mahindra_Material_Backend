package com.ats.mahindrabattery.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.mahindrabattery.entity.MasterPositionDetailsEntity;

@Transactional
public interface MasterPositionDetailsRepository extends JpaRepository<MasterPositionDetailsEntity, Integer> {

	MasterPositionDetailsEntity findByPositionId(int positionId);

//	List<MasterPositionDetailsEntity> findByPositionNameAndPositionIsAllocatedAndEmptyPalletPositionAndPositionIsActive(
//			String positionName, int positionIsAllocated, int emptyPalletPosition, int positionIsActive);

//	List<MasterPositionDetailsEntity> findByAreaIdAndFloorIdOrderByPositionName(int areaId,int floorId);
	List<MasterPositionDetailsEntity> findByAreaIdAndFloorIdOrderByPositionId(int areaId, int floorId);

	List<MasterPositionDetailsEntity> findByAreaId(int areaId);

	MasterPositionDetailsEntity findByPositionNameAndPositionIsAllocatedAndEmptyPalletPositionAndPositionIsActive(
			String positionName, int positionIsAllocated, int emptyPalletPosition, int positionIsActive);

//	@Modifying
//	@Query("update MasterPositionDetailsEntity u set u.isMaterialLoaded = :isMaterialLoaded where u.positionId = :positionId")
//	void updateisMaterialLoadedBypositionId(@Param(value = "isMaterialLoaded") int isMaterialLoaded,
//			@Param(value = "positionId") int positionId);

//	MasterPositionDetailsEntity findByPositionName(String binLocation);

	@Modifying
	@Query("update MasterPositionDetailsEntity u set u.isManualDispatch = :isManualDispatch where u.positionId = :positionId")
	void updateisManualDispatchBypositionId(@Param(value = "isManualDispatch") int isManualDispatch,
			@Param(value = "positionId") int positionId);

	@Modifying
	@Query("update MasterPositionDetailsEntity u set u.positionIsAllocated =:positionIsAllocated, u.emptyPalletPosition =:emptyPalletPosition, u.isManualDispatch =:isManualDispatch where u.positionId =:positionId")
	void updateMasterPositionDetails(int positionIsAllocated, int emptyPalletPosition, int isManualDispatch,
			int positionId);

	List<MasterPositionDetailsEntity> findByPositionName(String positionName);

	List<MasterPositionDetailsEntity> findByRackIdAndPositionIdLessThanAndEmptyPalletPositionAndPositionIsActiveAndIsManualDispatch(
			int rackId, int positionId, int emptyPalletPosition, int positionIsActive, int isManualDispatch);

	@Query("select m from MasterPositionDetailsEntity m where m.emptyPalletPosition = 1")
	public List<MasterPositionDetailsEntity> findByEmptyPalletPosition();


//	List<MasterPositionDetailsEntity> findByIsAlarmRack(int i);

	public MasterPositionDetailsEntity findByPositionIdAndPositionIsActive(int i, int j);

	List<MasterPositionDetailsEntity> findByPositionIdNot(Integer positionId);

	




	
	
	

	
}