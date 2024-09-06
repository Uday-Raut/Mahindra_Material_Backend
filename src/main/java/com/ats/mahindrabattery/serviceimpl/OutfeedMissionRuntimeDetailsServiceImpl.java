package com.ats.mahindrabattery.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.AuditTrailDetailsEntity;
import com.ats.mahindrabattery.entity.CurrentPalletStockDetailsEntity;
import com.ats.mahindrabattery.entity.MasterPalletInformationEntity;
import com.ats.mahindrabattery.entity.MasterPositionDetailsEntity;
import com.ats.mahindrabattery.entity.MasterUserDetailsEntity;
import com.ats.mahindrabattery.entity.OutfeedMissionRuntimeDetailsEntity;
import com.ats.mahindrabattery.repository.AuditTrailDetailsRepository;
import com.ats.mahindrabattery.repository.CurrentPalletStockDetailsRepository;
import com.ats.mahindrabattery.repository.MasterPalletInformationRepository;
import com.ats.mahindrabattery.repository.MasterPositionDetailsRepository;
import com.ats.mahindrabattery.repository.OutfeedMissionruntimeDetailsRepository;
import com.ats.mahindrabattery.service.OutfeedMissionRuntimeDetailsService;

@Service
public class OutfeedMissionRuntimeDetailsServiceImpl implements OutfeedMissionRuntimeDetailsService {

	@Autowired
	private OutfeedMissionruntimeDetailsRepository outfeedMissionruntimeDetailsRepository;

	@Autowired
	private MasterPositionDetailsRepository masterPositionDetailsRepositoryInstance;

	@Autowired
	MasterPalletInformationRepository masterPalletInformationRepositoryInstance;

	@Autowired
	private AuditTrailDetailsRepository auditTrailDetailsRepository;

	@Autowired
	private CurrentPalletStockDetailsRepository currentPalletStockDetailsRepository;

