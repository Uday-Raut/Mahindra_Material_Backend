package com.ats.mahindrabattery.serviceimpl;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ats.mahindrabattery.entity.CurrentPalletStockDetailsEntity;
import com.ats.mahindrabattery.entity.DashboardDetailsEntity;
import com.ats.mahindrabattery.entity.EquipmentAlarmHistoryEntity;
import com.ats.mahindrabattery.entity.GenerateManualRetrievalOrderEntity;
import com.ats.mahindrabattery.entity.InfeedMissionRuntimeDetailsEntity;
import com.ats.mahindrabattery.entity.MasterPositionDetailsEntity;
import com.ats.mahindrabattery.entity.OutfeedMissionRuntimeDetailsEntity;
import com.ats.mahindrabattery.entity.PickListStagingTableEntity;
import com.ats.mahindrabattery.repository.CurrentPalletStockDetailsRepository;
import com.ats.mahindrabattery.repository.DashboardDetailsRepository;
import com.ats.mahindrabattery.repository.EquipmentAlarmHistoryRepository;
import com.ats.mahindrabattery.repository.GenerateManualRetrievalOrderRepository;
import com.ats.mahindrabattery.repository.InfeedMissionRuntimeDetailsRepository;
import com.ats.mahindrabattery.repository.MasterPositionDetailsRepository;
import com.ats.mahindrabattery.repository.OutfeedMissionruntimeDetailsRepository;
import com.ats.mahindrabattery.repository.PickListStagingTableRepository;
import com.ats.mahindrabattery.service.DashboardDetailsService;

@Service
public class DashboardDetailsServiceImpl implements DashboardDetailsService {

	DashboardDetailsEntity dashboardEntity = new DashboardDetailsEntity();

	@Autowired
	private CurrentPalletStockDetailsRepository currentPalletStockDetailsRepository;

	@Autowired
	private EquipmentAlarmHistoryRepository equipmentAlarmHistoryRepository;

	@Autowired
	private InfeedMissionRuntimeDetailsRepository infeedMissionRuntimeDetailsRepository;

	@Autowired
	private OutfeedMissionruntimeDetailsRepository outfeedMissionRuntimeDetailsRepository;

	@Autowired
	private GenerateManualRetrievalOrderRepository generateManualRetrievalOrderRepository;

	@Autowired
	private CurrentPalletStockDetailsServiceImpl CurrentPalletStockDetailsServiceInstance;
//	@Autowired
//	private PickListStagingTableRepository pickListStagingTableRepository;

	@Autowired
	private DashboardDetailsRepository dashboardDetailsRepository;

	@Autowired
	private MasterPositionDetailsRepository masterPositionDetailsRepository;

	private int dashboardId;
	private int totalAlarmCount;
	private int bevInfeedCount;
	private int s230InfeedCount;
	private int bevOutfeedCount;
	private int s230OutfeedCount;
//	private int totalCurrentStockCount;
	private int currentokMaterialCount;
	private int currentNokMaterialCount;
	private int totalOrders;
	private int executedOrders;
	private int remainingOrders;
	private String percentageOrders = "";
	private float percentageOrders1;
	private String formattedPercentage;
	private int totalCurrentStock;
	private int totalNOKCount;
	private int totalBEVCount;
	private int totalOKCount;
	private int totalOKBEVCount;
	private int totalNOKBEVCount;
	private int totalS230NOKCount;
	private int totalS230OKCount;
	private int totalInfeedCount;
	private int totalOutfeedCount;

	private int s230Count;
	private int bevCount;
	private int emptyPalletCount;

	private int bevCurrentStockCount;
	private int s230CurrentStockCount;

	public Page<CurrentPalletStockDetailsEntity> findAll(Pageable pageable) {
		return currentPalletStockDetailsRepository.findAll(pageable);
	}

