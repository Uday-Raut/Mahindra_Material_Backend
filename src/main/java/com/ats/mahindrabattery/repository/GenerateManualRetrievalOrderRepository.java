package com.ats.mahindrabattery.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.mahindrabattery.entity.GenerateManualRetrievalOrderEntity;
import com.ats.mahindrabattery.entity.InfeedMissionRuntimeDetailsEntity;
import com.ats.mahindrabattery.entity.MasterProductVariantDetailsEntity;

@Transactional
public interface GenerateManualRetrievalOrderRepository
		extends JpaRepository<GenerateManualRetrievalOrderEntity, Integer> {

//	@Query(value = "select * from ats_wms_material_dispatch_schedule_history where cDateTime BETWEEN :startDate AND :endDate, AND IS_ORDER_DELETED :0", nativeQuery = true)
//	public List<GenerateManualRetrievalOrderEntity> getAllGenerateManualRetrievalOrderBetweenDatesAndIsOrderDeleted(String startDate,
//			String endDate,int isOrderDeleted);
//	
//	

	@Query(value = "select * from ats_wms_material_dispatch_schedule_history where CDATETIME BETWEEN :startDate AND :endDate ", nativeQuery = true)
	public List<GenerateManualRetrievalOrderEntity> findCDateTimeBetweenDates(String startDate, String endDate);

	public List<GenerateManualRetrievalOrderEntity> findBycreatedDatetimeBetween(String startDate, String endDate);

	public List<GenerateManualRetrievalOrderEntity> findBycreatedDatetimeBetweenAndIsOrderDeleted(String startDate,
			String endDate, int isOrderDeleted);

	@Query(value = "select * FROM ats_wms_material_dispatch_schedule_history where cDateTime BETWEEN :startDate AND :endDate", nativeQuery = true)
	public List<GenerateManualRetrievalOrderEntity> findGenerateManualRetrievalOrderBetweenDates(String startDate,
			String endDate);

	public List<GenerateManualRetrievalOrderEntity> findByIsDispatchStart(int isDispatchStart);

	public List<GenerateManualRetrievalOrderEntity> findBydispatchOrderNumber(String dispatchOrderNumber);

	public List<GenerateManualRetrievalOrderEntity> findByProductVariantCode(String productVariantCode);

	public List<GenerateManualRetrievalOrderEntity> findBySerialNumber(int serialNumber);

	public List<GenerateManualRetrievalOrderEntity> findByserialNumberBetween(int serialNumber1, int serialNumber2);

	public List<GenerateManualRetrievalOrderEntity> findByIsOrderDeleted(int i);

	public List<GenerateManualRetrievalOrderEntity> findByUserName(String name);

	Page<GenerateManualRetrievalOrderEntity> findByCreatedDatetimeBetweenAndIsOrderDeleted(String startDateTime,
			String endDateTime, int isOrderDeleted, Pageable pageable);

	List<GenerateManualRetrievalOrderEntity> findByCreatedDatetimeBetweenAndIsOrderDeletedAndIsOrderCancelled(
			String startDatetime, String endDatetime, int isOrderDeleted, int isOrderCancelled);

	public List<GenerateManualRetrievalOrderEntity> findByDispatchStatus(String dispatchStatus);

	public List<GenerateManualRetrievalOrderEntity> findByDispatchStatusIn(List<String> asList);

	public List<GenerateManualRetrievalOrderEntity> findByCreatedDatetimeBetweenAndDispatchStatus(String string,
			String string2, String string3);

	public List<GenerateManualRetrievalOrderEntity> findByCreatedDatetimeBetweenAndDispatchStatusIn(String string,
			String string2, List<String> asList);

	public List<GenerateManualRetrievalOrderEntity> findByCreatedDatetimeBetweenAndIsOrderDeletedAndIsOrderCancelledAndDispatchStatus(
			String string, String string2, int i, int j, String string3);

	public List<GenerateManualRetrievalOrderEntity> findByIsOrderDeletedAndDispatchStatusIn(int isOrderDeleted, List<String> dispatchStatus);

	@Query("SELECT e FROM GenerateManualRetrievalOrderEntity e WHERE e.createdDatetime NOT BETWEEN :startDatetime AND :endDatetime AND e.dispatchStatus IN :dispatchStatuses AND e.isOrderDeleted = :isOrderDeleted")
	List<GenerateManualRetrievalOrderEntity> findOrdersNotCreatedOnCurrentDateWithDispatchStatus(
	        @Param("startDatetime") String startDatetime,
	        @Param("endDatetime") String endDatetime,
	        @Param("dispatchStatuses") List<String> dispatchStatuses,
	        @Param("isOrderDeleted") int isOrderDeleted);





}
