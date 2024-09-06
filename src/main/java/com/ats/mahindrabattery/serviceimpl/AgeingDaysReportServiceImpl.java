package com.ats.mahindrabattery.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.AgeingDaysReportEntity;
import com.ats.mahindrabattery.entity.CurrentPalletStockDetailsEntity;
import com.ats.mahindrabattery.entity.GenerateManualRetrievalOrderEntity;
import com.ats.mahindrabattery.entity.MasterAgeingDaysDetailsEntity;
import com.ats.mahindrabattery.entity.MasterProductVariantDetailsEntity;
import com.ats.mahindrabattery.entity.OutfeedMissionRuntimeDetailsEntity;
import com.ats.mahindrabattery.repository.CurrentPalletStockDetailsRepository;
import com.ats.mahindrabattery.repository.IMasterAgeingDaysDetailsRepository;
import com.ats.mahindrabattery.repository.MasterProductVariantDetailsRepository;
import com.ats.mahindrabattery.response.ResponseHandler;
import com.ats.mahindrabattery.service.AgeingDaysReportService;

@Service
public class AgeingDaysReportServiceImpl implements AgeingDaysReportService {

	int days;
//	int ageingDays;
//	int ageingDays1;
//	int ageingDays2;
//	int ageingDays3;
//	int aboveAgeingDays;
//	int belowAgeingDays;

	AgeingDaysReportEntity ageingDaysReportEntity = new AgeingDaysReportEntity();

	@Autowired
	private CurrentPalletStockDetailsRepository currentPalletStockDetailsRepository;

	@Autowired
	private MasterProductVariantDetailsRepository masterProductVariantRepository;

	@Autowired
	private IMasterAgeingDaysDetailsRepository masterAgeingDaysDetailsRepository;



	public List<AgeingDaysReportEntity> findByAllFilters(String productVariantCode) {
		List<String> filterList = new ArrayList<>();
		List<AgeingDaysReportEntity> list = new ArrayList<>();

		if (!productVariantCode.equals("NA")) {
			filterList.add("productVariantCode");
			System.out.println("productVariantCode::" + productVariantCode);
		}

		Predicate<? super AgeingDaysReportEntity> productVariantCodePred = data -> data.getProductvariantCode()
				.equals(productVariantCode);

		if (filterList.size() != 0) {

			for (int i = 0; i < filterList.size(); i++) {

				if (filterList.get(i).equals("productVariantCode")) {
					list = list.stream().filter(productVariantCodePred).collect((Collectors.toList()));
					// System.out.println("area list::"+list.size());
				}

			}
		}

		if (filterList.size() == 0 && list.size() == 0) {
			list = null;
		}
		return list;

	}

