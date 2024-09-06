package com.ats.mahindrabattery.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ats.mahindrabattery.entity.MasterStationTagDetailsEntity;
import com.ats.mahindrabattery.repository.MasterStationTagDetailsRepository;
import com.ats.mahindrabattery.service.MasterStationTagDetailsService;



@Service
public class MasterStationTagDetailsServiceImpl implements MasterStationTagDetailsService {

	@Autowired
	private MasterStationTagDetailsRepository masterStationTagDetailsRepositoryInstance;

	public List<MasterStationTagDetailsEntity> findAllStationTagDetails() {
		try {
			return masterStationTagDetailsRepositoryInstance.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<MasterStationTagDetailsEntity> fetchAllStationDetailsByStationId(int stationId) {
		try {
			return masterStationTagDetailsRepositoryInstance.findByStationId(stationId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public MasterStationTagDetailsEntity updateStationTagDetailsTable(
			MasterStationTagDetailsEntity masterStationTagDetailsEntity, String currentValue) {
		try {
			masterStationTagDetailsRepositoryInstance.findBycurrentValue(currentValue).ifPresent(stationData -> {
				stationData.setCcAknowledgement(1);
				masterStationTagDetailsRepositoryInstance.save(stationData);
			});
			return masterStationTagDetailsEntity;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return masterStationTagDetailsEntity;

	}
}