	// CurrentStock All Detail by CdateTime
	public List<CurrentPalletStockDetailsEntity> getByDate() {
		try {
			Date date = new Date();
			String strDateFormat = "yyyy-MM-dd";
			DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
			String currentDateTime = dateFormat.format(date);

			// Create an instance of the repository
			CurrentPalletStockDetailsRepository repository = currentPalletStockDetailsRepository;

			// Call the instance method on the repository
			return repository.findCurrentStockDetailsBetweenDates(currentDateTime + " " + "00:00:00",
					currentDateTime + " " + "23:59:59");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// Infeed details by currentdate for BEV Material
	public List<InfeedMissionRuntimeDetailsEntity> getBevInfeedDetailsByDate() {
		try {
			Date date = new Date();
			String strDateFormat = "yyyy-MM-dd";
			DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
			String currentDateTime = dateFormat.format(date);
			// System.out.println("currentDateTime::"+currentDateTime);
			return infeedMissionRuntimeDetailsRepository
					.findBycreatedDatetimeBetweenAndInfeedMissionIsDeletedAndProductName(
							currentDateTime + " " + "00:00:00", currentDateTime + " " + "23:59:59", 0, "BEV");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// Infeed details by currentdate for BEV Material
	public List<InfeedMissionRuntimeDetailsEntity> getS230InfeedDetailsByDate() {
		try {
			Date date = new Date();
			String strDateFormat = "yyyy-MM-dd";
			DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
			String currentDateTime = dateFormat.format(date);
			// System.out.println("currentDateTime::"+currentDateTime);
			return infeedMissionRuntimeDetailsRepository
					.findBycreatedDatetimeBetweenAndInfeedMissionIsDeletedAndProductName(
							currentDateTime + " " + "00:00:00", currentDateTime + " " + "23:59:59", 0, "S230");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	// Total Infeed details by currentdate
	public int getInfeedDetailsByDate() {
		try {
			Date date = new Date();
			String strDateFormat = "yyyy-MM-dd";
			DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
			String currentDateTime = dateFormat.format(date);
			// System.out.println("currentDateTime::"+currentDateTime);
			List<InfeedMissionRuntimeDetailsEntity> findBycreatedDatetimeBetweenAndInfeedMissionIsDeleted = infeedMissionRuntimeDetailsRepository
					.findBycreatedDatetimeBetweenAndInfeedMissionIsDeletedAndPalletStatusIdNotAndInfeedMissionStatus(
							currentDateTime + " " + "00:00:00", currentDateTime + " " + "23:59:59", 0, 3, "COMPLETED");
			totalInfeedCount = (int) findBycreatedDatetimeBetweenAndInfeedMissionIsDeleted.stream().count();
			dashboardEntity.setTotalInfeedCount(totalInfeedCount);
//						 System.out.println("totalInfeedCount::"+dashboardEntity.getTotalInfeedCount());
			return dashboardEntity.getTotalInfeedCount();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return (Integer) null;

	}

	// currentStock count by CurrentDate
//	public int findCurrentStockCountByCurrentDatetime() {
//	    try {
//	        Date date = new Date();
//	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	        String currentDate = simpleDateFormat.format(date);
//
//	        List<CurrentPalletStockDetailsEntity> findByCurrentStockCountDatetimeBetween = currentPalletStockDetailsRepository
//	                .findByLoadDatetimeBetween(currentDate + " " + "00:00:00",
//	                        currentDate + " " + "23:59:59");
//
//	        totalCurrentStockCount = findByCurrentStockCountDatetimeBetween.size();
//	        dashboardEntity.setTotalCurrentStockCount(totalCurrentStockCount);
//
//	        return dashboardEntity.getTotalCurrentStockCount();
//	    } catch (Exception e) {
//	       
//	        e.printStackTrace();
//	    }
//	  
//	    return 0;
//	}
	// Outfeed details by currentdate for BEV Material
	public List<OutfeedMissionRuntimeDetailsEntity> getBevOuteedDetailsByDate() {
		try {
			Date date = new Date();
			String strDateFormat = "yyyy-MM-dd";
			DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
			String currentDateTime = dateFormat.format(date);
			// System.out.println("currentDateTime::"+currentDateTime);
			return outfeedMissionRuntimeDetailsRepository
					.findBycreatedDatetimeBetweenAndOutfeedMissionIsDeletedAndProductName(
							currentDateTime + " " + "00:00:00", currentDateTime + " " + "23:59:59", 0, "BEV");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// Outfeed details by currentdate for S230 Material
	public List<OutfeedMissionRuntimeDetailsEntity> getS230OuteedDetailsByDate() {
		try {
			Date date = new Date();
			String strDateFormat = "yyyy-MM-dd";
			DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
			String currentDateTime = dateFormat.format(date);
			// System.out.println("currentDateTime::"+currentDateTime);
			return outfeedMissionRuntimeDetailsRepository
					.findBycreatedDatetimeBetweenAndOutfeedMissionIsDeletedAndProductName(
							currentDateTime + " " + "00:00:00", currentDateTime + " " + "23:59:59", 0, "S230");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// Total Outfeed details by currentdate
	public int getOutfeedDetailsByDate() {
		try {
			Date date = new Date();
			String strDateFormat = "yyyy-MM-dd";
			DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
			String currentDateTime = dateFormat.format(date);
			// System.out.println("currentDateTime::"+currentDateTime);
			List<OutfeedMissionRuntimeDetailsEntity> findBycreatedDatetimeBetweenAndOutfeedMissionIsDeleted = outfeedMissionRuntimeDetailsRepository
					.findBycreatedDatetimeBetweenAndOutfeedMissionIsDeletedAndOutfeedMissionStatusAndPalletStatusIdNot(
							currentDateTime + " " + "00:00:00", currentDateTime + " " + "23:59:59", 0, "COMPLETED", 3);
			totalOutfeedCount = (int) findBycreatedDatetimeBetweenAndOutfeedMissionIsDeleted.stream().count();
			dashboardEntity.setTotalOutfeedCount(totalOutfeedCount);
			// System.out.println("totalOutfeedCount::"+dashboardEntity.getTotalOutfeedCount());
			return dashboardEntity.getTotalOutfeedCount();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return (Integer) null;

	}

	@SuppressWarnings("null")
	public int findbevOutfeedDetailsByCurrentDate() {
		try {
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String createdDatetime = simpleDateFormat.format(date);
			List<OutfeedMissionRuntimeDetailsEntity> findBycreatedDatetimeBetweenAndOutfeedMissionIsDeletedAndProductVariantName = outfeedMissionRuntimeDetailsRepository
					.findBycreatedDatetimeBetweenAndOutfeedMissionIsDeletedAndProductNameAndOutfeedMissionStatus(
							createdDatetime + " " + "00:00:00", createdDatetime + " " + "23:59:59", 0, "BEV","COMPLETED");
			bevOutfeedCount = (int) findBycreatedDatetimeBetweenAndOutfeedMissionIsDeletedAndProductVariantName.stream()
					.filter(e -> e.getPalletStatusId() != 3).count();
			dashboardEntity.setBevOutfeedCount(bevOutfeedCount);
			return dashboardEntity.getBevOutfeedCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Integer) null;
	}

	@SuppressWarnings("null")
	public int finds230OutfeedDetailsByCurrentDate() {
		try {
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String createdDatetime = simpleDateFormat.format(date);
			List<OutfeedMissionRuntimeDetailsEntity> findBycreatedDatetimeBetweenAndOutfeedMissionIsDeletedAndProductVariantName = outfeedMissionRuntimeDetailsRepository
					.findBycreatedDatetimeBetweenAndOutfeedMissionIsDeletedAndProductNameAndOutfeedMissionStatus(
							createdDatetime + " " + "00:00:00", createdDatetime + " " + "23:59:59", 0, "S230","COMPLETED");
			s230OutfeedCount = (int) findBycreatedDatetimeBetweenAndOutfeedMissionIsDeletedAndProductVariantName
					.stream().filter(e -> e.getPalletStatusId() != 3).count();
			dashboardEntity.setS230OutfeedCount(s230OutfeedCount);
			return dashboardEntity.getS230OutfeedCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Integer) null;
	}

	@SuppressWarnings("null")
	public int finds230InfeedDetailsByCurrentDate() {
		try {
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String createdDatetime = simpleDateFormat.format(date);
			List<InfeedMissionRuntimeDetailsEntity> findBycreatedDatetimeBetweenAndInfeedMissionIsDeletedAndProductVariantName = infeedMissionRuntimeDetailsRepository
					.findBycreatedDatetimeBetweenAndInfeedMissionIsDeletedAndProductNameAndInfeedMissionStatus(
							createdDatetime + " " + "00:00:00", createdDatetime + " " + "23:59:59", 0, "S230","COMPLETED");
			s230InfeedCount = (int) findBycreatedDatetimeBetweenAndInfeedMissionIsDeletedAndProductVariantName.stream()
					.filter(e -> e.getPalletStatusId() != 3).count();
			dashboardEntity.setS230InfeedCount(s230InfeedCount);
			return dashboardEntity.getS230InfeedCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Integer) null;
	}

	@SuppressWarnings("null")
	public int findBEVInfeedDetailsByCurrentDate() {
		try {
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String createdDatetime = simpleDateFormat.format(date);
			List<InfeedMissionRuntimeDetailsEntity> findBycreatedDatetimeBetweenAndInfeedMissionIsDeletedAndProductVariantName = infeedMissionRuntimeDetailsRepository
					.findBycreatedDatetimeBetweenAndInfeedMissionIsDeletedAndProductNameAndInfeedMissionStatus(
							createdDatetime + " " + "00:00:00", createdDatetime + " " + "23:59:59", 0, "BEV","COMPLETED");
			bevInfeedCount = (int) findBycreatedDatetimeBetweenAndInfeedMissionIsDeletedAndProductVariantName.stream()
					.filter(e -> e.getPalletStatusId() != 3).count();
			dashboardEntity.setBevInfeedCount(bevInfeedCount);
			return dashboardEntity.getBevInfeedCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Integer) null;
	}

	@SuppressWarnings("null")
	public int findTotalCurrentStockDetails() {
		try {
			List<CurrentPalletStockDetailsEntity> findByPalletCodeNot = currentPalletStockDetailsRepository
					.findByPalletCodeNot("NA");
			totalCurrentStock = findByPalletCodeNot.size();
			dashboardEntity.setTotalCurrentStockCount(totalCurrentStock);
			return dashboardEntity.getTotalCurrentStockCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Integer) null;
	}

	// CurrentStock Ok material Status details by currentdate for ok Material
	public List<CurrentPalletStockDetailsEntity> getOkMaterialCurrentStockDetailsByDate() {
		try {

			return currentPalletStockDetailsRepository.findByQualityStatus("OK");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Page<CurrentPalletStockDetailsEntity> getOkMaterialCurrentStockDetailsPage(Pageable pageable) {
		try {

			Page<CurrentPalletStockDetailsEntity> findAll = currentPalletStockDetailsRepository.findAll(pageable);
			List<CurrentPalletStockDetailsEntity> collect = findAll.stream()
					.filter(e -> e.getQualityStatus().equals("OK")).collect(Collectors.toList());
			return (Page<CurrentPalletStockDetailsEntity>) collect;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return null;
	}

	// CurrentStock NOk material Status details by currentdate for Nok Material
	public List<CurrentPalletStockDetailsEntity> getNOkMaterialCurrentStockDetailsByDate() {
		try {

//			Date date = new Date();
//			String strDateFormat = "yyyy-MM-dd";
//			DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
//			String currentDateTime = dateFormat.format(date);
			// System.out.println("currentDateTime::"+currentDateTime);
			return currentPalletStockDetailsRepository.findByQualityStatus("NOK");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// CurrentStock NOk BEV material count
	@SuppressWarnings("null")
	public int getNOkBEVMaterialCurrentStockDetails() {
		try {
			List<CurrentPalletStockDetailsEntity> findByQualityStatus = currentPalletStockDetailsRepository
					.findByQualityStatusAndProductName("NOK", "BEV");
			List<CurrentPalletStockDetailsEntity> collect = findByQualityStatus.stream()
					.filter(e -> !"NA".equals(e.getPalletCode())).collect(Collectors.toList());
			totalNOKBEVCount = (int) collect.stream().count();
			dashboardEntity.setTotalNOKBEVCount(totalNOKBEVCount);
			return dashboardEntity.getTotalNOKBEVCount();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return (Integer) null;
	}

	// CurrentStock Ok BEV material count
	@SuppressWarnings("null")
	public int getOkBEVMaterialCurrentStockDetails() {
		try {
			List<CurrentPalletStockDetailsEntity> findByQualityStatus = currentPalletStockDetailsRepository
					.findByQualityStatusAndProductName("OK", "BEV");
			List<CurrentPalletStockDetailsEntity> collect = findByQualityStatus.stream()
					.filter(e -> !"NA".equals(e.getPalletCode())).collect(Collectors.toList());
			totalOKBEVCount = (int) collect.stream().count();
			// totalOKCount = (int) findByQualityStatus.stream().count();
			dashboardEntity.setTotalOKBEVCount(totalOKBEVCount);
			return dashboardEntity.getTotalOKBEVCount();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return (Integer) null;
	}

	// CurrentStock NOk S230 material count
	@SuppressWarnings("null")
	public int getNOkS230MaterialCurrentStockDetails() {
		try {
			List<CurrentPalletStockDetailsEntity> findByQualityStatus = currentPalletStockDetailsRepository
					.findByQualityStatusAndProductName("NOK", "S230");
			List<CurrentPalletStockDetailsEntity> collect = findByQualityStatus.stream()
					.filter(e -> !"NA".equals(e.getPalletCode())).collect(Collectors.toList());
			totalS230NOKCount = (int) collect.stream().count();
			dashboardEntity.setTotalS230NOKCount(totalS230NOKCount);
			return dashboardEntity.getTotalS230NOKCount();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return (Integer) null;
	}

	// CurrentStock NOk S230 material count
	@SuppressWarnings("null")
	public int getOkS230MaterialCurrentStockDetails() {
		try {
			List<CurrentPalletStockDetailsEntity> findByQualityStatus = currentPalletStockDetailsRepository
					.findByQualityStatusAndProductName("OK", "S230");
			List<CurrentPalletStockDetailsEntity> collect = findByQualityStatus.stream()
					.filter(e -> !"NA".equals(e.getPalletCode())).collect(Collectors.toList());
			totalS230OKCount = (int) collect.stream().count();
			// totalOKCount = (int) findByQualityStatus.stream().count();
			dashboardEntity.setTotalS230OKCount(totalS230OKCount);
			return dashboardEntity.getTotalS230OKCount();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return (Integer) null;
	}

//	@SuppressWarnings("null")
//	public int findOkCurrentStockByCurrentDate() {
//		try {
//			Date date = new Date();
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			String createdDatetime = simpleDateFormat.format(date);
//			List<CurrentPalletStockDetailsEntity> findByLoadDatetimeBetweenAndQualityStatus = currentPalletStockDetailsRepository
//
//					.findByLoadDatetimeBetweenAndQualityStatus(createdDatetime + " " + "00:00:00",
//							createdDatetime + " " + "23:59:59", "OK");
//			currentokMaterialCount = (int) findByLoadDatetimeBetweenAndQualityStatus.stream().count();
//			dashboardEntity.setCurrentokMaterialCount(currentokMaterialCount);
//			return dashboardEntity.getCurrentokMaterialCount();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return (Integer) null;
//	}
//
//	@SuppressWarnings("null")
//	public int findNokCurrentStockByCurrentDate() {
//		try {
//			Date date = new Date();
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			String createdDatetime = simpleDateFormat.format(date);
//			List<CurrentPalletStockDetailsEntity> findByLoadDatetimeBetweenAndQualityStatus = currentPalletStockDetailsRepository
//
//					.findByLoadDatetimeBetweenAndQualityStatus(createdDatetime + " " + "00:00:00",
//							createdDatetime + " " + "23:59:59", "NOK");
//			currentNokMaterialCount = (int) findByLoadDatetimeBetweenAndQualityStatus.stream().count();
//			dashboardEntity.setCurrentNokMaterialCount(currentNokMaterialCount);
//			return dashboardEntity.getCurrentNokMaterialCount();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return (Integer) null;
//	}

	@SuppressWarnings("null")
	public int findNokCurrentStockByCurrentDate() {
		try {

			List<CurrentPalletStockDetailsEntity> findByLoadDatetimeBetweenAndQualityStatus = currentPalletStockDetailsRepository
					.findByQualityStatusAndPalletCodeNot("NOK", "NA");
			currentNokMaterialCount = (int) findByLoadDatetimeBetweenAndQualityStatus.stream().count();
			dashboardEntity.setCurrentNokMaterialCount(currentNokMaterialCount);
			return dashboardEntity.getCurrentNokMaterialCount();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Integer) null;
	}

	@SuppressWarnings("null")
	public int findOkCurrentStockByCurrentDate() {
		try {

			List<CurrentPalletStockDetailsEntity> findByLoadDatetimeBetweenAndQualityStatus = currentPalletStockDetailsRepository
					.findByQualityStatusAndPalletCodeNot("OK", "NA");
			currentokMaterialCount = (int) findByLoadDatetimeBetweenAndQualityStatus.stream().count();
			dashboardEntity.setCurrentNokMaterialCount(currentokMaterialCount);
			return dashboardEntity.getCurrentNokMaterialCount();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Integer) null;
	}

	@SuppressWarnings("null")
	public int findEquipmentAlarmHistoryByCDatetime() {
		try {
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String currentDate = simpleDateFormat.format(date);

			List<EquipmentAlarmHistoryEntity> findByEquipmentAlarmOccurredDatetimeBetween = equipmentAlarmHistoryRepository
					.findByEquipmentAlarmOccurredDatetimeBetween(currentDate + " " + "00:00:00",
							currentDate + " " + "23:59:59");
			totalAlarmCount = findByEquipmentAlarmOccurredDatetimeBetween.size();
			dashboardEntity.setTotalAlarmCount(totalAlarmCount);
			return dashboardEntity.getTotalAlarmCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Integer) null;
	}

//	public void findExpiryDateDetails() throws ParseException {
//		try {
//			List<CurrentPalletStockDetailsEntity> findByProductNameNot = currentPalletStockDetailsRepository
//					.findByProductNameNot("NA");
//			// System.out.println("findByProductNameNot size::" +
//			// findByProductNameNot.size());
//			count1 = 0;
//			count2 = 0;
//			count3 = 0;
//			for (int i = 0; i < findByProductNameNot.size(); i++) {
//				String expiryDate1 = findByProductNameNot.get(i).getExpiryDate();
//				String expDate = expiryDate1.substring(0, 10);
//				System.out.println("exp date::"+expDate);
//				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
//				LocalDate expiryDate = LocalDate.parse(expDate, formatter);
//				LocalDate currentDate = LocalDate.now();
//				days = (int) ChronoUnit.DAYS.between(currentDate, expiryDate);
//				System.out.println("days::" + days);
//
//				if (days > 5 && days <= 30) {
//					count1++;
//					dashboardEntity.setExpiredwithin30days(count1);
//				}
//				if (days <= 5 && days > 0) {
//					count2++;
//					dashboardEntity.setExpiredwithin5days(count2);
//				}
//				if (days <= 0) {
//					count3++;
//					dashboardEntity.setExpired(count3);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	public void findAllPickListDetails() {
//		try {
//			List<PickListStagingTableEntity> findAll = pickListStagingTableRepository.findAll();
//			totalOrders = findAll.size();
//			dashboardEntity.setTotalOrders(totalOrders);
//			executedOrders = (int) findAll.stream().filter(e -> e.getIsOrderFullFilled() == 1).count();
//			dashboardEntity.setExecutedOrders(executedOrders);
//			remainingOrders = totalOrders - executedOrders;
//			dashboardEntity.setRemainingOrders(remainingOrders);
//			percentageOrders = ((executedOrders * 100) / totalOrders);
//			dashboardEntity.getPercentageOrders();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	 @Scheduled(cron = "0 0 0 * * *")
//	public DashboardDetailsEntity findAllDashboardDetails() {
//		try {
//			Date date = new Date();
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String createdDatetime = simpleDateFormat.format(date);
//			String substring1 = createdDatetime.substring(0, 10);
//			// System.out.println("substring1::" + substring1);
//
//			DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//			LocalDate currenDateTime1 = LocalDate.parse(substring1, format1);
//			// System.out.println("currenDateTime1::" + currenDateTime1);
//
//			DashboardDetailsEntity findTopByOrderByDashboardIdDesc = dashboardDetailsRepository
//					.findTopByOrderByDashboardIdDesc();
//			String cDateTime = findTopByOrderByDashboardIdDesc.getCDateTime();
//			// System.out.println("cDateTime::"+cDateTime);
//			String substring2 = cDateTime.substring(0, 10);
//			DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//			LocalDate currenDateTime2 = LocalDate.parse(substring2, format2);
//			// System.out.println("currenDateTime2::" + currenDateTime2);
//
//			if (currenDateTime1.equals(currenDateTime2)) {
//				return null;
//			} else {
//				findEquipmentAlarmHistoryByCDatetime();
//				findBEVInfeedDetailsByCurrentDate();
//				finds230InfeedDetailsByCurrentDate();
//				findbevOutfeedDetailsByCurrentDate();
//				finds230OutfeedDetailsByCurrentDate();
//				findOkCurrentStockByCurrentDate();
//
//				findNokCurrentStockByCurrentDate();
//				// findAllPickListDetails();
//				getNOkBEVMaterialCurrentStockDetails();
//				getNOkS230MaterialCurrentStockDetails();
//				getOkBEVMaterialCurrentStockDetails();
//				getOkS230MaterialCurrentStockDetails();
//				findTotalCurrentStockDetails();
//				getInfeedDetailsByDate();
//				getOutfeedDetailsByDate();
//
//				DashboardDetailsEntity dashboardDetailsEntity = new DashboardDetailsEntity(dashboardId, totalAlarmCount,
//						bevInfeedCount, s230InfeedCount, bevOutfeedCount, s230OutfeedCount, currentokMaterialCount,
//						currentNokMaterialCount, totalOrders, executedOrders, remainingOrders, percentageOrders,
//						totalNOKCount, totalOKCount, totalCurrentStock, totalInfeedCount, totalOutfeedCount,
//						createdDatetime);
////			System.out.println("totalAlarmCount::" + dashboardEntity.getTotalAlarmCount());
////			System.out.println("getBevOutfeedCount::" + dashboardEntity.getBevOutfeedCount());
////			System.out.println("getS230OutfeedCount::" + dashboardEntity.getS230OutfeedCount());
////			System.out.println("getCurrentokMaterialCount::" + dashboardEntity.getCurrentokMaterialCount());
////			System.out.println("getCurrentNokMaterialCount::" + dashboardEntity.getCurrentNokMaterialCount());
////			System.out.println("totalorders::" + dashboardEntity.getTotalOrders());
////			System.out.println("executedOrders::" + dashboardEntity.getExecutedOrders());
////			System.out.println("remainingOrders::" + dashboardEntity.getRemainingOrders());
////			System.out.println("percentageOrders::" + dashboardEntity.getPercentageOrders());
////			System.out.println("totalNOKCount::" + dashboardEntity.getTotalNOKCount());
////			System.out.println("totalOkCount::" + dashboardEntity.getTotalOKCount());
////			System.out.println("totalCurrentStock::" + dashboardEntity.getTotalCurrentStockCount());
////			System.out.println("totalInfeedCount::" + dashboardEntity.getTotalInfeedCount());
////			System.out.println("totalOutfeedCount::" + dashboardEntity.getTotalOutfeedCount());
////			System.out.println("date::" + createdDatetime);
//				dashboardDetailsRepository.save(dashboardDetailsEntity);
//				return dashboardDetailsEntity;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	public Optional<DashboardDetailsEntity> findAllDashboarsDetails() {
		try {

			findEquipmentAlarmHistoryByCDatetime();
			findBEVInfeedDetailsByCurrentDate();
			finds230InfeedDetailsByCurrentDate();
			findbevOutfeedDetailsByCurrentDate();
			finds230OutfeedDetailsByCurrentDate();
			findOkCurrentStockByCurrentDate();
			findNokCurrentStockByCurrentDate();
			// findAllPickListDetails();
			findTotalOrderDetailsByCurrentDate();
			openOrders();
			findExecutedOrder();
			percentageOrders();

			getNOkBEVMaterialCurrentStockDetails();
			getNOkS230MaterialCurrentStockDetails();
			getOkBEVMaterialCurrentStockDetails();
			getOkS230MaterialCurrentStockDetails();
			findTotalCurrentStockDetails();
			getInfeedDetailsByDate();
			getOutfeedDetailsByDate();

			Date date1 = new Date();
			SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentDateTime = simpleDateFormat1.format(date1);
//			CurrentPalletStockDetailsServiceInstance.findBEVCurrentStockDetails();
//			System.out.println("bev current stock::"+bevCurrentStockCount);
//			CurrentPalletStockDetailsServiceInstance.findS230CurrentStockDetails();
//			CurrentPalletStockDetailsServiceInstance.findBevEmptyPalletCountCurrentStockDetails();
			// System.out.println("totalOutfeedCount::" + totalOutfeedCount);
//			DashboardDetailsEntity dashboardDetailsEntity = new DashboardDetailsEntity(dashboardId, totalAlarmCount,
//					bevInfeedCount, s230InfeedCount, bevOutfeedCount, s230OutfeedCount, currentokMaterialCount,
//					currentNokMaterialCount, totalOrders, executedOrders, remainingOrders, percentageOrders,
//					totalNOKCount, totalOKCount, totalCurrentStock, totalInfeedCount, totalOutfeedCount,
//					currentDateTime);

			DashboardDetailsEntity dashboardDetailsEntity = new DashboardDetailsEntity(dashboardId, totalAlarmCount,
					bevInfeedCount, s230InfeedCount, bevOutfeedCount, s230OutfeedCount, currentokMaterialCount,
					currentNokMaterialCount, totalOrders, executedOrders, remainingOrders, percentageOrders,
					totalCurrentStock, totalInfeedCount, totalOutfeedCount, totalOKBEVCount, totalNOKBEVCount,
					totalS230OKCount, totalS230NOKCount, currentDateTime, bevCount, s230Count, emptyPalletCount);

			// iDashboardDetailsRepository.save(dashboardDetailsEntity);
			System.out.println(("Dashboard Entity::" + dashboardDetailsEntity));
			return Optional.of(dashboardDetailsEntity);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return Optional.empty();
	}

	@SuppressWarnings("null")
	public int findBEVCurrentStockDetails() {
		try {

			List<CurrentPalletStockDetailsEntity> findByProductName = currentPalletStockDetailsRepository
					.findByProductName("BEV");
			bevCurrentStockCount = (int) findByProductName.stream().filter(e -> e.getPalletStatusId() != 3).count();
			// currentStockDetails.setBevCurrentStockCount(bevCurrentStockCount);
			dashboardEntity.setBevCurrentStockCount(bevCurrentStockCount);
			return dashboardEntity.getBevCurrentStockCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Integer) null;
	}

	@SuppressWarnings("null")
	public int findS230CurrentStockDetails() {
		try {

			List<CurrentPalletStockDetailsEntity> findByProductName = currentPalletStockDetailsRepository
					.findByProductName("S230");
			s230CurrentStockCount = (int) findByProductName.stream().filter(e -> e.getPalletStatusId() != 3).count();
			// currentStockDetails.setS230CurrentStockCount(s230CurrentStockCount);
			dashboardEntity.setS230CurrentStockCount(s230CurrentStockCount);
			return dashboardEntity.getS230CurrentStockCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Integer) null;
	}

//	@SuppressWarnings("null")
//	public int findBevEmptyPalletCountCurrentStockDetails() {
//		try {
//
//			List<CurrentPalletStockDetailsEntity> findByProductName = currentPalletStockDetailsRepository
//					.findByProductVariantCodeAndQuantity("NA", 0);
//			emptyPalletCount = (int) findByProductName.stream().filter(e -> e.getPalletStatusId() == 3).count();
//		
//			dashboardEntity.setEmptyPalletCount(emptyPalletCount);
//			return dashboardEntity.getEmptyPalletCount();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return (Integer) null;
//	}

	@SuppressWarnings("null")
	public int findBevEmptyPalletCountCurrentStockDetails() {
		try {
			List<CurrentPalletStockDetailsEntity> findByProductName = currentPalletStockDetailsRepository
					.findByProductVariantCodeAndQuantity("NA", 0);

			emptyPalletCount = (int) findByProductName.stream().filter(e -> e.getPalletStatusId() == 3).filter(e -> {
				MasterPositionDetailsEntity positionDetails = masterPositionDetailsRepository
						.findById(e.getPositionId()).orElse(null);
				return positionDetails != null && positionDetails.getEmptyPalletPosition() == 0
						&& positionDetails.getIsMaterialLoaded() == 0;
			}).count();

			dashboardEntity.setEmptyPalletCount(emptyPalletCount);
			return dashboardEntity.getEmptyPalletCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Integer) null;
	}

	public DashboardDetailsEntity findAllDashboard() {
		DashboardDetailsEntity findTopByOrderByDashboardIdDesc = dashboardDetailsRepository
				.findTopByOrderByDashboardIdDesc();
		try {
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String currentDate = simpleDateFormat.format(date);

			// Date date = new Date();
			SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createdDatetime = simpleDateFormat1.format(date);

			findEquipmentAlarmHistoryByCDatetime();
			findBEVInfeedDetailsByCurrentDate();
			finds230InfeedDetailsByCurrentDate();
			findbevOutfeedDetailsByCurrentDate();
			finds230OutfeedDetailsByCurrentDate();
			findOkCurrentStockByCurrentDate();
			findNokCurrentStockByCurrentDate();
			// findAllPickListDetails();
			findTotalOrderDetailsByCurrentDate();
			openOrders();
			findExecutedOrder();
			percentageOrders();
			String percentageOrders2 = dashboardEntity.getPercentageOrders();
			System.out.println("percent::" + percentageOrders2);

			getNOkBEVMaterialCurrentStockDetails();
			getNOkS230MaterialCurrentStockDetails();
			getOkBEVMaterialCurrentStockDetails();
			getOkS230MaterialCurrentStockDetails();
			findTotalCurrentStockDetails();
			getInfeedDetailsByDate();
			getOutfeedDetailsByDate();

			findBevEmptyPalletCountCurrentStockDetails();
			findS230CurrentStockDetails();
			findBEVCurrentStockDetails();

//			CurrentPalletStockDetailsServiceInstance.findBEVCurrentStockDetails();
//			System.out.println("bev current stock::"+bevCurrentStockCount);
//			CurrentPalletStockDetailsServiceInstance.findS230CurrentStockDetails();
//			CurrentPalletStockDetailsServiceInstance.findBevEmptyPalletCountCurrentStockDetails();

//			List<DashboardDetailsEntity> findBycDateTimeBetween = dashboardDetailsRepository
//					.findBycDateTimeBetween(currentDate + " " + "00:00:00", currentDate + " " + "23:59:59");

			List<DashboardDetailsEntity> findAll = dashboardDetailsRepository.findAll();

			List<DashboardDetailsEntity> findBycDateTimeBetween = findAll.stream()
					.filter(e -> e.getCDateTime().substring(0, 10).equalsIgnoreCase(currentDate))
					.collect(Collectors.toList());

			if (findAll.isEmpty()) {
				DashboardDetailsEntity dashboardDetailsEntity = new DashboardDetailsEntity(1, totalAlarmCount,
						bevInfeedCount, s230InfeedCount, bevOutfeedCount, s230OutfeedCount, currentokMaterialCount,
						currentNokMaterialCount, totalOrders, executedOrders, remainingOrders, percentageOrders2,
						totalCurrentStock, totalInfeedCount, totalOutfeedCount, totalOKBEVCount, totalNOKBEVCount,
						totalS230OKCount, totalS230NOKCount, createdDatetime, bevCurrentStockCount,
						s230CurrentStockCount, emptyPalletCount);
				dashboardDetailsRepository.save(dashboardDetailsEntity);
			} else {
				if (findBycDateTimeBetween.isEmpty()) {
					DashboardDetailsEntity dashboardDetailsEntity = new DashboardDetailsEntity(
							findTopByOrderByDashboardIdDesc.getDashboardId() + 1, totalAlarmCount, bevInfeedCount,
							s230InfeedCount, bevOutfeedCount, s230OutfeedCount, currentokMaterialCount,
							currentNokMaterialCount, totalOrders, executedOrders, remainingOrders, percentageOrders2,
							totalCurrentStock, totalInfeedCount, totalOutfeedCount, totalOKBEVCount, totalNOKBEVCount,
							totalS230OKCount, totalS230NOKCount, createdDatetime, bevCurrentStockCount,
							s230CurrentStockCount, emptyPalletCount);
					dashboardDetailsRepository.save(dashboardDetailsEntity);
				} else {
					String cDateTime = findTopByOrderByDashboardIdDesc.getCDateTime();
					if (cDateTime.substring(0, 10).equalsIgnoreCase(currentDate)) {
						findTopByOrderByDashboardIdDesc.setTotalAlarmCount(totalAlarmCount);
						findTopByOrderByDashboardIdDesc.setBevInfeedCount(bevInfeedCount);
						findTopByOrderByDashboardIdDesc.setS230InfeedCount(s230InfeedCount);
						findTopByOrderByDashboardIdDesc.setBevOutfeedCount(bevOutfeedCount);
						findTopByOrderByDashboardIdDesc.setS230OutfeedCount(s230OutfeedCount);
						findTopByOrderByDashboardIdDesc.setCurrentokMaterialCount(currentokMaterialCount);
						findTopByOrderByDashboardIdDesc.setCurrentNokMaterialCount(currentNokMaterialCount);
						findTopByOrderByDashboardIdDesc.setTotalOrders(totalOrders);
						findTopByOrderByDashboardIdDesc.setExecutedOrders(executedOrders);
						findTopByOrderByDashboardIdDesc.setRemainingOrders(remainingOrders);
						findTopByOrderByDashboardIdDesc.setPercentageOrders(percentageOrders2);
						findTopByOrderByDashboardIdDesc.setTotalCurrentStockCount(totalCurrentStock);
						findTopByOrderByDashboardIdDesc.setTotalInfeedCount(totalInfeedCount);
						findTopByOrderByDashboardIdDesc.setTotalOutfeedCount(totalOutfeedCount);
						findTopByOrderByDashboardIdDesc.setTotalOKBEVCount(totalOKBEVCount);
						findTopByOrderByDashboardIdDesc.setTotalNOKBEVCount(totalNOKBEVCount);
						findTopByOrderByDashboardIdDesc.setTotalS230OKCount(totalS230OKCount);
						findTopByOrderByDashboardIdDesc.setTotalS230NOKCount(totalS230NOKCount);
						findTopByOrderByDashboardIdDesc.setCDateTime(createdDatetime);
						findTopByOrderByDashboardIdDesc.setBevCurrentStockCount(bevCurrentStockCount);
						findTopByOrderByDashboardIdDesc.setS230CurrentStockCount(s230CurrentStockCount);
						findTopByOrderByDashboardIdDesc.setEmptyPalletCount(emptyPalletCount);
						dashboardDetailsRepository.save(findTopByOrderByDashboardIdDesc);
					} 
//					else {
//						DashboardDetailsEntity dashboardDetailsEntity = new DashboardDetailsEntity(
//								findTopByOrderByDashboardIdDesc.getDashboardId() + 1, totalAlarmCount, bevInfeedCount,
//								s230InfeedCount, bevOutfeedCount, s230OutfeedCount, currentokMaterialCount,
//								currentNokMaterialCount, totalOrders, executedOrders, remainingOrders,
//								percentageOrders2, totalCurrentStock, totalInfeedCount, totalOutfeedCount,
//								totalOKBEVCount, totalNOKBEVCount, totalS230OKCount, totalS230NOKCount, createdDatetime,
//								bevCurrentStockCount, s230CurrentStockCount, emptyPalletCount);
//						dashboardDetailsRepository.save(dashboardDetailsEntity);
//					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return findTopByOrderByDashboardIdDesc;
	}

//	@Override
//	public ResponseEntity<String> addDashboardDetails() {
//		try {
//			Date date = new Date();
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			String currentDate = simpleDateFormat.format(date);
//
//			// Date date = new Date();
//			SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String createdDatetime = simpleDateFormat1.format(date);
//
//			if (findAllDashboarsDetails().isPresent()) {
//				// Date date2 = new Date();
//				// int currentHour = date2.getHours();
//				List<DashboardDetailsEntity> findByCreatedDatetimeBetween = dashboardDetailsRepository
//						.findBycDateTimeBetween(currentDate + " " + "00:00:00", currentDate + " " + "23:59:59");
//				// Optional<DashboardDetailsEntity> findAllDashboarsDetails =
//				// findAllDashboarsDetails();
//
//				if (findByCreatedDatetimeBetween.isEmpty()) {
//					DashboardDetailsEntity dashboardDetailsEntity2 = new DashboardDetailsEntity();
//
//					dashboardDetailsEntity2.setTotalAlarmCount(totalAlarmCount);
//					dashboardDetailsEntity2.setBevInfeedCount(bevInfeedCount);
//					dashboardDetailsEntity2.setS230InfeedCount(s230InfeedCount);
//					dashboardDetailsEntity2.setBevOutfeedCount(bevOutfeedCount);
//					dashboardDetailsEntity2.setS230OutfeedCount(s230OutfeedCount);
//					dashboardDetailsEntity2.setCurrentokMaterialCount(currentokMaterialCount);
//					dashboardDetailsEntity2.setCurrentNokMaterialCount(currentNokMaterialCount);
//					dashboardDetailsEntity2.setTotalOrders(totalOrders);
//					dashboardDetailsEntity2.setExecutedOrders(executedOrders);
//					dashboardDetailsEntity2.setRemainingOrders(remainingOrders);
//					dashboardDetailsEntity2.setPercentageOrders(percentageOrders);
////					dashboardDetailsEntity2.setTotalNOKCount(totalNOKCount);
////					dashboardDetailsEntity2.setTotalOKCount(totalOKCount);
//					dashboardDetailsEntity2.setTotalCurrentStockCount(totalCurrentStock);
//					dashboardDetailsEntity2.setTotalInfeedCount(totalInfeedCount);
//					dashboardDetailsEntity2.setTotalOutfeedCount(totalOutfeedCount);
//					dashboardDetailsEntity2.setTotalOKBEVCount(totalOKBEVCount);
//					dashboardDetailsEntity2.setTotalNOKBEVCount(totalNOKBEVCount);
//					dashboardDetailsEntity2.setTotalS230OKCount(totalS230OKCount);
//					dashboardDetailsEntity2.setTotalS230NOKCount(totalS230NOKCount);
//					dashboardDetailsEntity2.setCDateTime(createdDatetime);
//					dashboardDetailsEntity2.setBevCurrentStockCount(bevCurrentStockCount);
//					dashboardDetailsEntity2.setS230CurrentStockCount(s230CurrentStockCount);
//					dashboardDetailsEntity2.setEmptyPalletCount(emptyPalletCount);
//					dashboardDetailsRepository.save(dashboardDetailsEntity2);
//					return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
//				} else {
//					// for (int i = 0; i < findAll.size(); i++)
//					{
//						List<DashboardDetailsEntity> findBycreatedDatetimeBetween = dashboardDetailsRepository
//								.findBycDateTimeBetween(currentDate + " " + "00:00:00", currentDate + " " + "23:59:59");
//						if (findBycreatedDatetimeBetween.size() > 0) {
//							List<DashboardDetailsEntity> findAll2 = dashboardDetailsRepository.findAll();
//							int i = findAll2.size();
//							String cDateTime = findAll2.get(i - 1).getCDateTime();
//							String currentDate1 = cDateTime.substring(0, 10);
//							DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//							LocalDateTime currenDateTime1 = LocalDateTime.parse(cDateTime, format);
//
//							int currentHour1 = currenDateTime1.getHour();
//
//							if (currentDate.equalsIgnoreCase(currentDate1)) {
//
//								dashboardDetailsRepository.updateDashboardDetails(i,
//										findAllDashboarsDetails().get().getTotalAlarmCount(),
//										findAllDashboarsDetails().get().getBevInfeedCount(),
//										findAllDashboarsDetails().get().getS230InfeedCount(),
//										findAllDashboarsDetails().get().getBevOutfeedCount(),
//										findAllDashboarsDetails().get().getS230OutfeedCount(),
//										findAllDashboarsDetails().get().getCurrentokMaterialCount(),
//										findAllDashboarsDetails().get().getCurrentNokMaterialCount(),
//										findAllDashboarsDetails().get().getTotalOrders(),
//										findAllDashboarsDetails().get().getExecutedOrders(),
//										findAllDashboarsDetails().get().getRemainingOrders(),
//										findAllDashboarsDetails().get().getPercentageOrders(),
//										findAllDashboarsDetails().get().getTotalCurrentStockCount(),
//										findAllDashboarsDetails().get().getTotalInfeedCount(),
//										findAllDashboarsDetails().get().getTotalOutfeedCount(),
//										findAllDashboarsDetails().get().getTotalOKBEVCount(),
//										findAllDashboarsDetails().get().getTotalNOKBEVCount(),
//										findAllDashboarsDetails().get().getTotalS230OKCount(),
//										findAllDashboarsDetails().get().getTotalS230NOKCount(), createdDatetime,
//										findAllDashboarsDetails().get().getBevCurrentStockCount(),
//										findAllDashboarsDetails().get().getS230CurrentStockCount(),
//										findAllDashboarsDetails().get().getEmptyPalletCount());
//								return new ResponseEntity<>("Data updated successfully", HttpStatus.OK);
//							} else {
//
//								DashboardDetailsEntity dashboardDetailsEntity2 = new DashboardDetailsEntity();
//								dashboardDetailsEntity2.setTotalAlarmCount(totalAlarmCount);
//								dashboardDetailsEntity2.setBevInfeedCount(bevInfeedCount);
//								dashboardDetailsEntity2.setS230InfeedCount(s230InfeedCount);
//								dashboardDetailsEntity2.setBevOutfeedCount(bevOutfeedCount);
//								dashboardDetailsEntity2.setS230OutfeedCount(s230OutfeedCount);
//								dashboardDetailsEntity2.setCurrentokMaterialCount(currentokMaterialCount);
//								dashboardDetailsEntity2.setCurrentNokMaterialCount(currentNokMaterialCount);
////								dashboardDetailsEntity2.setTotalNOKCount(totalNOKCount);
////								dashboardDetailsEntity2.setTotalOKCount(totalOKCount);
//								dashboardDetailsEntity2.setTotalCurrentStockCount(totalCurrentStock);
//								dashboardDetailsEntity2.setTotalInfeedCount(totalInfeedCount);
//								dashboardDetailsEntity2.setTotalOutfeedCount(totalOutfeedCount);
//								dashboardDetailsEntity2.setCDateTime(createdDatetime);
//								dashboardDetailsEntity2.setBevCurrentStockCount(bevCurrentStockCount);
//								dashboardDetailsEntity2.setS230CurrentStockCount(s230CurrentStockCount);
//								dashboardDetailsEntity2.setEmptyPalletCount(emptyPalletCount);
//								dashboardDetailsRepository.save(dashboardDetailsEntity2);
//								return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
//							}
//
//						}
//
//					}
//
//				}
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ResponseEntity<>("Data not inserted", HttpStatus.BAD_REQUEST);
//	}

	public List<String> fetchFormattedDates() throws ParseException {
		List<DashboardDetailsEntity> list = new ArrayList<>(
				dashboardDetailsRepository.findTop7ByOrderByDashboardIdDesc());

		List<String> datetimeCon = new ArrayList<>();

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("dd MMM");

		for (int i = 0; i < list.size(); i++) {
			Date date = format1.parse(list.get(i).getCDateTime().substring(0, 10));
			list.get(i).setCDateTime(format2.format(date));
			datetimeCon.add(format2.format(date));
		}

		Collections.reverse(datetimeCon);
		return datetimeCon;
	}

	public List<DashboardDetailsEntity> fetchProductionTrendDetails() {
		try {
			List<DashboardDetailsEntity> list = new ArrayList<>(
					dashboardDetailsRepository.findTop7ByOrderByDashboardIdDesc());

			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat format2 = new SimpleDateFormat("dd MMM");

			for (int i = 0; i < list.size(); i++) {
				Date date = format1.parse(list.get(i).getCDateTime().substring(0, 10));
				list.get(i).setCDateTime(format2.format(date));
			}

			Collections.reverse(list);

			return list;
		} catch (Exception ex) {

			ex.printStackTrace();
			return null;

		}

	}

//	public Page<CurrentPalletStockDetailsEntity> findByQualityStatus(String qualityStatus,int page,int size){
//		return currentPalletStockDetailsRepository.findByQualityStatus(qualityStatus,PageRequest.of(page, size));
//		
	public Page<CurrentPalletStockDetailsEntity> findByQualityStatus(String qualityStatus, Pageable pageable) {
		Page<CurrentPalletStockDetailsEntity> findByQualityStatus = currentPalletStockDetailsRepository
				.findByQualityStatusAndPalletCodeNot(qualityStatus, "NA", pageable);
		return findByQualityStatus;
	}

	public Page<CurrentPalletStockDetailsEntity> getByQualityStatus(String qualityStatus, Pageable pageable) {
		Page<CurrentPalletStockDetailsEntity> getByQualityStatus = currentPalletStockDetailsRepository
				.getByQualityStatus(qualityStatus, pageable);
		return getByQualityStatus;
	}

	@SuppressWarnings("null")
	public int findTotalOrderDetailsByCurrentDate() {
		try {
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String createdDatetime = simpleDateFormat.format(date);

			List<GenerateManualRetrievalOrderEntity> orders = generateManualRetrievalOrderRepository
					.findByCreatedDatetimeBetweenAndIsOrderDeletedAndIsOrderCancelledAndDispatchStatus(
							createdDatetime + " " + "00:00:00", createdDatetime + " " + "23:59:59", 0, 0, "COMPLETED");
			List<GenerateManualRetrievalOrderEntity> findByDispatchStatusIn = generateManualRetrievalOrderRepository
					 .findByIsOrderDeletedAndDispatchStatusIn(0, Arrays.asList("READY", "IN_PROGRESS"));

			totalOrders = orders.size() + findByDispatchStatusIn.size();

			dashboardEntity.setTotalOrders(totalOrders);

			return dashboardEntity.getTotalOrders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Integer) null;
	}

	@SuppressWarnings("null")
	public int findExecutedOrder() {
		try {
//			Date date = new Date();
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			String createdDatetime = simpleDateFormat.format(date);
//
//			List<GenerateManualRetrievalOrderEntity> orders = generateManualRetrievalOrderRepository
//					.findByCreatedDatetimeBetweenAndDispatchStatus(createdDatetime + " " + "00:00:00",
//							createdDatetime + " " + "23:59:59", "COMPLETED");
//
//			executedOrders = orders.size();
//
//			dashboardEntity.setExecutedOrders(executedOrders);

			findTotalOrderDetailsByCurrentDate();
			openOrders();

			executedOrders = (dashboardEntity.getTotalOrders()) - (dashboardEntity.getRemainingOrders());
			dashboardEntity.setExecutedOrders(executedOrders);
			System.out.println("executedOrders::" + dashboardEntity.getExecutedOrders());
			return dashboardEntity.getExecutedOrders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Integer) null;
	}

	public int openOrders() {
		try {

			List<GenerateManualRetrievalOrderEntity> orders = generateManualRetrievalOrderRepository
					 .findByIsOrderDeletedAndDispatchStatusIn(0, Arrays.asList("READY", "IN_PROGRESS"));
			remainingOrders = orders.size();

			dashboardEntity.setRemainingOrders(remainingOrders);

			return dashboardEntity.getRemainingOrders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Integer) null;
	}

//	public String percentageOrders() {
//		try {
//
//			findTotalOrderDetailsByCurrentDate();
//			findExecutedOrder();
//		 
//			percentageOrders1 = (dashboardEntity.getExecutedOrders() * 100) / dashboardEntity.getTotalOrders();
//			percentageOrders=Float.toString(percentageOrders1);
//			DecimalFormat decimalFormatter = new DecimalFormat("0.000");
//			String format2 = decimalFormatter.format(percentageOrders1);
//			
//		//	 String format = String.format("%.2f", format2);
//			 
//			// System.out.println("formatted" + formattedTime);
////			percentageOrders=Float.toString(percentageOrders1);
//			dashboardEntity.setPercentageOrders(format2);
//			System.out.println("percentorder::"+dashboardEntity.getPercentageOrders());
//			return dashboardEntity.getPercentageOrders();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return  null;
//		
//	}
	public String percentageOrders() {
		try {
			findTotalOrderDetailsByCurrentDate();
			findExecutedOrder();

			if (dashboardEntity.getTotalOrders() == 0) {

				DecimalFormat decimalFormatter = new DecimalFormat("0.0000");
				formattedPercentage = decimalFormatter.format(0.0000);

				dashboardEntity.setPercentageOrders(formattedPercentage);
				System.out.println("percentorder::" + dashboardEntity.getPercentageOrders());

				return dashboardEntity.getPercentageOrders();
			}

			float percentageOrders1 = (dashboardEntity.getExecutedOrders() * 100.0f) / dashboardEntity.getTotalOrders();

			// Format to 3 decimal places
			DecimalFormat decimalFormatter = new DecimalFormat("0.0000");
			formattedPercentage = decimalFormatter.format(percentageOrders1);

			dashboardEntity.setPercentageOrders(formattedPercentage);
			System.out.println("percentorder::" + dashboardEntity.getPercentageOrders());

			return dashboardEntity.getPercentageOrders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	public void percentageOrders() {
//		try {
//			Date date = new Date();
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			String createdDatetime = simpleDateFormat.format(date);
//			List<GenerateManualRetrievalOrderEntity> findByCreatedDatetimeBetweenAndIsOrderDeletedAndIsOrderCancelled = generateManualRetrievalOrderRepository
//					.findByCreatedDatetimeBetweenAndIsOrderDeletedAndIsOrderCancelled(
//							createdDatetime + " " + "00:00:00", createdDatetime + " " + "23:59:59", 0, 0);
//			List<GenerateManualRetrievalOrderEntity> findByDispatchStatusIn = generateManualRetrievalOrderRepository
//					.findByDispatchStatusIn(Arrays.asList("READY", "IN_PROGRESS"));
//			totalOrders = findByCreatedDatetimeBetweenAndIsOrderDeletedAndIsOrderCancelled.size()
//					+ findByDispatchStatusIn.size();
//			dashboardEntity.setRemainingOrders(findByDispatchStatusIn.size());
//			dashboardEntity.setTotalOrders(totalOrders);
//			// executedOrders = (int) findAll.stream().filter(e -> e.getIsOrderFullFilled()
//			// == 1).count();
//			// dashboardEntity.setExecutedOrders(executedOrders);
//			executedOrders = totalOrders - remainingOrders;
//			dashboardEntity.setExecutedOrders(executedOrders);
//			percentageOrders = ((executedOrders * 100) / totalOrders);
//			dashboardEntity.getPercentageOrders();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
