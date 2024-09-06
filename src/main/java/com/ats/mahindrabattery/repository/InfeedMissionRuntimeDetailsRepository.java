package com.ats.mahindrabattery.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.mahindrabattery.entity.InfeedMissionRuntimeDetailsEntity;
import com.ats.mahindrabattery.entity.OutfeedMissionRuntimeDetailsEntity;

@Transactional
public interface InfeedMissionRuntimeDetailsRepository
		extends JpaRepository<InfeedMissionRuntimeDetailsEntity, Integer> {

	// public List<InfeedMissionRuntimeDetailsEntity> findBycDateTime(String
	// cDateTime);

	// public List<InfeedMissionRuntimeDetailsEntity> findByfilledPercentage(int
	// filledPercentage);

	@Query(value = "select * from ats_wms_infeed_mission_runtime_details where INFEED_MISSION_CDATETIME BETWEEN :startDate AND :endDate", nativeQuery = true)
	public List<InfeedMissionRuntimeDetailsEntity> getAllInfeedMissionRuntimeDetailsBetweenDates(String startDate,
			String endDate);

	@Query(value = "select * FROM ats_wms_infeed_mission_runtime_details where INFEED_MISSION_CDATETIME BETWEEN :startDate AND :endDate", nativeQuery = true)
	// public List<InfeedMissionRuntimeDetailsEntity> findBycDatetimeBetween(String
	// startDate,String endDate);
	public List<InfeedMissionRuntimeDetailsEntity> findInfeedfeedMissionRuntimeDetailsBetweenDates(String startDate,
			String endDate);

	public List<InfeedMissionRuntimeDetailsEntity> findByinfeedMissionId(int infeedMissionId);

	@Modifying
	@Query("SELECT u FROM InfeedMissionRuntimeDetailsEntity u WHERE u.infeedMissionStatus = :infeedMissionStatus1  OR u.infeedMissionStatus= :infeedMissionStatus2")
	List<InfeedMissionRuntimeDetailsEntity> findByInfeedMissionStatus(String infeedMissionStatus1,
			String infeedMissionStatus2);

	public List<InfeedMissionRuntimeDetailsEntity> findByCreatedDatetime(String createdDatetime);

	public List<InfeedMissionRuntimeDetailsEntity> findBycreatedDatetimeBetweenAndInfeedMissionIsDeleted(
			String startDate, String endDate, int infeedMissionIsDeleted);

	public List<InfeedMissionRuntimeDetailsEntity> findBycreatedDatetimeBetweenAndInfeedMissionIsDeletedAndProductVariantName(
			String startDate, String endDate, int infeedMissionIsDeleted, String productVariantName);

	public List<InfeedMissionRuntimeDetailsEntity> findBycreatedDatetimeBetweenAndInfeedMissionIsDeletedAndProductName(
			String string, String string2, int i, String string3);

	public InfeedMissionRuntimeDetailsEntity findByPalletInformationId(int palletInformationId);

	public List<InfeedMissionRuntimeDetailsEntity> findBycreatedDatetimeBetweenAndInfeedMissionIsDeletedAndPalletStatusIdNotAndInfeedMissionStatus(
			String string, String string2, int i, int j, String string3);

	public List<InfeedMissionRuntimeDetailsEntity> findBycreatedDatetimeBetweenAndInfeedMissionIsDeletedAndProductNameAndInfeedMissionStatus(
			String string, String string2, int i, String string3, String string4);

	

}
