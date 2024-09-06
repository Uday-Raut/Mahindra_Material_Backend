package com.ats.mahindrabattery.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.AuditTrailDetailsEntity;
import com.ats.mahindrabattery.entity.GenerateManualRetrievalOrderEntity;
import com.ats.mahindrabattery.entity.MasterPalletInformationEntity;
import com.ats.mahindrabattery.entity.MasterPositionDetailsEntity;
import com.ats.mahindrabattery.entity.TransferPalletMissionRuntimeDetailsEntity;
import com.ats.mahindrabattery.repository.AuditTrailDetailsRepository;
import com.ats.mahindrabattery.repository.MasterPalletInformationRepository;
import com.ats.mahindrabattery.repository.MasterPositionDetailsRepository;
import com.ats.mahindrabattery.repository.TransferPalletMissionRuntimeDetailsRepository;
import com.ats.mahindrabattery.service.TransferPalletMissionRuntimeDetailsService;



@Service
public class TransferPalletMissionRuntimeDetailsServiceImpl implements TransferPalletMissionRuntimeDetailsService {
	
	@Autowired
	private TransferPalletMissionRuntimeDetailsRepository transferPalletMissionRuntimeDetailsRepositoryInstance;
	
	@Autowired
	MasterPalletInformationRepository masterPalletInformationRepositoryInstance;

	@Autowired
	private MasterPositionDetailsRepository masterPositionDetailsRepositoryInstance;
	
	@Autowired
	private AuditTrailDetailsRepository auditTrailDetailsRepository;
	
