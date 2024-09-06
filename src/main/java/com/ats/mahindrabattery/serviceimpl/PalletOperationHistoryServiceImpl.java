package com.ats.mahindrabattery.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.CurrentPalletStockDetailsEntity;
import com.ats.mahindrabattery.entity.MasterPalletInformationEntity;
import com.ats.mahindrabattery.entity.MasterProductVariantDetailsEntity;
import com.ats.mahindrabattery.entity.PalletOperationHistoryEntity;
import com.ats.mahindrabattery.repository.CurrentPalletStockDetailsRepository;
import com.ats.mahindrabattery.repository.MasterPalletInformationRepository;
import com.ats.mahindrabattery.repository.MasterProductVariantDetailsRepository;
import com.ats.mahindrabattery.repository.PalletOperationHistoryRepository;
import com.ats.mahindrabattery.service.PalletOperationHistoryService;

@Service
public class PalletOperationHistoryServiceImpl implements PalletOperationHistoryService {

	@Autowired
	private PalletOperationHistoryRepository palletOperationHistoryRepositoryInstance;

	@Autowired
	private MasterPalletInformationRepository masterPalletInformationDetailsRepositoryInstance;
	
	@Autowired
	private MasterProductVariantDetailsRepository masterProductVariantDetailsRepositoryInstance;
	@Autowired
	private CurrentPalletStockDetailsRepository currentPalletStockDetailsRepository;

