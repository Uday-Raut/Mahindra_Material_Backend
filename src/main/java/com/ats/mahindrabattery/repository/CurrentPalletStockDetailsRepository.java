package com.ats.mahindrabattery.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.mahindrabattery.entity.CurrentPalletStockDetailsEntity;
import com.ats.mahindrabattery.entity.EquipmentAlarmHistoryEntity;
import com.ats.mahindrabattery.entity.InfeedMissionRuntimeDetailsEntity;

@Transactional
public interface CurrentPalletStockDetailsRepository extends JpaRepository<CurrentPalletStockDetailsEntity, Integer> {
	@Query(value = "select * FROM ats_wms_current_stock_details where Load_Datetime BETWEEN :startDate AND :endDate", nativeQuery = true)

	public List<CurrentPalletStockDetailsEntity> findCurrentStockDetailsBetweenDates(String startDate, String endDate);

	List<CurrentPalletStockDetailsEntity> findBypalletCode(String palletCode);

	List<CurrentPalletStockDetailsEntity> findByQuantity(int quantity);

	List<CurrentPalletStockDetailsEntity> findAllByPositionName(String positionName);

	void deleteByProductVariantCodeAndPalletCodeAndCurrentPalletStockDetailsId(String productVariantCode,
			String palletCode, int currentPalletStockDetailsId);

	CurrentPalletStockDetailsEntity findByCurrentPalletStockDetailsId(int currentPalletStockDetailsId);

	void deleteByProductVariantCode(int currentPalletStockDetailsId);

	Object deleteByProductVariantCodeAndPalletCode(String productVariantCode, String palletCode);

	void deleteByPalletCode(String palletCode);

	List<CurrentPalletStockDetailsEntity> deleteByProductVariantCode(String productVariantCode);

	List<CurrentPalletStockDetailsEntity> findByAreaName(String areaName);

	List<CurrentPalletStockDetailsEntity> findByproductName(String productName);

	List<CurrentPalletStockDetailsEntity> findByproductVariantCode(String productVariantCode);

	@Query(value = "select * from ats_wms_current_pallet_stock_details where Load_Datetime BETWEEN :startDateTime AND :endDateTime", nativeQuery = true)
	public List<CurrentPalletStockDetailsEntity> getAllCurrentPalletStockDetailsBetweenDates(String startDateTime,
			String endDateTime);

	List<CurrentPalletStockDetailsEntity> findByLoadDatetimeBetween(String startDateTime, String endDateTime);

	List<CurrentPalletStockDetailsEntity> findByPalletCode(String palletCode);

	List<CurrentPalletStockDetailsEntity> findByPositionId(int positionId);

	CurrentPalletStockDetailsEntity getByPositionId(int positionId);

	@Modifying
	@Query("update CurrentPalletStockDetailsEntity u set u.quantity = :quantity ,palletCode=:palletCode,productVariantCode=:productVariantCode where u.positionId = :positionId")
	void updateQuantityAndPalletCodeAndproductVariantCodeByPositionId(@Param(value = "quantity") int quantity,
			@Param(value = "positionId") int positionId, @Param(value = "palletCode") String palletCode,
			@Param(value = "productVariantCode") String productVariantCode);

	CurrentPalletStockDetailsEntity findByProductVariantCodeAndPalletCode(String productVariantCode, String palletCode);

	CurrentPalletStockDetailsEntity findByQuantityAndPositionIdAndPalletCodeAndProductVariantCode(int quantity,
			int positionId, String palletCode, String productVariantCode);

	List<CurrentPalletStockDetailsEntity> findByPalletCodeAndProductVariantCode(String palletCode,
			String productVariantCode);

	@Modifying
	@Query("update CurrentPalletStockDetailsEntity u set u.quantity = :quantity," + " u.userId= :userId,"
			+ " u.userName= :userName" + " where u.currentPalletStockDetailsId = :currentPalletStockDetailsId")
	void updateQuantityByCurrentPalletStockDetailsId(
			@Param(value = "currentPalletStockDetailsId") int currentPalletStockDetailsId,
			@Param(value = "quantity") int quantity, @Param(value = "userId") int userId,
			@Param(value = "userName") String userName);