	public List<TransferPalletMissionRuntimeDetailsEntity> fetchTransferPalletMissionRuntimeDetails() {
		try {
			return transferPalletMissionRuntimeDetailsRepositoryInstance.findByTransferMissionStatus("READY", "IN_PROGRESS");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	
	}
	
	
	public void updatePalletMissionRuntimeDetails(TransferPalletMissionRuntimeDetailsEntity transferPalletMissionRuntimeDetailsEntity) {
		
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		String currentDate = ft.format(dNow);

		int positionId = transferPalletMissionRuntimeDetailsEntity.getPreviousePositionId();
		int transferPositionId = transferPalletMissionRuntimeDetailsEntity.getTransferPositionId();
		MasterPositionDetailsEntity findByPositionId = masterPositionDetailsRepositoryInstance
				.findByPositionId(positionId);
		MasterPalletInformationEntity findByPalletInformationId = masterPalletInformationRepositoryInstance
				.findByPalletInformationId(transferPalletMissionRuntimeDetailsEntity.getPalletInformationId());
		MasterPositionDetailsEntity	findByPositionId2 = masterPositionDetailsRepositoryInstance
				.findByPositionId(transferPositionId);

		String transferMissionStatus = transferPalletMissionRuntimeDetailsEntity.getTransferMissionStatus();
		if (transferMissionStatus.equals("ABORT")) {
			findByPositionId.setIsManualDispatch(0);
			findByPositionId.setPositionIsAllocated(1);
			findByPositionId.setCDateTime(currentDate);
			findByPositionId.setEmptyPalletPosition(0);
			masterPositionDetailsRepositoryInstance.save(findByPositionId);
			
			findByPalletInformationId.setIsTransferManagementMissionGenerated(0);
			findByPalletInformationId.setCdatetime(currentDate);
			
			AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String name = authentication.getName();
			System.out.println(" name :: " + name);
			auditTrailDetailsEntity.setOperatorActions(
					"Transfer mission aborted from  " + transferPalletMissionRuntimeDetailsEntity.getPreviousPositionName()
							+ " to  " + transferPalletMissionRuntimeDetailsEntity.getTransferPositionName() +" having pallet code "+findByPalletInformationId.getPalletCode()+ " by " + name);
			auditTrailDetailsEntity.setField("Transfer mission Aborted");
//			auditTrailDetailsEntity.setAfterValue(0);
//			auditTrailDetailsEntity.setBeforeValue(0);
			auditTrailDetailsEntity.setReason("Transfer mission Aborted");

			auditTrailDetailsEntity.setUsername(name);
			auditTrailDetailsEntity.setDatetimeC(currentDate);
			auditTrailDetailsRepository.save(auditTrailDetailsEntity);
			masterPalletInformationRepositoryInstance.save(findByPalletInformationId);
		} else if (transferMissionStatus.equals("COMPLETED")) {
			findByPositionId.setIsManualDispatch(0);
			findByPositionId.setPositionIsAllocated(0);
			findByPositionId.setCDateTime(currentDate);
			findByPositionId.setEmptyPalletPosition(1);
			masterPositionDetailsRepositoryInstance.save(findByPositionId);
			findByPositionId2.setPositionIsAllocated(1);
			findByPositionId2.setEmptyPalletPosition(0);
		
			masterPositionDetailsRepositoryInstance.save(findByPositionId2);
			findByPalletInformationId.setIsTransferManagementMissionGenerated(0);
			findByPalletInformationId.setCdatetime(currentDate);
			
			AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String name = authentication.getName();
			System.out.println(" name :: " + name);
			auditTrailDetailsEntity.setOperatorActions(
					"Transfer mission Completed from  " + transferPalletMissionRuntimeDetailsEntity.getPreviousPositionName()
							+ " to  " + transferPalletMissionRuntimeDetailsEntity.getTransferPositionName() +" having pallet code "+findByPalletInformationId.getPalletCode()+ " by " + name);
			auditTrailDetailsEntity.setField("Transfer mission Completed");
//			auditTrailDetailsEntity.setAfterValue(0);
//			auditTrailDetailsEntity.setBeforeValue(0);
			auditTrailDetailsEntity.setReason("Transfer mission Completed");

			auditTrailDetailsEntity.setUsername(name);
			auditTrailDetailsEntity.setDatetimeC(currentDate);
			auditTrailDetailsRepository.save(auditTrailDetailsEntity);
//			
			masterPalletInformationRepositoryInstance.save(findByPalletInformationId);
		}
		System.out.println("masterPositionDetailsEntity" + findByPositionId);
		transferPalletMissionRuntimeDetailsRepositoryInstance.save(transferPalletMissionRuntimeDetailsEntity);
	}
	
	public List<TransferPalletMissionRuntimeDetailsEntity> getAllTransferPalletMissionRuntimeDetails() {
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String currentDateTime = dateFormat.format(date);
//			System.out.println("currentDateTime::"+currentDateTime);
//			System.out.println("curretdate::"+infeedMissionRuntimeDetailsRepository.getAllInfeedMissionRuntimeDetailsBetweenDates(
//					currentDateTime + " " + "00:00:00", currentDateTime + " " + "23:59:59"));
			return transferPalletMissionRuntimeDetailsRepositoryInstance.getAllGenerateManualRetrievalOrderBetweenDates(
					currentDateTime + " " + "00:00:00", currentDateTime + " " + "23:59:59");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}
	
	public List<TransferPalletMissionRuntimeDetailsEntity> getAllTransferPalletMissionDetailsDetails(
			) {
		try {
			List<TransferPalletMissionRuntimeDetailsEntity> findBytransferMissionIsDeleted = transferPalletMissionRuntimeDetailsRepositoryInstance
					.findBytransferMissionIsDeleted(0);
			
			return findBytransferMissionIsDeleted;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public List<TransferPalletMissionRuntimeDetailsEntity> findByAllFilters(String cDatetimeStart, String cDatetimeEnd,
			String palletCode, String productVariantCode) {
		List<String> filterList = new ArrayList<>();
		List<TransferPalletMissionRuntimeDetailsEntity> list = new ArrayList<>();

		if (!palletCode.equals("NA")) {
			filterList.add("palletCode");
			System.out.println("palletCode::" + palletCode);
		}
		if (!productVariantCode.equals("NA")) {
			filterList.add("productVariantCode");
			System.out.println("productVariantCode::" + productVariantCode);
		}
		
		Predicate<TransferPalletMissionRuntimeDetailsEntity> palletCodePred = data -> data.getPalletCode()
				.equals(palletCode);
		Predicate<TransferPalletMissionRuntimeDetailsEntity> productVariantCodePred = data -> data.getProductvariantCode()
				.equals(productVariantCode);
		

		if (!(cDatetimeStart.equals("NA")) && !(cDatetimeEnd.equals("NA"))) {
			String startDateTime = cDatetimeStart.toString().replace("T", " ");
			String endDateTime = cDatetimeEnd.toString().replace("T", " ");
			System.out.println("startDateTime= " + startDateTime);
			System.out.println("endDateTime= " + endDateTime);
			list=transferPalletMissionRuntimeDetailsRepositoryInstance.findBycdatetimeBetween(startDateTime, endDateTime);
			System.out.println("list1:"+list);
			
		} else {
			Date dateNow = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = dateFormat.format(dateNow);
			list=transferPalletMissionRuntimeDetailsRepositoryInstance.findBycdatetimeBetween(date + " " + "00:00:00",
					date + " " + "23:59:59");
			System.out.println("list2:"+list);
		}


		if (filterList.size() != 0) {
			
						for (int i = 0; i < filterList.size(); i++) {
			
							 if (filterList.get(i).equals("palletCode")) {
								list = list.stream().filter(palletCodePred).collect((Collectors.toList()));
							//	System.out.println("floor list::"+list.size());
							} 
							 else if (filterList.get(i).equals("productVariantCode")) {
								list = list.stream().filter(productVariantCodePred).collect((Collectors.toList()));
							//	System.out.println("area list::"+list.size());
							} 
							
						}
					}
					
					if (filterList.size() == 0 && list.size() == 0) {
						list = null;
					}
					return list;

	}

}