	public List<PalletOperationHistoryEntity> fetchAllPalletOperationHistory(
			PalletOperationHistoryEntity palletOperationHistoryEntity) {
		try {
			List<PalletOperationHistoryEntity> findAllPalletOperationHistory = palletOperationHistoryRepositoryInstance
					.findAll();
			return findAllPalletOperationHistory;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<PalletOperationHistoryEntity> findByloadDateTimeAndOperationInfeedMissionDetails() {
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String currentDateTime = dateFormat.format(date);
            //System.out.println("currentDateTime " + currentDateTime);
			List<PalletOperationHistoryEntity> oadDateTimeAndOperationInfeedMissionList = palletOperationHistoryRepositoryInstance
					.findByLoadDateTimeBetween(currentDateTime + " " + "00:00:00", currentDateTime + " " + "23:59:59");
			Predicate<PalletOperationHistoryEntity> predOperation = data -> (data.getOperation().equals("Loading"));
			oadDateTimeAndOperationInfeedMissionList = oadDateTimeAndOperationInfeedMissionList.stream().filter(predOperation).collect(Collectors.toList());
			
			
			return oadDateTimeAndOperationInfeedMissionList;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<PalletOperationHistoryEntity> findByloadDateTimeAndOperationOutfeedMissionDetails() {
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String currentDateTime = dateFormat.format(date);

			List<PalletOperationHistoryEntity> loadDateTimeAndOperationOutfeedMissionList = palletOperationHistoryRepositoryInstance
					.findByLoadDateTimeBetween(currentDateTime + " " + "00:00:00", currentDateTime + " " + "23:59:59");
		
				Predicate<PalletOperationHistoryEntity> predOperation = data -> (data.getOperation().equals("Unloading"));
				loadDateTimeAndOperationOutfeedMissionList = loadDateTimeAndOperationOutfeedMissionList.stream().filter(predOperation).collect(Collectors.toList());
			
			
			
			return loadDateTimeAndOperationOutfeedMissionList;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<PalletOperationHistoryEntity> findBetweenStartDateandEndDate(String startDateTime, String endDateTime) {
		try {
			List<PalletOperationHistoryEntity> findBetweenStartDateandEndDate = palletOperationHistoryRepositoryInstance
					.findByLoadDateTimeBetween(startDateTime, endDateTime);
			return findBetweenStartDateandEndDate;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<PalletOperationHistoryEntity> findByproductVariantCode(String productVariantCode) {
		try {
			List<PalletOperationHistoryEntity> findByproductVariantCode = palletOperationHistoryRepositoryInstance
					.findByproductVariantCode(productVariantCode);
			return findByproductVariantCode;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<PalletOperationHistoryEntity> findByAllFilters(String startDate, String endDate,
			String productVariantCode, String areaName, String floorName, String operation) {
		List<String> filterList = new ArrayList<>();
		List<PalletOperationHistoryEntity> list = new ArrayList<>();

		if (!productVariantCode.equals("NA")) {
			filterList.add("productVariantCode");
			//System.out.println("productVariantCode= " + productVariantCode);
		}
		if (!areaName.equals("NA")) {
			filterList.add("areaName");
			//System.out.println("Area= " + areaName.length());
		}
		if (!floorName.equals("NA")) {
			filterList.add("floorName");
			//System.out.println("Floor= " + floorName);
		}
		if (!operation.equals("NA")) {
			filterList.add("operation");
			//System.out.println("Operation= " + operation);
		}

		Predicate<PalletOperationHistoryEntity> predProductVariantCode = data -> (data.getProductVariantCode()
				.equals(productVariantCode));
		//System.out.println("predicate product variant code=" + productVariantCode);
		Predicate<PalletOperationHistoryEntity> predArea = data -> (data.getAreaName().equals(areaName));
		//System.out.println("predicate area=" + areaName);
		Predicate<PalletOperationHistoryEntity> predFloor = data -> (data.getFloorName().equals(floorName));
		//System.out.println("Predicate floor=" + floorName);
		Predicate<PalletOperationHistoryEntity> predOperation = data -> (data.getOperation().equals(operation));
		//System.out.println("Predicate operation=" + operation);

		if (!(startDate.equals("NA")) && !(endDate.equals("NA"))) {
			String startDateTime = startDate.toString().replace("T", " ");
			String endDateTime = endDate.toString().replace("T", " ");
//			System.out.println("startDateTime " + startDateTime);
//			System.out.println("endDateTime " + endDateTime);
			
			list = palletOperationHistoryRepositoryInstance.findByLoadDateTimeBetween(startDateTime, endDateTime);					
			
			
			//System.out.println("Product Variant code::" + productVariantCode);
		} else {
			Date dateNow = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
			String date = dateFormat.format(dateNow);
		//	System.out.println("Date= " + date);
			
			list = palletOperationHistoryRepositoryInstance.findByLoadDateTimeBetween(date + " " + "00:00:00",
					date + " " + "23:59:59");
				
		}

		if (filterList.size() != 0) {
			//System.out.println("filter list size=" + filterList.size());
			for (int i = 0; i < filterList.size(); i++) {
				//System.out.println("filterlist content: " + filterList.get(i));
				if (filterList.get(i).equals("productVariantCode")) {
					list = list.stream().filter(predProductVariantCode).collect(Collectors.toList());
					//System.out.println("productVariantCode list size= " + list.size());
				} else if (filterList.get(i).equals("areaName")) {
					list = list.stream().filter(predArea).collect(Collectors.toList());
					//System.out.println("area list size= " + list.size());
				} else if (filterList.get(i).equals("floorName")) {
					list = list.stream().filter(predFloor).collect(Collectors.toList());
					//System.out.println("floor list size= " + list.size());
				} else if (filterList.get(i).equals("operation")) {
					list = list.stream().filter(predOperation).collect(Collectors.toList());
					//System.out.println("operation list size= " + list.size());
				}

			}
		}

		if (filterList.size() == 0 || list.size() == 0) {
//			String startDateTime = startDate.toString().replace("T", " ");
//			String endDateTime = endDate.toString().replace("T", " ");
//			System.out.println("startDateTime " + startDateTime);
//			System.out.println("endDateTime " + endDateTime);
//			list = palletOperationHistoryRepository.findBetweenStartDateandEndDate(startDateTime, endDateTime);
			list = null;
		}
		return list;

	}

	public List<PalletOperationHistoryEntity> findByOperation(String operation) {
		try {
			List<PalletOperationHistoryEntity> findByoperation = palletOperationHistoryRepositoryInstance
					.findByoperation(operation);
			return findByoperation;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<PalletOperationHistoryEntity> findAll() {
		try {
			return palletOperationHistoryRepositoryInstance.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public ResponseEntity<PalletOperationHistoryEntity> updatePalletOperationHistory(
			PalletOperationHistoryEntity palletOperationHistoryEntity, String palletCode, String productVariantCode) {
		List<PalletOperationHistoryEntity> palletOperationHistoryEntityList = null;
		try {
			palletOperationHistoryEntityList = palletOperationHistoryRepositoryInstance
					.findByPalletCodeAndProductVariantCode(palletOperationHistoryEntity.getPalletCode(),
							palletOperationHistoryEntity.getProductVariantCode());
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
			String date = ft.format(dNow);
			palletOperationHistoryEntity.setLoadDateTime(date);

			if (palletOperationHistoryEntityList.size() == 0) {

				List<PalletOperationHistoryEntity> findByPalletCodeAndProductVariantCode = palletOperationHistoryRepositoryInstance
						.findByPalletCodeAndProductVariantCode(palletCode, productVariantCode);
				PalletOperationHistoryEntity palletOperationHistoryEntity1 = new PalletOperationHistoryEntity();

				return new ResponseEntity<PalletOperationHistoryEntity>(
						palletOperationHistoryRepositoryInstance.save(palletOperationHistoryEntity), HttpStatus.OK);
			} else {

				return new ResponseEntity<PalletOperationHistoryEntity>(new PalletOperationHistoryEntity(),
						HttpStatus.ALREADY_REPORTED);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public PalletOperationHistoryEntity addManualOutfeedMissionDetails(
			PalletOperationHistoryEntity palletOperationHistoryEntity) {
		List<PalletOperationHistoryEntity> palletOperationHistoryList = null;
		try {
			palletOperationHistoryRepositoryInstance
					.findByPalletCodeAndProductVariantCodeAndProductVariantNameAndOrderIdAndQuantityAndUserNameAndLoadDateTimeAndProductName(
							palletOperationHistoryEntity.getPalletCode(),
							palletOperationHistoryEntity.getProductVariantCode(),
							palletOperationHistoryEntity.getProductVariantName(),
							palletOperationHistoryEntity.getOrderId(), palletOperationHistoryEntity.getQuantity(),
							palletOperationHistoryEntity.getUserName(), palletOperationHistoryEntity.getLoadDateTime(),
							palletOperationHistoryEntity.getProductName());

			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
			String date = ft.format(dNow);
			palletOperationHistoryEntity.setLoadDateTime(date);

			palletOperationHistoryEntity.setLoadDateTime(date);

			return palletOperationHistoryRepositoryInstance.save(palletOperationHistoryEntity);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return palletOperationHistoryEntity;
	}

	public ResponseEntity<String> addCurrentPalletStockDataInPalletOperationHistoryDetails(
			PalletOperationHistoryEntity palletOperationHistoryEntity) {
		List<MasterProductVariantDetailsEntity> productVariantList = null;
		List<MasterPalletInformationEntity> palletInformationList = null;
		List<CurrentPalletStockDetailsEntity> currentPalletStockDetailsEntityList = null;
//		System.out.println("palletcode" + palletOperationHistoryEntity.getPalletCode());
//		System.out.println("productVriantcode" + palletOperationHistoryEntity.getProductVariantCode());
		try {
			currentPalletStockDetailsEntityList = currentPalletStockDetailsRepository
					.findByPalletCodeAndProductVariantCode(palletOperationHistoryEntity.getPalletCode(),
							palletOperationHistoryEntity.getProductVariantCode());

			if (currentPalletStockDetailsEntityList.size() > 0) {
				int totalQtyOnPallet = 0;

				for (int i = 0; i < currentPalletStockDetailsEntityList.size(); i++) {

					totalQtyOnPallet += currentPalletStockDetailsEntityList.get(i).getQuantity();
				}

				if ((palletOperationHistoryEntity.getQuantity() > totalQtyOnPallet)
						&& palletOperationHistoryEntity.getOperation().equals("Unloading")) {
				//	System.out.println("total:: " + palletOperationHistoryEntity.getQuantity());
					return new ResponseEntity<String>("Not Enough Quantity", HttpStatus.BAD_REQUEST);
				}

			} else if (currentPalletStockDetailsEntityList.size() == 0
					&& palletOperationHistoryEntity.getOperation().equals("Unloading")) {
				return new ResponseEntity<String>("Not Enough Quantity", HttpStatus.BAD_REQUEST);
			}

			palletInformationList = masterPalletInformationDetailsRepositoryInstance
					.findByPalletCode(palletOperationHistoryEntity.getPalletCode());

			palletOperationHistoryEntity.setPalletInformationId(palletInformationList.get(0).getPalletInformationId());

			//System.out.println("PalletOperationHistoryController :: in add pallet operation history :: "
				//	+ palletOperationHistoryEntity.getPalletInformationId());
			productVariantList = masterProductVariantDetailsRepositoryInstance
					.findByProductVariantCode(palletOperationHistoryEntity.getProductVariantCode());
			if (productVariantList.size() > 0) {
				palletOperationHistoryEntity.setProductVariantName(productVariantList.get(0).getProductVariantname());
				//System.out.println(" date from frontend :: " + palletOperationHistoryEntity.getExpiryDate());
				palletOperationHistoryEntity.setProductVariantId(productVariantList.get(0).getProductVariantId());
				palletOperationHistoryEntity.setProductName(productVariantList.get(0).getProductName());
				palletOperationHistoryEntity.setProductId(productVariantList.get(0).getProductId());
				palletOperationHistoryEntity.setPositionName("NA");
				palletOperationHistoryEntity.setAreaName("NA");
				palletOperationHistoryEntity.setFloorName("NA");
				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
				String date = ft.format(dNow);
				palletOperationHistoryEntity.setLoadDateTime(date);

				palletOperationHistoryRepositoryInstance.save(palletOperationHistoryEntity);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public List<PalletOperationHistoryEntity> findByWmsTransferOrderIdOrderByPalletRetrievalHistoryIdDesc(
			String wmsTransferOrderId) {
//		System.out.println("trnasferorderid" + wmsTransferOrderId);
//		System.out.println("wmsTransferOrderId" + palletOperationHistoryRepositoryInstance
				//.findByWmsTransferOrderIdOrderByPalletRetrievalHistoryIdDesc(wmsTransferOrderId));
		try {
			List<PalletOperationHistoryEntity> findByWmsTransferOrderIdOrderByPalletRetrievalHistoryIdDesc = palletOperationHistoryRepositoryInstance
					.findByWmsTransferOrderIdOrderByPalletRetrievalHistoryIdDesc(wmsTransferOrderId);
			return findByWmsTransferOrderIdOrderByPalletRetrievalHistoryIdDesc;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
