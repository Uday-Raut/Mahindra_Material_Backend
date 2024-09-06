//package com.ats.mahindraelectric.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import com.ats.mahindraelectric.entity.AgeingDaysReportEntity;
//
//public interface AgingDaysReportRepository extends JpaRepository<AgeingDaysReportEntity, Integer> {
//	
//	@Query(value = "select * FROM ats_wms_inventory_summary_dashboard_details where cDateTime BETWEEN :startDate AND :endDate",nativeQuery = true)
//	public List<AgeingDaysReportEntity> findAgingDaysReportDetailsBetweenDates(String startDate,String endDate);
//
//
//}
//package com;