	public List<OutfeedMissionRuntimeDetailsEntity> getAllOutfeedMissionRuntimeDetails() {
		try {
			List<OutfeedMissionRuntimeDetailsEntity> findAllOutfeedMissionRuntimeDetails = outfeedMissionruntimeDetailsRepository
					.findAll();
			return findAllOutfeedMissionRuntimeDetails;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<OutfeedMissionRuntimeDetailsEntity> findByCreatedDateTime(String createdDatetime) {
		try {
			List<OutfeedMissionRuntimeDetailsEntity> findByCreatedDateTime = outfeedMissionruntimeDetailsRepository
					.findByCreatedDatetime(createdDatetime);
			return findByCreatedDateTime;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<OutfeedMissionRuntimeDetailsEntity> findOutfeedNissionRuntimeDetailsBetweenDates(String startDate,
			String endDate) {
		try {
			List<OutfeedMissionRuntimeDetailsEntity> findOutfeedMissionRuntimeDetailsBetweenDates = outfeedMissionruntimeDetailsRepository
					.findOutfeedMissionRuntimeDetailsBetweenDates(startDate, endDate);
			return findOutfeedMissionRuntimeDetailsBetweenDates;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<OutfeedMissionRuntimeDetailsEntity> getByDate() {
		try {
			Date date = new Date();
			String strDateFormat = "yyyy-MM-dd";
			DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
			String currentDateTime = dateFormat.format(date);
			// System.out.println("currentDateTime::"+currentDateTime);
			return outfeedMissionruntimeDetailsRepository.findOutfeedMissionRuntimeDetailsBetweenDates(
					currentDateTime + " " + "00:00:00", currentDateTime + " " + "23:59:59");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<OutfeedMissionRuntimeDetailsEntity> fetchOutfeedMissionRuntimeDetails() {
		try {
			return outfeedMissionruntimeDetailsRepository.findByOutfeedMissionStatus("READY", "IN_PROGRESS");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void updateOutfeedMissionRuntimeDetails(
			OutfeedMissionRuntimeDetailsEntity outfeedMissionRuntimeDetailsEntity) {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = ft.format(dNow);

		int positionId = outfeedMissionRuntimeDetailsEntity.getPositionId();
		MasterPositionDetailsEntity findByPositionId = masterPositionDetailsRepositoryInstance
				.findByPositionId(positionId);
		MasterPalletInformationEntity findByPalletInformationId = masterPalletInformationRepositoryInstance
				.findByPalletInformationId(outfeedMissionRuntimeDetailsEntity.getPalletInformationId());

		String outfeedMissionStatus = outfeedMissionRuntimeDetailsEntity.getOutfeedMissionStatus();
		if (outfeedMissionStatus.equals("ABORT")) {
			findByPositionId.setIsManualDispatch(0);
			findByPositionId.setPositionIsAllocated(1);
			findByPositionId.setEmptyPalletPosition(0);
			findByPositionId.setCDateTime(currentDate);
			masterPositionDetailsRepositoryInstance.save(findByPositionId);
			findByPalletInformationId.setIsOutfeedMissionGenerated(0);
//			findByPalletInformationId.setCdatetime(currentDate);

			AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String name = authentication.getName();
			System.out.println(" name :: " + name);
			auditTrailDetailsEntity
					.setOperatorActions("Outfeed mission aborted for position " + findByPositionId.getPositionName()
							+ " having pallet code " + findByPalletInformationId.getPalletCode() + " by " + name);
			auditTrailDetailsEntity.setField("Outfeed mission Aborted");
//			auditTrailDetailsEntity.setAfterValue(0);
//			auditTrailDetailsEntity.setBeforeValue(0);
			auditTrailDetailsEntity.setReason("Outfeed mission Aborted");

			auditTrailDetailsEntity.setUsername(name);
			auditTrailDetailsEntity.setDatetimeC(currentDate);
			auditTrailDetailsRepository.save(auditTrailDetailsEntity);

			masterPalletInformationRepositoryInstance.save(findByPalletInformationId);
//			outfeedMissionRuntimeDetailsEntity.setOutfeedMissionEndDateTime(currentDate);
//			outfeedMissionruntimeDetailsRepository.save(outfeedMissionRuntimeDetailsEntity);
		} else if (outfeedMissionStatus.equals("COMPLETED")) {
			findByPositionId.setIsManualDispatch(0);
			findByPositionId.setPositionIsAllocated(0);
			findByPositionId.setEmptyPalletPosition(1);
			findByPositionId.setCDateTime(currentDate);
			masterPositionDetailsRepositoryInstance.save(findByPositionId);
			findByPalletInformationId.setIsOutfeedMissionGenerated(0);
			findByPalletInformationId.setIsInfeedMissionGenerated(0);
			findByPalletInformationId.setCdatetime(currentDate);
			masterPalletInformationRepositoryInstance.save(findByPalletInformationId);
			outfeedMissionRuntimeDetailsEntity.setOutfeedMissionEndDateTime(currentDate);

			AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String name = authentication.getName();
			System.out.println(" name :: " + name);
			auditTrailDetailsEntity
					.setOperatorActions("Outfeed mission Completed for position " + findByPositionId.getPositionName()
							+ " having pallet code " + findByPalletInformationId.getPalletCode() + " by " + name);
			auditTrailDetailsEntity.setField("Outfeed mission Completed");
//			auditTrailDetailsEntity.setAfterValue(0);
//			auditTrailDetailsEntity.setBeforeValue(0);
			auditTrailDetailsEntity.setReason("Outfeed mission Completed");

			auditTrailDetailsEntity.setUsername(name);
			auditTrailDetailsEntity.setDatetimeC(currentDate);
			auditTrailDetailsRepository.save(auditTrailDetailsEntity);

			outfeedMissionruntimeDetailsRepository.save(outfeedMissionRuntimeDetailsEntity);
			System.out.println("outfeedMissionRuntimeDetailsEntity::" + outfeedMissionRuntimeDetailsEntity);

			List<CurrentPalletStockDetailsEntity> findByPositionId2 = currentPalletStockDetailsRepository
					.findByPositionId(positionId);
			findByPositionId2.get(0).setPalletCode("NA");
			findByPositionId2.get(0).setPalletInformationId(0);
			findByPositionId2.get(0).setSerialNumber(0);
			findByPositionId2.get(0).setProductVariantCode("NA");
			findByPositionId2.get(0).setProductId(0);
			findByPositionId2.get(0).setProductName("NA");
			findByPositionId2.get(0).setProductVariantId(0);
			findByPositionId2.get(0).setProductVariantName("NA");
			findByPositionId2.get(0).setPalletStatusId(0);
			findByPositionId2.get(0).setPalletStatusname("NA");
			findByPositionId2.get(0).setAgeingDays(0);
			findByPositionId2.get(0).setQuantity(0);
			findByPositionId2.get(0).setQualityStatus("NA");
			findByPositionId2.get(0).setBatchNumber("NA");
			findByPositionId2.get(0).setModelNumber("NA");
			findByPositionId2.get(0).setLocation("NA");
			findByPositionId2.get(0).setPositionId(outfeedMissionRuntimeDetailsEntity.getPositionId());
			findByPositionId2.get(0).setPositionName(outfeedMissionRuntimeDetailsEntity.getPositionName());
			findByPositionId2.get(0)
					.setPositionNumberInRack(outfeedMissionRuntimeDetailsEntity.getPositionNumberInRack());
			findByPositionId2.get(0).setRackId(outfeedMissionRuntimeDetailsEntity.getRackId());
			findByPositionId2.get(0).setRackName(outfeedMissionRuntimeDetailsEntity.getRackName());
			findByPositionId2.get(0).setRackSide(outfeedMissionRuntimeDetailsEntity.getRackSide());
			findByPositionId2.get(0).setRackColumn(outfeedMissionRuntimeDetailsEntity.getRackColumn());
			findByPositionId2.get(0).setFloorId(outfeedMissionRuntimeDetailsEntity.getFloorId());
			findByPositionId2.get(0).setFloorName(outfeedMissionRuntimeDetailsEntity.getFloorName());
			findByPositionId2.get(0).setAreaId(outfeedMissionRuntimeDetailsEntity.getAreaId());
			findByPositionId2.get(0).setAreaName(outfeedMissionRuntimeDetailsEntity.getAreaName());
			findByPositionId2.get(0).setLoadDatetime("NA");
			findByPositionId2.get(0).setIsOutfeedMissionGenerated(0);
			findByPositionId2.get(0).setIsInfeedMissionGenerated(0);
			findByPositionId2.get(0).setUserId(0);
			findByPositionId2.get(0).setUserName("NA");
//			findByPositionId2.get(0).setVendorCode("NA");
//			findByPositionId2.get(0).setPartIdentificationCode("NA");
//			findByPositionId2.get(0).setMfgDate("NA");
//			findByPositionId2.get(0).setMfgShift("NA");
			
			currentPalletStockDetailsRepository.save(findByPositionId2.get(0));
		}
		System.out.println("masterPositionDetailsEntity" + findByPositionId);
		outfeedMissionruntimeDetailsRepository.save(outfeedMissionRuntimeDetailsEntity);

	}

	public List<OutfeedMissionRuntimeDetailsEntity> findByAllFilters(String floorName, String areaName,
			String outfeedMissionStatus, String outfeedMissionCdatetimeStart, String outfeedMissionCdatetimeEnd) {
		List<String> filterList = new ArrayList<String>();
		// System.out.println("filterList ::"+filterList);
		List<OutfeedMissionRuntimeDetailsEntity> list = new ArrayList<OutfeedMissionRuntimeDetailsEntity>();

		if (!floorName.equals("NA")) {
			filterList.add("floorName");
		}
		if (!areaName.equals("NA")) {
			filterList.add("areaName");
		}
		if (!outfeedMissionStatus.equals("NA")) {
			filterList.add("outfeedMissionStatus");
		}

		Predicate<OutfeedMissionRuntimeDetailsEntity> floorNamePred = data -> data.getFloorName().equals(floorName);
		Predicate<OutfeedMissionRuntimeDetailsEntity> areaNamePred = data -> (data.getAreaName().equals(areaName));
		Predicate<OutfeedMissionRuntimeDetailsEntity> outfeedMissionStatusPred = data -> data.getOutfeedMissionStatus()
				.equals(outfeedMissionStatus);

		if (!(outfeedMissionCdatetimeStart.equals("NA")) && !(outfeedMissionCdatetimeEnd.equals("NA"))) {
			// Removinf "T" from dataetime format
			// System.out.println("startDateTime::"+
			// outfeedMissionCdatetimeStart.toString().replace("T", " "));
			String startDateTime = outfeedMissionCdatetimeStart.toString().replace("T", " ");
			// System.out.println("endDateTime ::"+
			// outfeedMissionCdatetimeEnd.toString().replace("T", " "));
			String endDateTime = outfeedMissionCdatetimeEnd.toString().replace("T", " ");

			list = outfeedMissionruntimeDetailsRepository.findOutfeedMissionRuntimeDetailsBetweenDates(startDateTime,
					endDateTime);
		} else {
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			String date = ft.format(dNow);
			list = outfeedMissionruntimeDetailsRepository
					.findOutfeedMissionRuntimeDetailsBetweenDates(date + " " + "00:00:00", date + " " + "23:59:59");
			// System.out.println("filtered date::"+list);
		}

		if (filterList.size() != 0) {

			for (int i = 0; i < filterList.size(); i++) {
				if (filterList.get(i).equals("floorName")) {
					list = list.stream().filter(floorNamePred).collect((Collectors.toList()));
				} else if (filterList.get(i).equals("areaName")) {
					list = list.stream().filter(areaNamePred).collect((Collectors.toList()));
				} else if (filterList.get(i).equals("outfeedMissionStatus")) {
					list = list.stream().filter(outfeedMissionStatusPred).collect((Collectors.toList()));
				}
			}
		}

		if (filterList.size() == 0 && list.size() == 0) {
			list = null;
		}
		return list;

	}

}
