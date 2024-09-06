package com.ats.mahindrabattery.controller;

import java.io.IOException;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.MasterPositionDetailsEntity;
import com.ats.mahindrabattery.entity.MasterRackPositionDetails;
import com.ats.mahindrabattery.serviceimpl.MasterPositionDetailsServiceImpl;

import javazoom.jl.decoder.JavaLayerException;

@CrossOrigin
@RestController
@RequestMapping("/masterPositionDetails")

public class MasterPositionDetailsController {
	@Autowired
	private MasterPositionDetailsServiceImpl masterPositionDetailsServiceInstance;

	@GetMapping("/fetchAllMasterPositionDetails")
	public List<MasterPositionDetailsEntity> fetchAllMasterPositionDetails() {
		return masterPositionDetailsServiceInstance.findAll();
	}

	@GetMapping("/fetchAllMasterPositionDetailsByPositionName/{positionName}")
	public List<MasterPositionDetailsEntity> fetchAllMasterPositionDetailsByPositionName(
			@PathVariable String positionName) {
		return masterPositionDetailsServiceInstance.findByPositionName(positionName);

	}

	@GetMapping("/fetchAllMasterPositionDetailsByPositionNameAndPositionIsAllocatedAndEmptyPalletPositionAndPositionIsActive/{positionName}/{positionIsAllocated}/{emptyPalletPosition}/{positionIsActive}")
	public MasterPositionDetailsEntity fetchAllMasterPositionDetailsByPositionNameAndPositionIsAllocatedAndPositionIsEmptyAndPositionIsActive(
			@PathVariable String positionName, @PathVariable int positionIsAllocated,
			@PathVariable int emptyPalletPosition, @PathVariable int positionIsActive) {
		return masterPositionDetailsServiceInstance
				.findByPositionNameAndPositionIsAllocatedAndEmptyPalletPositionAndPositionIsActive(positionName,
						positionIsAllocated, emptyPalletPosition, positionIsActive);

	}

	@GetMapping("/fetchMasterPositionDetailsByFloorId/{areaId}/{floorId}")
	public List<MasterRackPositionDetails> fetchMasterPositionDetailsByFloorId(@PathVariable int areaId,
			@PathVariable int floorId) {
		long startTime = System.nanoTime();
		List<MasterRackPositionDetails> findByAreaIdAndFloorId = masterPositionDetailsServiceInstance
				.findByAreaIdAndFloorId(areaId, floorId);
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
//		System.out.println("Total Time taken by process is ::  " + totalTime);
		return findByAreaIdAndFloorId;

	}

	@GetMapping("/fetchMasterPositionDetailsByAreaId/{areaId}")
	public List<MasterPositionDetailsEntity> fetchMasterPositionDetailsByAreaId(@PathVariable int areaId) {
		return masterPositionDetailsServiceInstance.findByAreaId(areaId);

	}

	@PutMapping("/updatIsManualDispatchInMasterPositionDetails/{positionId}")
	public ResponseEntity<MasterPositionDetailsEntity> updatIsManualDispatchInMasterPositionDetails(
			@PathVariable int positionId) {

		return masterPositionDetailsServiceInstance.updatIsManualDispatchInMasterPositionDetails(positionId);

	}

	@PutMapping("/unlockSelectedPositionIsActive/{positionId}")
	public MasterPositionDetailsEntity updateUnlockSelectedPositionIsActive(
			@RequestBody MasterPositionDetailsEntity masterPositionDetailsEntity, @PathVariable int positionId) {

		return masterPositionDetailsServiceInstance.updateUnlockSelectedPositionIsActive(masterPositionDetailsEntity,
				positionId);
	}

//	@PutMapping("/lockSelectedPositionIsActive/{positionId}")
//	public ResponseEntity<Object> updateMasterPositionIsActiveDetails(
//			@RequestBody MasterPositionDetailsEntity masterPositionDetailsEntity, @PathVariable int positionId) {
//		return masterPositionDetailsServiceInstance.updateLockSelectedPositionIsActive(masterPositionDetailsEntity,
//				positionId);
//	}

	@PutMapping("/lockSelectedPositionIsActive/{positionId}")
	public MasterPositionDetailsEntity updateMasterPositionIsActiveDetails(
			@RequestBody MasterPositionDetailsEntity masterPositionDetailsEntity, @PathVariable int positionId) {
		return masterPositionDetailsServiceInstance.updateLockSelectedPositionIsActive(masterPositionDetailsEntity,
				positionId);
	}

	// update value in position table for delete data
	@PutMapping("/UpdatePositionIsEmpty/{positionId}")
	public MasterPositionDetailsEntity UpdatePositionIsEmpty(
			@RequestBody MasterPositionDetailsEntity masterPositionDetailsEntity, @PathVariable int positionId) {

		return masterPositionDetailsServiceInstance.UpdatePositionIsEmpty(masterPositionDetailsEntity, positionId);
	}

	@PutMapping("/updatePositionDetailsIsAllocated/{positionId}")
	public void updatePositionIsAllocated(@RequestBody MasterPositionDetailsEntity masterPositionDetailsEntity,
			@PathVariable int positionId) {
		masterPositionDetailsServiceInstance.updatePositionIsAllocated(masterPositionDetailsEntity, positionId);
	}

	@GetMapping("/findEmptyPositionAlarm")
	public ResponseEntity<Object> findEmptyPositionAlarm() {
		return masterPositionDetailsServiceInstance.findEmptyPositionAlarm();
	}

//	@Scheduled(fixedRate = 10000)
//	@GetMapping("/getAlarmAudio")
//	public ResponseEntity<Object> getAlarmAudio()
//			throws UnsupportedAudioFileException, IOException, LineUnavailableException, JavaLayerException {
//		return masterPositionDetailsServiceInstance.getAlarmAudio();
//	}
	


}
