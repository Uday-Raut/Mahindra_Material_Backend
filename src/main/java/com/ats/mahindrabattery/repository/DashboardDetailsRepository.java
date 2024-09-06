package com.ats.mahindrabattery.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.mahindrabattery.entity.CurrentPalletStockDetailsEntity;
import com.ats.mahindrabattery.entity.DashboardDetailsEntity;

@Transactional
public interface DashboardDetailsRepository extends JpaRepository<DashboardDetailsEntity, Integer> {

	List<DashboardDetailsEntity> findBycDateTime(String cDateTime);

	@Query(value = "select * from ats_wms_dashboard_details where cDateTime BETWEEN :startDate AND :endDate", nativeQuery = true)
	public List<DashboardDetailsEntity> getAllDashboardDetailsBetweenDates(String startDate, String endDate);

	DashboardDetailsEntity findAllBycDateTimeContaining(String date);

	DashboardDetailsEntity findBycDateTimeContaining(String currentDateTime);

	List<DashboardDetailsEntity> findTop7ByOrderByDashboardIdDesc();

	DashboardDetailsEntity findTopByOrderByDashboardIdDesc();

	List<DashboardDetailsEntity> findBycDateTimeBetween(String string, String string2);

	// public Page<CurrentPalletStockDetailsEntity> findByQualityStatus(String
	// qualityStatus,Pageable pageable);

	@Modifying(clearAutomatically = true)
	@Query("update DashboardDetailsEntity d set d.totalAlarmCount =:totalAlarmCount, d.bevInfeedCount =:bevInfeedCount, d.s230InfeedCount =:s230InfeedCount, d.bevOutfeedCount =:bevOutfeedCount,d.s230OutfeedCount =:s230OutfeedCount,d.currentokMaterialCount =:currentokMaterialCount,d.currentNokMaterialCount =:currentNokMaterialCount,d.totalOrders =:totalOrders,d.executedOrders =:executedOrders,d.remainingOrders =:remainingOrders,d.percentageOrders =:percentageOrders,d.totalCurrentStockCount =:totalCurrentStockCount,d.totalInfeedCount =:totalInfeedCount,d.totalOutfeedCount =:totalOutfeedCount,d.totalOKBEVCount =:totalOKBEVCount,d.totalNOKBEVCount =:totalNOKBEVCount,d.totalS230OKCount =:totalS230OKCount,d.totalS230NOKCount =:totalS230NOKCount,d.cDateTime =:cDateTime,d.bevCurrentStockCount =:bevCurrentStockCount,d.s230CurrentStockCount =:s230CurrentStockCount,d.emptyPalletCount =:emptyPalletCount where d.dashboardId =:dashboardId")
	public void updateDashboardDetails(int dashboardId, int totalAlarmCount, int bevInfeedCount, int s230InfeedCount,
			int bevOutfeedCount, int s230OutfeedCount,int currentokMaterialCount,int currentNokMaterialCount, int totalOrders, int executedOrders, int remainingOrders,
			String percentageOrders, int totalCurrentStockCount, int totalInfeedCount, int totalOutfeedCount,
			int totalOKBEVCount, int totalNOKBEVCount, int totalS230OKCount, int totalS230NOKCount, String cDateTime,int bevCurrentStockCount,int s230CurrentStockCount,int emptyPalletCount);

	
	
	
	
//	@Modifying(clearAutomatically = true)
//	@Query("update DashboardDetailsEntity d set d.totalAlarmCount =:totalAlarmCount, d.bevInfeedCount =:bevInfeedCount, d.s230InfeedCount =:s230InfeedCount, d.bevOutfeedCount =:bevOutfeedCount,d.s230OutfeedCount =:s230OutfeedCount,d.totalOrders =:totalOrders,d.executedOrders =:executedOrders,d.remainingOrders =:remainingOrders,d.percentageOrders =:percentageOrders,d.totalCurrentStockCount =:totalCurrentStockCount,d.totalInfeedCount =:totalInfeedCount,d.totalOutfeedCount =:totalOutfeedCount,d.totalOKBEVCount =:totalOKBEVCount,d.totalNOKBEVCount =:totalNOKBEVCount,d.totalS230OKCount =:totalS230OKCount,d.totalS230NOKCount =:totalS230NOKCount,d.cDateTime =:cDateTime,d.bevCount =:bevCount,d.s230Count =:s230Count where d.dashboardId =:dashboardId")
//	public void updateDashboardDetails(int dashboardId, int totalAlarmCount, int bevInfeedCount, int s230InfeedCount,
//			int bevOutfeedCount, int s230OutfeedCount, int totalOrders, int executedOrders, int remainingOrders,
//			String percentageOrders, int totalCurrentStockCount, int totalInfeedCount, int totalOutfeedCount,
//			int totalOKBEVCount, int totalNOKBEVCount, int totalS230OKCount, int totalS230NOKCount, String cDateTime);



}
