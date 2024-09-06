package com.ats.mahindrabattery.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ats.mahindrabattery.entity.AuditTrailDetailsEntity;
import com.ats.mahindrabattery.entity.CurrentPalletStockDetailsEntity;
import com.ats.mahindrabattery.entity.ManualOutfeedMissionDetailsEntity;
import com.ats.mahindrabattery.entity.MappingFloorAreaDetailsEntity;
import com.ats.mahindrabattery.entity.MasterPositionDetailsEntity;
import com.ats.mahindrabattery.repository.AuditTrailDetailsRepository;
import com.ats.mahindrabattery.repository.CurrentPalletStockDetailsRepository;
import com.ats.mahindrabattery.repository.ManualOutfeedMissionDetailsRepository;
import com.ats.mahindrabattery.repository.MappingFloorAreaDetailsRepository;
import com.ats.mahindrabattery.repository.MasterPositionDetailsRepository;
import com.ats.mahindrabattery.service.ManualOutfeedMissionDetailsService;

@Service
public class ManualOutfeedMissionDetailsServiceImpl implements ManualOutfeedMissionDetailsService {

	@Autowired
	ManualOutfeedMissionDetailsRepository manualOutfeedMissionDetailsRepositoryInstance;

	@Autowired
	CurrentPalletStockDetailsRepository currentPalletStockDetailsRepositoryInstance;

	@Autowired
	MasterPositionDetailsRepository masterPositionDetalisRepositoryInstance;

	@Autowired
	MappingFloorAreaDetailsRepository MappingFloorAreaDetailsRepositoryInstance;

	@Autowired
	private AuditTrailDetailsRepository auditTrailDetailsRepository;

	public List<ManualOutfeedMissionDetailsEntity> findAll() {
		return manualOutfeedMissionDetailsRepositoryInstance.findAll();
	}