	@Override
	public List<CurrentPalletStockDetailsEntity> findAgeingDays() {
		try {

			List<CurrentPalletStockDetailsEntity> findByProductNameNot = currentPalletStockDetailsRepository
					.findByProductNameNot("NA");
			for (int i = 0; i < findByProductNameNot.size(); i++) {
				String loadDatetime = findByProductNameNot.get(i).getLoadDatetime();
				String loadDate = loadDatetime.substring(0, 10);
				// System.out.println("load date::" + loadDate);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
				LocalDate expiryDate = LocalDate.parse(loadDate, formatter);
				LocalDate currentDate = LocalDate.now();
				days = (int) ChronoUnit.DAYS.between(expiryDate, currentDate);
				// CurrentPalletStockDetailsEntity currentPalletStockDetailsEntity = new
				// CurrentPalletStockDetailsEntity();
				currentPalletStockDetailsRepository.updateAgeingDays(days,
						findByProductNameNot.get(i).getCurrentPalletStockDetailsId());
				// System.out.println("current stock::");

				// currentPalletStockDetailsEntity.setAgeingDays(days);
//				currentPalletStockDetailsRepository.save(currentPalletStockDetailsEntity);
				// System.out.println("currentPalletStockDetailsEntity::" +
				// findByProductNameNot.toString());
			}
			return findByProductNameNot;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	@Override
//	public List<AgeingDaysReportEntity> findInventoryAgeingReport() {
//		try {
//			List<CurrentPalletStockDetailsEntity> findAll = currentPalletStockDetailsRepository
//					.findByProductNameNot("NA");
//			List<AgeingDaysReportEntity> list = new ArrayList<>();
//			List<AgeingDaysReportEntity> list1 = new ArrayList<>();
//			// List<AgeingDaysReportEntity> list1 = new ArrayList<>();
//			for (int i = 0; i < findAll.size(); i++) {
//				Date date = new Date();
//				String strDateFormat = "yyyy-MM-dd";
//				DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
//				String currentDateTime = dateFormat.format(date);
//				String productVariantName = findAll.get(i).getProductVariantName();
//				int productVariantId = findAll.get(i).getProductVariantId();
//				int quantity = findAll.get(i).getQuantity();
//				String qualityStatus = findAll.get(i).getQualityStatus();
//				String productVariantCode1 = findAll.get(i).getProductVariantCode();
//				System.out.println("findall size::" + findAll.size());
//				System.out.println("i::" + i);
//				if (i == findAll.size()-1) {
////					String productVariantCode2 = findAll.get(i).getProductVariantCode();
////					if (productVariantCode1.equals(productVariantCode2)) {
//					int ageingDays1 = findAll.get(i).getAgeingDays();
//					System.out.println("ageingDays1::" + ageingDays1);
//					// ageingDays2 = findAll.get(i + 1).getAgeingDays();
//					// ageingDays = ageingDays1 + ageingDays2;
//					int aboveAgeingDays=0;
//					int belowAgeingDays=0;
//					if (ageingDays1 > 15) {
//					aboveAgeingDays = aboveAgeingDays + 1;
//						System.out.println("aboveAgeingDays ::" + aboveAgeingDays);
//					} else if (ageingDays1 < 15) {
//					belowAgeingDays = belowAgeingDays + 1;
//						System.out.println("belowAgeingDays ::" + belowAgeingDays);
//
//					} else {
//						System.out.println("ageingDays1::" + ageingDays1);
//					}
//					list.add(
//							new AgeingDaysReportEntity(i + 1, productVariantCode1, productVariantName, productVariantId,
//									belowAgeingDays, aboveAgeingDays, quantity, qualityStatus, currentDateTime));
//
//				} else {
//					String productVariantCode2 = findAll.get(i + 1).getProductVariantCode();
//					if (productVariantCode1.equals(productVariantCode2)) {
//						int ageingDays1 = findAll.get(i).getAgeingDays();
//						int ageingDays2 = findAll.get(i + 1).getAgeingDays();
//						int ageingDays = ageingDays1 + ageingDays2;
//						int aboveAgeingDays=0;
//						int belowAgeingDays=0;
//						if (ageingDays > 15) {
//							aboveAgeingDays = aboveAgeingDays + 1;
//							System.out.println("aboveAgeingDays ::" + aboveAgeingDays);
//						} else if (ageingDays < 15) {
//							belowAgeingDays = belowAgeingDays + 1;
//							System.out.println("belowAgeingDays ::" + belowAgeingDays);
//
//						} else {
//							System.out.println("ageingDays::" + ageingDays);
//						}
//						list.add(new AgeingDaysReportEntity(i + 1, productVariantCode2, productVariantName,
//								productVariantId, belowAgeingDays, aboveAgeingDays, quantity, qualityStatus,
//								currentDateTime));
//
//					}
////				else {
////					ageingDays = findAll.get(i).getAgeingDays();
////					if (ageingDays > 15) {
////						aboveAgeingDays = aboveAgeingDays + 1;
////						System.out.println("aboveAgeingDays ::" + aboveAgeingDays);
////					} else if (ageingDays < 15) {
////						belowAgeingDays = belowAgeingDays + 1;
////						System.out.println("belowAgeingDays ::" + belowAgeingDays);
////					} else {
////						System.out.println("ageingDays::" + ageingDays);
////					}
////					list1.add(
////							new AgeingDaysReportEntity(i + 1, productVariantCode1, productVariantName, productVariantId,
////									belowAgeingDays, aboveAgeingDays, quantity, qualityStatus, currentDateTime));
////					}
//					// }
//				}
//				
//				
//				
//			}
//			System.out.println("list::" + list);
//			list1=list.stream().filter(distinctByKey(CurrentPalletStockDetailsEntity::getProductVariantCode)).collect(Collectors.toList());
//			System.out.println("list1::"+list1);
//			return list1;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//
//	}

//	@SuppressWarnings("unchecked")
//	public static <T> Predicate<? super AgeingDaysReportEntity> distinctByKey(Function<? super T, ?> keyExtractor) {
//	    Set<Object> seen = ConcurrentHashMap.newKeySet();
//	    return t -> seen.add(keyExtractor.apply((T) t));
//	}

	// find above ageing days material
	public List<AgeingDaysReportEntity> findMaterialAboveAgeingDayaCount() {

		try {

			List<AgeingDaysReportEntity> MaterialAboveDaysDetails = new ArrayList<>();

			// MaterialAboveDaysDetails.removeAll(MaterialAboveDaysDetails);

			// List<MasterAgeingDaysDetailsEntity> againgDaysList =
			// ageingDaysDetailsRepository.findAll();

			List<MasterAgeingDaysDetailsEntity> againgDaysList = masterAgeingDaysDetailsRepository.findAll();

			int actualAgeingDays = againgDaysList.get(0).getAgeingDays();

			// int actualAgeingDays = 15;

			// System.out.println("actualAgeingDays :: " + actualAgeingDays);

			List<MasterProductVariantDetailsEntity> allProductVariants = masterProductVariantRepository
					.findByproductVariantIsDeleted(0);

			// System.out.println("allProductVariants :: " + allProductVariants.size());

			List<CurrentPalletStockDetailsEntity> currentStockList1 = currentPalletStockDetailsRepository
					.findByProductNameNot("NA");

			for (int i = 0; i < allProductVariants.size(); i++) {

//					List<CurrentStockDetailsEntity> currentStockList = currentStockDetailsRepository.findByProductVariantCodeAndAgeingDaysGreaterThan(
//							allProductVariants.get(i).getProductVariantCode(),actualAgeingDays);
//					
//									
//					currentStockAboveAgeingDays = 2 * currentStockList.size();
//					
//					currentStockMaterialAboveAgeingDaysCountEntity.setProductVariantCode(allProductVariants.get(i).getProductVariantCode());
//					currentStockMaterialAboveAgeingDaysCountEntity.setAboveAgeingDaysCount(currentStockAboveAgeingDays);

				String pvar = allProductVariants.get(i).getProductVariantCode();

//					System.out.println("In for loop ");

				List<CurrentPalletStockDetailsEntity> productVariantCode1 = currentStockList1.stream()
						.filter(e -> (e.getProductVariantCode().equals(pvar) && e.getAgeingDays() >= 0
								&& e.getAgeingDays() <= 3))
						.collect(Collectors.toList());

				List<CurrentPalletStockDetailsEntity> productVariantCode2 = currentStockList1.stream()
						.filter(e -> (e.getProductVariantCode().equals(pvar) && e.getAgeingDays() >= 4
								&& e.getAgeingDays() <= 7))
						.collect(Collectors.toList());

				List<CurrentPalletStockDetailsEntity> productVariantCode3 = currentStockList1.stream()
						.filter(e -> (e.getProductVariantCode().equals(pvar) && e.getAgeingDays() >= 8
								&& e.getAgeingDays() <= 14))
						.collect(Collectors.toList());

				List<CurrentPalletStockDetailsEntity> productVariantCode4 = currentStockList1.stream()
						.filter(e -> (e.getProductVariantCode().equals(pvar) && e.getAgeingDays() >= 15
								&& e.getAgeingDays() <= 30))
						.collect(Collectors.toList());

				List<CurrentPalletStockDetailsEntity> productVariantCode5 = currentStockList1.stream()
						.filter(e -> (e.getProductVariantCode().equals(pvar) && e.getAgeingDays() > 30))
						.collect(Collectors.toList());

//					System.out.println("productVariantCode :: " + productVariantCode.size());

				int range0to3 = productVariantCode1.size();

				int range4to7 = productVariantCode2.size();

				int range8to14 = productVariantCode3.size();

				int range15to30 = productVariantCode4.size();

				int range30plus = productVariantCode5.size();

				int total = range0to3 + range4to7 + range8to14 + range15to30 + range30plus;

				// MaterialAboveDaysDetails.addAll(productVariantCode);
//				MaterialAboveDaysDetails.add(new AgeingDaysReportEntity(i, allProductVariants.get(i).getProductName(),
//						allProductVariants.get(i).getProductVariantCode(),
//						allProductVariants.get(i).getProductVariantname(), belowAgeingDaysCount, aboveAgeingDaysCount,
//						total));

				MaterialAboveDaysDetails.add(new AgeingDaysReportEntity(i, allProductVariants.get(i).getProductName(),
						allProductVariants.get(i).getProductVariantCode(),
						allProductVariants.get(i).getProductVariantname(), range0to3, range4to7, range8to14,
						range15to30, range30plus, total));

				// System.out.println("ProductVariantCode :: "
				// +allProductVariants.get(i).getProductVariantCode() + ","+
				// "currentStockAboveAgeingDays :: " + currentStockAboveAgeingDays);

			}
//			Collections.sort(MaterialAboveDaysDetails, new Comparator<AgeingDaysReportEntity>() {
//
//				@Override
//				public int compare(AgeingDaysReportEntity o1, AgeingDaysReportEntity o2) {
//
//					return o2.getAboveAgingDaysQuantity() - o1.getAboveAgingDaysQuantity();
//				}
//
//			});
			return MaterialAboveDaysDetails;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<AgeingDaysReportEntity> findInventoryAgeingReport() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
}