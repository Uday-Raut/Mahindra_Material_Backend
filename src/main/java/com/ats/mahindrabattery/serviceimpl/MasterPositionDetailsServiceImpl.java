package com.ats.mahindrabattery.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ats.mahindrabattery.entity.AuditTrailDetailsEntity;
import com.ats.mahindrabattery.entity.CurrentPalletStockDetailsEntity;
import com.ats.mahindrabattery.entity.MasterPositionDetailsEntity;
import com.ats.mahindrabattery.entity.MasterRackDetailsEntity;
import com.ats.mahindrabattery.entity.MasterRackPositionDetails;
import com.ats.mahindrabattery.repository.AuditTrailDetailsRepository;
import com.ats.mahindrabattery.repository.CurrentPalletStockDetailsRepository;
import com.ats.mahindrabattery.repository.MasterPositionDetailsRepository;
import com.ats.mahindrabattery.repository.MasterRackDetailsRepository;
import com.ats.mahindrabattery.response.ResponseHandler;
import com.ats.mahindrabattery.service.MasterPositionDetailsService;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;



@Service

public class MasterPositionDetailsServiceImpl implements MasterPositionDetailsService {

	@Autowired
	private MasterPositionDetailsRepository masterPositionDetailsRepositoryInstance;

	@Autowired
	private MasterRackDetailsRepository masterRackDetailsRepositoryInstance;

	@Autowired
	private CurrentPalletStockDetailsRepository currentPalletStockDetailsRepository;

	@Autowired
	private AuditTrailDetailsRepository auditTrailDetailsRepository;

	public List<MasterPositionDetailsEntity> findAll() {
		return masterPositionDetailsRepositoryInstance.findAll();
	}
//	public List<MasterPositionDetailsEntity>findByPositionName(String positionName){
//		return masterPositionDetailsRepositoryInstance.findByPositionName(positionName);
//	}