	public ResponseEntity<ManualOutfeedMissionDetailsEntity> addCurrentPalletStockDetailsInManualOutfeed(
			ManualOutfeedMissionDetailsEntity manualOutfeedMissionDetailsEntity) {

		try {
			Date dNow = new Date();
			SimpleDateFormat sdateformat = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
			String date = sdateformat.format(dNow);
			List<ManualOutfeedMissionDetailsEntity> manuallist = null;
			List<CurrentPalletStockDetailsEntity> currentlist = null;
			List<MappingFloorAreaDetailsEntity> mappingFloorAreaDetailsEntity = null;
			ManualOutfeedMissionDetailsEntity findByPalletCodeAndIsMissionGenerated = null;
			// MasterPositionDetailsEntity masterPositionDetailsEntity = new
			// MasterPositionDetailsEntity();
			currentlist = currentPalletStockDetailsRepositoryInstance
					.findByPalletCode(manualOutfeedMissionDetailsEntity.getPalletCode());
			System.out.println("1. mappingFloorAreaDetailsEntity ::" + mappingFloorAreaDetailsEntity);

			List<ManualOutfeedMissionDetailsEntity> manualOutfeedlist = manualOutfeedMissionDetailsRepositoryInstance
					.findByPalletCodeAndIsMissionGenerated(manualOutfeedMissionDetailsEntity.getPalletCode(), 1);
			// check area or floor outfeed is active

			System.out.println("manualOutfeedlist :: " + manualOutfeedlist.size());
			MasterPositionDetailsEntity masterPositionDetailsEntity = masterPositionDetalisRepositoryInstance
					.findByPositionId(currentlist.get(0).getPositionId());
			System.out.println("position " + currentlist.get(0).getPositionId());
			findByPalletCodeAndIsMissionGenerated = manualOutfeedMissionDetailsRepositoryInstance
					.findByPalletInformationDetailsIdAndIsMissionGenerated(
							manualOutfeedMissionDetailsEntity.getPalletInformationDetailsId(), 1);
			if (!currentlist.isEmpty() && manualOutfeedlist.isEmpty()) {

				if (findByPalletCodeAndIsMissionGenerated == null) {

					mappingFloorAreaDetailsEntity = MappingFloorAreaDetailsRepositoryInstance
							.findByAreaIdAndFloorId(currentlist.get(0).getAreaId(), currentlist.get(0).getFloorId());
					System.out.println("2. mappingFloorAreaDetailsEntity ::" + mappingFloorAreaDetailsEntity);

					if (mappingFloorAreaDetailsEntity.get(0).getOutfeedIsActive() == 1
							&& masterPositionDetailsEntity.getIsManualDispatch() == 0
							&& masterPositionDetailsEntity.getEmptyPalletPosition() == 0) {

						manualOutfeedMissionDetailsEntity
								.setPalletInformationDetailsId(currentlist.get(0).getPalletInformationId());
						manualOutfeedMissionDetailsEntity.setPositionName(currentlist.get(0).getPositionName());
						manualOutfeedMissionDetailsEntity.setPositionId(currentlist.get(0).getPositionId());
//							manualOutfeedMissionDetailsEntity.setUserId(currentlist.get(0).getUserId());
						manualOutfeedMissionDetailsEntity.setUserName(currentlist.get(0).getUserName());
						manualOutfeedMissionDetailsEntity.setLoadDateTime(date);
//						manualOutfeedMissionDetailsEntity.setMfgDate(currentlist.get(0).getMfgDate());
//						manualOutfeedMissionDetailsEntity.setMfgShift(currentlist.get(0).getMfgShift());
						manualOutfeedMissionDetailsEntity.setIsMissionGenerated(0);
						// manualOutfeedMissionDetailsEntity.setStationId(0);

						// MasterPositionDetailsEntity masterPositionDetailsEntity = new
						// MasterPositionDetailsEntity();
						masterPositionDetailsEntity = masterPositionDetalisRepositoryInstance
								.findByPositionId(currentlist.get(0).getPositionId());
						System.out.println("manual dispatch 0");

//						Date dNow = new Date();
//						SimpleDateFormat sdateformat = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
//						String date = sdateformat.format(dNow);

						manualOutfeedMissionDetailsEntity.setCDateTime(date);
						masterPositionDetalisRepositoryInstance.updateisManualDispatchBypositionId(1,
								masterPositionDetailsEntity.getPositionId());
						System.out.println("manual dispatch 1");

						AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
						Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
						String name = authentication.getName();
						System.out.println(" name :: " + name);
						auditTrailDetailsEntity.setOperatorActions("Dispatch given by  " + name + " for position  "
								+ manualOutfeedMissionDetailsEntity.getPositionName() + " having pallet code "
								+ manualOutfeedMissionDetailsEntity.getPalletCode());
						auditTrailDetailsEntity.setField("Dipsatch");
//							auditTrailDetailsEntity.setAfterValue(0);
//							auditTrailDetailsEntity.setBeforeValue(0);
						auditTrailDetailsEntity.setReason("Manual Dispatch");

						auditTrailDetailsEntity.setUsername(name);
						auditTrailDetailsEntity.setDatetimeC(date);
						auditTrailDetailsRepository.save(auditTrailDetailsEntity);

//						return new ResponseEntity<ManualOutfeedMissionDetailsEntity>(
//								manualOutfeedMissionDetailsRepositoryInstance.save(manualOutfeedMissionDetailsEntity),
//								HttpStatus.OK);
//						 return new ResponseEntity<ManualOutfeedMissionDetailsEntity>(manualOutfeedMissionDetailsEntity,HttpStatus.NON_AUTHORITATIVE_INFORMATION);
					} else if (masterPositionDetailsEntity.getIsManualDispatch() == 1
							&& masterPositionDetailsEntity.getEmptyPalletPosition() == 0) {
						return new ResponseEntity<ManualOutfeedMissionDetailsEntity>(
								new ManualOutfeedMissionDetailsEntity(), HttpStatus.ALREADY_REPORTED);
					} else if (masterPositionDetailsEntity.getEmptyPalletPosition() == 1) {
						// Handle the case where the position is empty
						return new ResponseEntity<ManualOutfeedMissionDetailsEntity>(
								new ManualOutfeedMissionDetailsEntity(), HttpStatus.NOT_ACCEPTABLE);
					} else if (mappingFloorAreaDetailsEntity.get(0).getOutfeedIsActive() == 0) {
						// Handle the case where outfeed is not active
						return new ResponseEntity<ManualOutfeedMissionDetailsEntity>(
								new ManualOutfeedMissionDetailsEntity(), HttpStatus.NO_CONTENT); // You can use any
																									// appropriate
																									// status
																									// code here
					} else {
						return new ResponseEntity<ManualOutfeedMissionDetailsEntity>(manualOutfeedMissionDetailsEntity,
								HttpStatus.FORBIDDEN);
					}

				} else {
					findByPalletCodeAndIsMissionGenerated.setIsMissionGenerated(0);
					findByPalletCodeAndIsMissionGenerated
							.setPalletCode(manualOutfeedMissionDetailsEntity.getPalletCode());
					manualOutfeedMissionDetailsRepositoryInstance.save(findByPalletCodeAndIsMissionGenerated);
					return new ResponseEntity<>(new ManualOutfeedMissionDetailsEntity(), HttpStatus.OK);
				}

			} else {
				findByPalletCodeAndIsMissionGenerated.setPalletInformationDetailsId(
						manualOutfeedMissionDetailsEntity.getPalletInformationDetailsId());
				findByPalletCodeAndIsMissionGenerated.setCDateTime(date);
				findByPalletCodeAndIsMissionGenerated.setIsMissionGenerated(0);
				findByPalletCodeAndIsMissionGenerated.setPalletCode(manualOutfeedMissionDetailsEntity.getPalletCode());
				findByPalletCodeAndIsMissionGenerated.setPositionId(manualOutfeedMissionDetailsEntity.getPositionId());
				findByPalletCodeAndIsMissionGenerated
						.setPositionName(manualOutfeedMissionDetailsEntity.getPositionName());
				findByPalletCodeAndIsMissionGenerated
						.setLoadDateTime(manualOutfeedMissionDetailsEntity.getLoadDateTime());
//				findByPalletCodeAndIsMissionGenerated.setMfgDate(manualOutfeedMissionDetailsEntity.getMfgDate());
//				findByPalletCodeAndIsMissionGenerated.setMfgShift(manualOutfeedMissionDetailsEntity.getMfgShift());
				findByPalletCodeAndIsMissionGenerated.setUserId(manualOutfeedMissionDetailsEntity.getUserId());
				findByPalletCodeAndIsMissionGenerated.setUserName(manualOutfeedMissionDetailsEntity.getUserName());
				manualOutfeedMissionDetailsRepositoryInstance.save(findByPalletCodeAndIsMissionGenerated);
				return new ResponseEntity<>(new ManualOutfeedMissionDetailsEntity(), HttpStatus.ALREADY_REPORTED);
			}

			// Check IsMissionGenerated is 0

			// System.out.println("manual dispatch value
			// "+masterPositionDetailsEntity.getIsManualDispatch());
			// System.out.println("position ID value "+currentlist.get(0).getPositionId());
//			if(masterPositionDetailsEntity.getIsManualDispatch()==1 && masterPositionDetailsEntity.getEmptyPalletPosition()==0)
//			{
//			
//			}
//			else {
//				return new ResponseEntity<ManualOutfeedMissionDetailsEntity>(new ManualOutfeedMissionDetailsEntity(),
//						HttpStatus.ALREADY_REPORTED);
//			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<ManualOutfeedMissionDetailsEntity>(
				manualOutfeedMissionDetailsRepositoryInstance.save(manualOutfeedMissionDetailsEntity), HttpStatus.OK);
	}
}