	@Modifying
	@Query("update CurrentPalletStockDetailsEntity u set u.productVariantId = :productVariantId,"
			+ " u.productVariantCode = :productVariantCode," + " u.productVariantName = :productVariantName,"
			+ " u.quantity = :quantity," + " u.loadDatetime = :loadDatetime," + " u.userId= :userId,"
			+ " u.userName= :userName" + " where u.currentPalletStockDetailsId = :currentPalletStockDetailsId")
	void updateNApalletData(@Param(value = "productVariantId") int productVariantId,
			@Param(value = "productVariantCode") String productVariantCode,
			@Param(value = "productVariantName") String productVariantName, @Param(value = "quantity") int quantity,
			@Param(value = "loadDatetime") String loadDatetime, @Param(value = "userId") int userId, String userName,
			int currentPalletStockDetailsId);

	List<CurrentPalletStockDetailsEntity> deleteByCurrentPalletStockDetailsId(int currentPalletStockDetailsId);

	List<CurrentPalletStockDetailsEntity> findByPositionName(String positionName);

	public List<CurrentPalletStockDetailsEntity> findByProductNameNot(String productName);

	public List<CurrentPalletStockDetailsEntity> findByLoadDatetimeBetweenAndQualityStatus(String startDate,
			String endDate, String qualityStatus);

	public List<CurrentPalletStockDetailsEntity> findByQualityStatus(String qualityStatus);

	public List<CurrentPalletStockDetailsEntity> findByProductVariantName(String productVariantName);

	public List<CurrentPalletStockDetailsEntity> findByProductVariantCode(String productVariantCode);

	public Page<CurrentPalletStockDetailsEntity> findByQualityStatusAndPalletCodeNot(String qualityStatus,
			String palletCode, Pageable pageable);

	@Query(value = "select * from ats_wms_current_stock_details where QUALITY_STATUS = :qualityStatus", nativeQuery = true)
	public Page<CurrentPalletStockDetailsEntity> getByQualityStatus(@Param("qualityStatus") String qualityStatus,
			Pageable pageable);

	public List<CurrentPalletStockDetailsEntity> findByPalletCodeNot(String string);

	public Page<CurrentPalletStockDetailsEntity> findByPalletCodeNotOrderByAgeingDaysDesc(Pageable pageable,
			String PalletCode);

	@Modifying
	@Query("update CurrentPalletStockDetailsEntity c set c.ageingDays = :ageingDays where currentPalletStockDetailsId=:id")
	public void updateAgeingDays(int ageingDays, int id);

	@Modifying(clearAutomatically = true)
	@Query("update CurrentPalletStockDetailsEntity m set m.palletInformationId =:palletInformationId, m.palletCode =:palletCode,m.serialNumber =:serialNumber,m.productId =:productId,m.productName =:productName,m.productVariantId =:productVariantId,m.productVariantName =:productVariantName,m.productVariantCode =:productVariantCode,m.quantity =:quantity,m.palletStatusId =:palletStatusId,m.palletStatusname =:palletStatusname,m.ageingDays =:ageingDays,m.batchNumber =:batchNumber,m.modelNumber =:modelNumber,m.location =:location,m.qualityStatus =:qualityStatus,m.isInfeedMissionGenerated =:isInfeedMissionGenerated,m.isOutfeedMissionGenerated =:isOutfeedMissionGenerated,m.loadDatetime =:loadDatetime, m.userId =:userId, m.userName =:userName where m.positionId =:positionId")
	public int updateCurrentPalletInforamtion(int positionId, int palletInformationId, String palletCode,
			int serialNumber, int productId, String productName, int productVariantId, String productVariantName,
			String productVariantCode, int quantity, int palletStatusId, String palletStatusname, int ageingDays,
			String batchNumber, String modelNumber, String location, String qualityStatus, int isInfeedMissionGenerated,
			int isOutfeedMissionGenerated, String loadDatetime, int userId, String userName);

