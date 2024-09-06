package com.ats.mahindrabattery.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.mahindrabattery.entity.OutfeedMissionRuntimeDetailsEntity;

@Transactional
public interface OutfeedMissionruntimeDetailsRepository
		extends JpaRepository<OutfeedMissionRuntimeDetailsEntity, Integer> {

	public List<OutfeedMissionRuntimeDetailsEntity> findByCreatedDatetime(String createdDatetime);

	@Query(value = "select * FROM ats_wms_outfeed_mission_runtime_details where OUTFEED_MISSION_CDATETIME BETWEEN :startDate AND :endDate", nativeQuery = true)
	public List<OutfeedMissionRuntimeDetailsEntity> findOutfeedMissionRuntimeDetailsBetweenDates(String startDate,
			String endDate);

	@Modifying
	@Query("SELECT u FROM OutfeedMissionRuntimeDetailsEntity u WHERE u.outfeedMissionStatus = :outfeedMissionStatus1  OR u.outfeedMissionStatus= :outfeedMissionStatus2")
	List<OutfeedMissionRuntimeDetailsEntity> findByOutfeedMissionStatus(String outfeedMissionStatus1,
			String outfeedMissionStatus2);

	public List<OutfeedMissionRuntimeDetailsEntity> findBycreatedDatetimeBetweenAndOutfeedMissionIsDeleted(
			String startDate, String endDate, int outfeedMissionIsDeleted);

	public List<OutfeedMissionRuntimeDetailsEntity> findBycreatedDatetimeBetweenAndOutfeedMissionIsDeletedAndProductVariantName(
			String startDate, String endDate, int outfeedMissionIsDeleted, String productVariantName);

	public List<OutfeedMissionRuntimeDetailsEntity> findBycreatedDatetimeBetweenAndOutfeedMissionIsDeletedAndProductName(
			String string, String string2, int i, String string3);

	public List<OutfeedMissionRuntimeDetailsEntity> findBycreatedDatetimeBetweenAndOutfeedMissionIsDeletedAndOutfeedMissionStatusAndPalletStatusIdNot(
			String string, String string2, int i, String string3, int j);

	public List<OutfeedMissionRuntimeDetailsEntity> findBycreatedDatetimeBetweenAndOutfeedMissionIsDeletedAndProductNameAndOutfeedMissionStatus(
			String string, String string2, int i, String string3, String string4);

}
