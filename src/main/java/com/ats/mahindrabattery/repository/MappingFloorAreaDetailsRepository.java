package com.ats.mahindrabattery.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.mahindrabattery.entity.MappingFloorAreaDetailsEntity;

@Transactional
public interface MappingFloorAreaDetailsRepository extends JpaRepository<MappingFloorAreaDetailsEntity, Integer> {

	List<MappingFloorAreaDetailsEntity> findByAreaId(int areaId);

	@Modifying
	@Query("update MappingFloorAreaDetailsEntity u set u.infeedIsActive = :infeedIsActive, u.outfeedIsActive =:outfeedIsActive  where u.areaId = :areaId")
	void updateInfeedIsActiveAndOutfeedIsActiveByareaId(@Param(value = "infeedIsActive") int infeedIsActive,
			@Param(value = "outfeedIsActive") int outfeedIsActive, @Param(value = "areaId") int areaId);

	// void updateInfeedIsActiveAndOutfeedIsActiveByareaId(int infeedIsActive, int
	// outfeedIsActive, int areaId);
	List<MappingFloorAreaDetailsEntity> findByAreaIdAndFloorId(int areaId, int floorId);

	List<MappingFloorAreaDetailsEntity> findByAreaIdAndFloorName(int areaId, String floorName);

	@Query(value = "select * from ats_wms_mapping_floor_area_details where AREA_ID =:areaId AND FLOOR_NAME =:floorName", nativeQuery = true)
	MappingFloorAreaDetailsEntity getByAreaIdAndFloorName(int areaId, String floorName);

}
