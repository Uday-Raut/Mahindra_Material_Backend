package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ats.mahindrabattery.entity.PalletOperationHistoryEntity;



public interface PalletOperationHistoryRepository extends JpaRepository<PalletOperationHistoryEntity, Integer>{

//	public List<PalletOperationHistoryEntity> findByloadDateTimeAndOperation(String loadDateTime,String operation);
	
//	@Query(value = "select * from ats_wms_pallet_operation_history where Load_DateTime BETWEEN :startDateTime AND :endDateTime",nativeQuery = true)
//	public List<PalletOperationHistoryEntity> findBetweenStartDateandEndDate(String startDateTime, String endDateTime);
	
	public List<PalletOperationHistoryEntity> findByproductVariantCode(String productVariantCode);
	
	public List<PalletOperationHistoryEntity> findByloadDateTimeContainingAndOperation(String currentDateTime, String string);
	
	public List<PalletOperationHistoryEntity> findByoperation(String operation);
	
	List<PalletOperationHistoryEntity> findByPalletCodeAndProductVariantCode(String palletCode,
			String productVariantCode);

	List<PalletOperationHistoryEntity> findByPalletCodeAndPalletInformationId(String palletCode,
			String productVariantCode);


	List<PalletOperationHistoryEntity> findByPalletCodeAndProductVariantCodeAndProductVariantNameAndOrderIdAndQuantity(
			String palletCode, String productVariantCode, String productVariantName, String orderId, int quantity);

	List<PalletOperationHistoryEntity> findByPalletCodeAndProductVariantCodeAndProductVariantNameAndOrderIdAndQuantityAndUserNameAndLoadDateTimeAndProductName(
			String palletCode, String productVariantCode, String productVariantName, int orderId, int quantity,
			String userName, String loadDateTime, String productName);

	
	
	 
//	@Query(value = "select * from ats_wms_pallet_operation_history where operation='Loading' And Load_DateTime BETWEEN :startDateTime AND :endDateTime",nativeQuery = true)

//	List<PalletOperationHistoryEntity> findByloadDateTimeBetween(String startDateTime, String endDateTime);
	
	List<PalletOperationHistoryEntity> findByWmsTransferOrderIdOrderByPalletRetrievalHistoryIdDesc(
			String wmsTransferOrderId);

	public List<PalletOperationHistoryEntity> findByProductVariantCodeAndSapOrderNoAndOperation(String materialCode,
			String sapOrderNo, String operation);

	
	 
//	@Query(value = "select * from ats_wms_pallet_operation_history where operation='Unloading' And Load_DateTime BETWEEN :startDateTime AND :endDateTime",nativeQuery = true)

	public List<PalletOperationHistoryEntity> findByLoadDateTimeBetween(String startDateTime, String endDateTime);

}
