package com.ats.mahindrabattery.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.StationDetailsEntity;
import com.ats.mahindrabattery.repository.StationDetailsRepository;
import com.ats.mahindrabattery.service.StationDetailsService;



@Service
public class StationDetailsServicempl implements StationDetailsService {

	@Autowired
	StationDetailsRepository stationDetailsRepositoryInstance;

	public List<StationDetailsEntity> findAllStationDetailsByWmsStationId(int wmsStationId) {
		try {
			return stationDetailsRepositoryInstance.findByWmsStationId(wmsStationId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}
}	
