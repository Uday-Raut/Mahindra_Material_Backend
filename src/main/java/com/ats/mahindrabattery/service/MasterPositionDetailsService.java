package com.ats.mahindrabattery.service;

import java.io.IOException;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.ats.mahindrabattery.entity.MasterPositionDetailsEntity;
import com.ats.mahindrabattery.entity.MasterRackPositionDetails;

import javazoom.jl.decoder.JavaLayerException;

public interface MasterPositionDetailsService {

	public List<MasterPositionDetailsEntity> findAll();

	public MasterPositionDetailsEntity findByPositionNameAndPositionIsAllocatedAndEmptyPalletPositionAndPositionIsActive(
			String positionName, int positionIsAllocated, int emptyPalletPosition, int positionIsActive);

	public List<MasterRackPositionDetails> findByAreaIdAndFloorId(int areaId, int floorId);

	public List<MasterPositionDetailsEntity> findByAreaId(int areaId);

	public ResponseEntity<MasterPositionDetailsEntity> updatIsManualDispatchInMasterPositionDetails(int positionId);

	public MasterPositionDetailsEntity updateUnlockSelectedPositionIsActive(
			MasterPositionDetailsEntity masterPositionDetailsEntity, @PathVariable int positionId);

//	public ResponseEntity<Object> updateLockSelectedPositionIsActive(
//			MasterPositionDetailsEntity masterPositionDetailsEntity, int positionId) ;

	public MasterPositionDetailsEntity UpdatePositionIsEmpty(MasterPositionDetailsEntity masterPositionDetailsEntity,
			int positionId);

	public void updatePositionIsAllocated(MasterPositionDetailsEntity masterPositionDetailsEntity, int positionId);

	public List<MasterPositionDetailsEntity> findByPositionName(String positionName);

	public ResponseEntity<Object> findEmptyPositionAlarm();

//	public ResponseEntity<Object> getAlarmAudio();
//	public ResponseEntity<Object> getAlarmAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException, JavaLayerException;

}