	public MasterPositionDetailsEntity findByPositionNameAndPositionIsAllocatedAndEmptyPalletPositionAndPositionIsActive(
			String positionName, int positionIsAllocated, int emptyPalletPosition, int positionIsActive) {
		try {
			return masterPositionDetailsRepositoryInstance
					.findByPositionNameAndPositionIsAllocatedAndEmptyPalletPositionAndPositionIsActive(positionName,
							positionIsAllocated, emptyPalletPosition, positionIsActive);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public List<MasterRackPositionDetails> findByAreaIdAndFloorId(int areaId, int floorId) {
		// System.out.println("in 123");
		// System.out.println("Area Id: " + areaId + "Floor Id : " + floorId);
		List<MasterRackPositionDetails> list = new ArrayList<MasterRackPositionDetails>();
		List<MasterRackDetailsEntity> rackList = null;
		List<MasterPositionDetailsEntity> positionList = null;
		// List<CurrentPalletStockDetailsEntity>currentPalletList=null;
		try {
			positionList = masterPositionDetailsRepositoryInstance.findByAreaIdAndFloorIdOrderByPositionId(areaId,
					floorId);
			// System.out.println("in positionList ::"+positionList);
			rackList = masterRackDetailsRepositoryInstance.findByAreaIdAndFloorIdOrderByRackId(areaId, floorId);
			// System.out.println("rackList ::"+rackList.size());
			for (int i = 0; i < rackList.size(); i++) {
				MasterRackPositionDetails obj = new MasterRackPositionDetails();
				obj.setRackId(rackList.get(i).getRackId());
				int rackId = rackList.get(i).getRackId();

				List<MasterPositionDetailsEntity> list1 = null;
				list1 = positionList.stream().filter(data -> data.getRackId() == (rackId))
						.sorted(Comparator.comparing(MasterPositionDetailsEntity::getPositionId).reversed())
						.collect(Collectors.toList());
				// System.out.println("in list1 ::"+list1.size());
				for (int p = 0; p < list1.size(); p++) {
					// find by position id
					List<CurrentPalletStockDetailsEntity> currentPalletList = null;
					currentPalletList = currentPalletStockDetailsRepository
							.findByPositionId(list1.get(p).getPositionId());

					// check list size >0

					if (currentPalletList.size() > 0) {
						// check if materialcode!=na, if true
						// System.out.println("in
						// currentpallet::"+currentPalletList.get(0).getProductVariantCode());
						if (!currentPalletList.get(0).getProductVariantCode().equals("NA")) {
							if (currentPalletList.get(0).getProductName().equals("BEV")) {
								list1.get(p).setIsMaterialLoaded(1);
								list1.get(p).setProductName(currentPalletList.get(0).getProductName());
//								System.out.println("list1 productvariant name::" + list1.get(p).getProductName());
							} else if (currentPalletList.get(0).getProductName().equals("S230")) {
								list1.get(p).setIsMaterialLoaded(1);
								list1.get(p).setProductName(currentPalletList.get(0).getProductName());
//								System.out.println("list1 productvariant name::" + list1.get(p).getProductName());
							}

						}

						else {

							// System.out.println("in
							// else::"+currentPalletList.get(0).getProductVariantCode());
							list1.get(p).setIsMaterialLoaded(0);

						}

					}

				}

				obj.setPosition(list1);
				list.add(obj);

			}
			// System.out.println(list);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// System.out.println(list);
		return list;
//		return null;
	}

//
//	public List<MasterRackPositionDetails> findByAreaIdAndFloorId(int areaId, int floorId) {
//		//System.out.println("in 123");
//		//System.out.println("Area Id: " + areaId + "Floor Id : " + floorId);
//		List<MasterRackPositionDetails> list = new ArrayList<MasterRackPositionDetails>();
//		List<MasterRackDetailsEntity> rackList = null;
//		List<MasterPositionDetailsEntity> positionList = null;
//		//List<CurrentPalletStockDetailsEntity>currentPalletList=null;
//		try {
//			positionList = masterPositionDetailsRepositoryInstance.findByAreaIdAndFloorIdOrderByPositionId(areaId, floorId);
//		//System.out.println("in positionList ::"+positionList);
//			rackList = masterRackDetailsRepositoryInstance.findByAreaIdAndFloorIdOrderByRackId(areaId, floorId);
//			//System.out.println("rackList ::"+rackList.size());
//			for (int i = 0; i < rackList.size(); i++) {
//				MasterRackPositionDetails obj = new MasterRackPositionDetails();
//				obj.setRackId(rackList.get(i).getRackId());
//				int rackId = rackList.get(i).getRackId();
//
//				List<MasterPositionDetailsEntity> list1 = null;
//				list1 = positionList.stream().filter(data -> data.getRackId() == (rackId))
//						.sorted(Comparator.comparing(MasterPositionDetailsEntity::getPositionId).reversed())
//						.collect(Collectors.toList());
//				//System.out.println("in list1 ::"+list1.size());
//				for(int p=0;p<list1.size();p++) {
//					//find by position id
//					List<CurrentPalletStockDetailsEntity>currentPalletList=null;
//					currentPalletList=currentPalletStockDetailsRepository.findByPositionId(list1.get(p).getPositionId());
//					
//					// check list size >0
//					
//					if(currentPalletList.size()>0) {
//						//check if materialcode!=na, if true
//						//System.out.println("in currentpallet::"+currentPalletList.get(0).getProductVariantCode());
//						if(!currentPalletList.get(0).getProductVariantCode().equals("NA")) {
//							
//							list1.get(p).setIsMaterialLoaded(1);
//							
//						}
//						
//						else {
//							
//							//System.out.println("in else::"+currentPalletList.get(0).getProductVariantCode());
//							list1.get(p).setIsMaterialLoaded(0);
//							
//						}
//						
//						
//					}
//					
//					
//				}
//				
//
//				obj.setPosition(list1);
//				list.add(obj);
//				
//
//			}
//			//System.out.println(list);
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		//System.out.println(list);
//		return list;
////		return null;
//	}

//	public List<MasterRackPositionDetails> findByAreaIdAndFloorId1(int areaId, int floorId) {
//		
//	}

	public List<MasterPositionDetailsEntity> findByAreaId(int areaId) {
		try {
			return masterPositionDetailsRepositoryInstance.findByAreaId(areaId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public ResponseEntity<MasterPositionDetailsEntity> updatIsManualDispatchInMasterPositionDetails(int positionId) {
		MasterPositionDetailsEntity masterPositionDetailsEntity1 = new MasterPositionDetailsEntity();
		try {
			masterPositionDetailsEntity1 = masterPositionDetailsRepositoryInstance.findByPositionId(positionId);

			if (masterPositionDetailsEntity1 != null) {
				masterPositionDetailsEntity1.setIsManualDispatch(1);
				return new ResponseEntity<MasterPositionDetailsEntity>(
						masterPositionDetailsRepositoryInstance.save(masterPositionDetailsEntity1), HttpStatus.OK);
			} else {
				return new ResponseEntity<MasterPositionDetailsEntity>(new MasterPositionDetailsEntity(),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public MasterPositionDetailsEntity updateUnlockSelectedPositionIsActive(
			MasterPositionDetailsEntity masterPositionDetailsEntity, @PathVariable int positionId) {
//		try {
//			Date dNow = new Date();
//			SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
//			String date = ft.format(dNow);
//			masterPositionDetailsEntity.setCDateTime(date);
//			masterPositionDetailsRepositoryInstance.findById(positionId).ifPresent(positionData -> {
//				positionData.setPositionIsActive(1);
//				// System.out.println("positionData" + positionData.toString());
//				MasterPositionDetailsEntity save1 = masterPositionDetailsRepositoryInstance.save(positionData);
//			});
//			return masterPositionDetailsEntity;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return masterPositionDetailsEntity;

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
		String date = ft.format(dNow);

		MasterPositionDetailsEntity findByPositionId2 = masterPositionDetailsRepositoryInstance
				.findByPositionId(positionId);
		if (positionId % 2 == 0) {
			MasterPositionDetailsEntity findByPositionId = masterPositionDetailsRepositoryInstance
					.findByPositionId(positionId - 1);
			findByPositionId.setCDateTime(date);
			findByPositionId2.setCDateTime(date);
			findByPositionId.setPositionIsActive(1);
			findByPositionId2.setPositionIsActive(1);
			masterPositionDetailsRepositoryInstance.save(findByPositionId);
			masterPositionDetailsRepositoryInstance.save(findByPositionId2);
		} else {

			findByPositionId2.setPositionIsActive(1);
			findByPositionId2.setCDateTime(date);
			masterPositionDetailsRepositoryInstance.save(findByPositionId2);
		}

		return masterPositionDetailsEntity;
	}

//	public ResponseEntity<Object> updateLockSelectedPositionIsActive(
//			MasterPositionDetailsEntity masterPositionDetailsEntity, int positionId) {
//		try {
//			Date dNow = new Date();
//			SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
//			String date = ft.format(dNow);
//			masterPositionDetailsEntity.setCDateTime(date);
//
//			MasterPositionDetailsEntity masterPositionDetailsEntity2 = masterPositionDetailsRepositoryInstance
//					.findById(positionId).get();
//
//			if (positionId % 2 == 1) {
//				MasterPositionDetailsEntity findByPositionId = masterPositionDetailsRepositoryInstance
//						.findByPositionId(positionId+1);
//				int emptyPalletPosition = findByPositionId.getEmptyPalletPosition();
//				int isMaterialLoaded = findByPositionId.getIsMaterialLoaded();
//
//				if (emptyPalletPosition != 1 && isMaterialLoaded != 0) {
//					masterPositionDetailsEntity2.setPositionIsActive(0);
//					positionId = positionId + 1;
//					MasterPositionDetailsEntity masterPositionDetailsEntity3 = masterPositionDetailsRepositoryInstance
//							.findById(positionId).get();
//					masterPositionDetailsEntity3.setPositionIsActive(0);
//					masterPositionDetailsEntity3.setCDateTime(date);
//					masterPositionDetailsEntity2.setCDateTime(date);
//					masterPositionDetailsRepositoryInstance.save(masterPositionDetailsEntity2);
//					masterPositionDetailsRepositoryInstance.save(masterPositionDetailsEntity3);
//
//				} else {
//					return ResponseHandler.generateResponse("Unable to lock position", HttpStatus.ALREADY_REPORTED,
//							null);
//				}
//
//			} else {
//				masterPositionDetailsEntity2.setPositionIsActive(0);
//				masterPositionDetailsEntity2.setCDateTime(date);
//				masterPositionDetailsRepositoryInstance.save(masterPositionDetailsEntity2);
//				return ResponseHandler.generateResponse("Position locked successfully", HttpStatus.OK, null);
//
//			}
////			return masterPositionDetailsEntity;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return ResponseHandler.generateResponse("Position locked successfully", HttpStatus.OK, null);
////		return masterPositionDetailsEntity;
//	}

	public MasterPositionDetailsEntity updateLockSelectedPositionIsActive(
			MasterPositionDetailsEntity masterPositionDetailsEntity, int positionId) {
		try {
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
			String date = ft.format(dNow);
			masterPositionDetailsEntity.setCDateTime(date);

			MasterPositionDetailsEntity masterPositionDetailsEntity2 = masterPositionDetailsRepositoryInstance
					.findById(positionId).get();

			if (positionId % 2 == 1) {
				masterPositionDetailsEntity2.setPositionIsActive(0);
				positionId = positionId + 1;
				MasterPositionDetailsEntity masterPositionDetailsEntity3 = masterPositionDetailsRepositoryInstance
						.findById(positionId).get();
				masterPositionDetailsEntity3.setPositionIsActive(0);
				masterPositionDetailsRepositoryInstance.save(masterPositionDetailsEntity2);
				masterPositionDetailsRepositoryInstance.save(masterPositionDetailsEntity3);
			} else {
				masterPositionDetailsEntity2.setPositionIsActive(0);
				masterPositionDetailsRepositoryInstance.save(masterPositionDetailsEntity2);
			}
			return masterPositionDetailsEntity;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return masterPositionDetailsEntity;
	}

	public MasterPositionDetailsEntity UpdatePositionIsEmpty(MasterPositionDetailsEntity masterPositionDetailsEntity,
			int positionId) {

		try {
			masterPositionDetailsRepositoryInstance.findById(positionId).ifPresent(positionData -> {
				positionData.setPositionIsAllocated(0);
				positionData.setEmptyPalletPosition(1);
				masterPositionDetailsRepositoryInstance.save(positionData);
			});
			System.out.println("positionId");
			return masterPositionDetailsEntity;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return masterPositionDetailsEntity;

	}

	public void updatePositionIsAllocated(MasterPositionDetailsEntity masterPositionDetailsEntity, int positionId) {
		masterPositionDetailsRepositoryInstance.findById(positionId).ifPresent(positionDetails -> {
			positionDetails.setPositionIsAllocated(0);
			positionDetails.setEmptyPalletPosition(1);
			positionDetails.setIsManualDispatch(0);
			masterPositionDetailsRepositoryInstance.save(positionDetails);

		});
//		Date dNow = new Date();
//		SimpleDateFormat sdateformat = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
//		String date = sdateformat.format(dNow);
//		
//		CurrentPalletStockDetailsEntity currentStockDetailsEntityInsrt = new CurrentPalletStockDetailsEntity();
//		
//		currentStockDetailsEntityInsrt = currentPalletStockDetailsRepository.getByPositionId(positionId);
//		if (currentStockDetailsEntityInsrt != null) {
//			currentStockDetailsEntityInsrt.setPalletCode("NA");
//			currentStockDetailsEntityInsrt.setProductName("NA");
//			currentStockDetailsEntityInsrt.setPalletInformationId(0);
//			currentStockDetailsEntityInsrt.setProductVariantCode("NA");
//			currentStockDetailsEntityInsrt.setProductId(0);
//			currentStockDetailsEntityInsrt.setProductVariantId(0);
//			currentStockDetailsEntityInsrt.setProductVariantName("NA");
//			currentStockDetailsEntityInsrt.setPalletStatusId(3);
//			currentStockDetailsEntityInsrt.setSerialNumber(0);
//			currentStockDetailsEntityInsrt.setPalletStatusname("NA");
//			currentStockDetailsEntityInsrt.setQualityStatus("NA");
//			currentStockDetailsEntityInsrt.setAgeingDays(0);
//			currentStockDetailsEntityInsrt.setBatchNumber("NA");
//			currentStockDetailsEntityInsrt.setModelNumber("NA");
//			currentStockDetailsEntityInsrt.setLocation("NA");
//
//			currentStockDetailsEntityInsrt.setIsInfeedMissionGenerated(0);
//			currentStockDetailsEntityInsrt.setIsOutfeedMissionGenerated(0);
//			currentStockDetailsEntityInsrt.setQuantity(0);
//
//			currentStockDetailsEntityInsrt.setProductId(0);
//
//
//			currentStockDetailsEntityInsrt.setLoadDatetime(date);
//			currentPalletStockDetailsRepository.save(currentStockDetailsEntityInsrt);
//		}

		Date dNow = new Date();
		SimpleDateFormat sdateformat = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
		String date = sdateformat.format(dNow);

		MasterPositionDetailsEntity masterPositionDetailsEntity2 = masterPositionDetailsRepositoryInstance
				.findById(positionId).get();

		AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		System.out.println(" name :: " + name);
		auditTrailDetailsEntity.setOperatorActions("Position free allocated by  " + name + " for position  "
				+ masterPositionDetailsEntity2.getPositionName());
		auditTrailDetailsEntity.setField("Free allocation");

		auditTrailDetailsEntity.setReason("Free allocation");

		auditTrailDetailsEntity.setUsername(name);
		auditTrailDetailsEntity.setDatetimeC(date);
		auditTrailDetailsRepository.save(auditTrailDetailsEntity);

	}

	public List<MasterPositionDetailsEntity> findByPositionName(String positionName) {
		// TODO Auto-generated method stub
		List<MasterPositionDetailsEntity> data = masterPositionDetailsRepositoryInstance
				.findByPositionName(positionName);
		;
		return data;
//		return null;
	}

//	@Override
//	public ResponseEntity<Object> findEmptyPositionAlarm() {
//		int findByEmptyPalletPosition = masterPositionDetailsRepositoryInstance.findByEmptyPalletPosition();
//		if (findByEmptyPalletPosition == 0) {
//			return ResponseHandler.generateResponse("ASRS capacity has reached its maximum threshold. Please initiate an outfeed operation of first depth position of any available rack to clear space. ", HttpStatus.OK,
//					findByEmptyPalletPosition);
//		} else {
//			return ResponseHandler.generateResponse("ASRS is empty ", HttpStatus.ALREADY_REPORTED,
//					findByEmptyPalletPosition);
//		}
//
//	}

	@Override
	public ResponseEntity<Object> findEmptyPositionAlarm() {
		List<MasterPositionDetailsEntity> emptyPalletPositions = masterPositionDetailsRepositoryInstance
				.findByEmptyPalletPosition();

		if (emptyPalletPositions.isEmpty()) {
			return ResponseHandler.generateResponse(
					"ASRS capacity has reached its maximum threshold. Please initiate an outfeed operation of first depth position of any available rack to clear space.",
					HttpStatus.OK, null);
		} else {
			return ResponseHandler.generateResponse("ASRS is empty", HttpStatus.ALREADY_REPORTED,
					emptyPalletPositions.size());
		}
	}

//	@Override
//	public ResponseEntity<Object> getAlarmAudio()
//			throws UnsupportedAudioFileException, IOException, LineUnavailableException, JavaLayerException {
//		
//		List<MasterPositionDetailsEntity> findByIsAlarmRack = masterPositionDetailsRepositoryInstance
//				.findByIsAlarmRack(1);
//	
//		List<String> list = new ArrayList<>();
//		try {
//		for (MasterPositionDetailsEntity masterPositionDetailsEntity : findByIsAlarmRack) {
//			list.add(masterPositionDetailsEntity.getPositionName());
//		}
//		if (!findByIsAlarmRack.isEmpty()) {
//			 String filePath = "D://Electric//alarm.mp3";
////			String filePath = "C://Users//shubhangij//Documents//GitHub//Mahindra_Battery_UI//src//assets//audio//alarm.mp3";
//			FileInputStream in = new FileInputStream(filePath);
//			AdvancedPlayer player = new AdvancedPlayer(in);
//			player.play();
//			AudioInputStream aui = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
//			Clip clip = AudioSystem.getClip();
//			clip.open(aui);
//			clip.loop(Clip.LOOP_CONTINUOUSLY);
//			clip.start();
//			if (findByIsAlarmRack.isEmpty()) {
//				clip.stop();
//			}
//			// clip.loop(10);
//			// clip.stop();
//			clip.close();
//
//			System.out.println("alarm generated");
//			
//		}
//		else {
//			return ResponseHandler.generateResponse("Alarm generated at positions " + list, HttpStatus.ALREADY_REPORTED,
//					null);
//		}
//		}catch (Exception e) {
//			e.printStackTrace();
//			return ResponseHandler.generateResponse("Alarm generated at positions " + list, HttpStatus.OK,
//					null);
//		}
//		return ResponseHandler.generateResponse("Alarm generated at positions " + list, HttpStatus.OK,
//				findByIsAlarmRack);
//	}
	
	


}