	public List<CurrentPalletStockDetailsEntity> findBypalletInformationId(int palletInformationId);

	public List<CurrentPalletStockDetailsEntity> findByPalletCodeOrderByPalletInformationIdDesc(String palletCode);

//	@Query(value = "select * from ats_wms_current_stock_details where SERIAL_NUMBER BETWEEN :startSerialNumber AND :endSerialNumber", nativeQuery = true)
//	public List<CurrentPalletStockDetailsEntity> getBySerialNumberAndQualityStatus(String startSerialNumber,
//			String endSerialNumber);

	public List<CurrentPalletStockDetailsEntity> findByProductName(String string);

	public CurrentPalletStockDetailsEntity findByPalletInformationId(int palletInformationId);

	Page<CurrentPalletStockDetailsEntity> findByProductNameAndPalletStatusIdNotOrderByAgeingDaysDesc(Pageable pageable,
			String productName, int palletStatusId);

	public List<CurrentPalletStockDetailsEntity> findByProductNameAndQualityStatusAndPalletStatusIdNot(
			Pageable pageable, String string, String string2, int i);

	public List<CurrentPalletStockDetailsEntity> findByQualityStatusAndProductName(String string, String string2);

	public Page<CurrentPalletStockDetailsEntity> findByQualityStatusAndProductNameOrderByAgeingDaysDesc(
			String qualityStatus, String productName, Pageable pageable);

	public Page<CurrentPalletStockDetailsEntity> findByPalletCodeNot(Pageable pageable, String string);

	public Page<CurrentPalletStockDetailsEntity> getByPalletCodeNotOrderByAgeingDaysDesc(Pageable pageable,
			String string);

	public Page<CurrentPalletStockDetailsEntity> getByPalletCodeNot(Pageable pageable, String string);

//	@Query(value = "select * from ats_wms_current_stock_details where SERIAL_NUMBER BETWEEN :startSerialNumber AND :endSerialNumber", nativeQuery = true)
//	public List<CurrentPalletStockDetailsEntity> findByserialNumberBetween(int startSerialNumber, int endSerialNumber);

	public List<CurrentPalletStockDetailsEntity> findByserialNumberBetween(int serialNumber1, int serialNumbere2);

//	@Query(value = "SELECT * FROM ats_wms_current_stock_details WHERE SERIAL_NUMBER BETWEEN :startSerialNumber AND :endSerialNumber", nativeQuery = true)
//	public List<CurrentPalletStockDetailsEntity> findBySerialNumberBetween(int startSerialNumber, int endSerialNumber);

	public List<CurrentPalletStockDetailsEntity> findByQualityStatusAndPalletCodeNot(String string, String string2);

	public List<CurrentPalletStockDetailsEntity> findByserialNumber(int x);

	public List<CurrentPalletStockDetailsEntity> findByproductVariantCodeAndSerialNumberBetween(
			String productVariantCode, int serialNumber1, int serialNumber2);

	public Page<CurrentPalletStockDetailsEntity> findByProductNameAndPalletStatusIdOrderByAgeingDaysDesc(
			Pageable pageable, String string, int i);

	public List<CurrentPalletStockDetailsEntity> findByProductVariantCodeAndQuantity(String string, int i);

	public Page<CurrentPalletStockDetailsEntity> findByProductVariantCodeAndQuantityAndPalletStatusIdAndPalletCodeNot(
			String string, int i, int j, String string2, Pageable pageable);

	public List<CurrentPalletStockDetailsEntity> findByProductNameAndPalletCodeNot(String string, String string2);

	public Page<CurrentPalletStockDetailsEntity> findByProductNameAndPalletCodeNotAndPalletStatusIdOrderByAgeingDaysDesc(
			Pageable pageable, String string, String string2, int i);

	// public List<CurrentPalletStockDetailsEntity>
	// findBySerialNumberGreaterThanEqualAndSerialNumberLessThanEqual(int
	// SerialNumber1, int SerialNumber2);

}
