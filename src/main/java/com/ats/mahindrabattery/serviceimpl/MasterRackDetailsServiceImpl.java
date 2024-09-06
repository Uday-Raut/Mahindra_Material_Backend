package com.ats.mahindrabattery.serviceimpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.MasterRackDetailsEntity;
import com.ats.mahindrabattery.repository.MasterRackDetailsRepository;
import com.ats.mahindrabattery.service.MasterRackDetailsService;



@Service
public class MasterRackDetailsServiceImpl implements MasterRackDetailsService {
	@Autowired
	private MasterRackDetailsRepository masterRackDetailsRepositoryRepositoryInstance;

	public List<MasterRackDetailsEntity> getRackDetailsByAreaIdAndFloorId(int areaId, int floorId) {
		try {
			return masterRackDetailsRepositoryRepositoryInstance.findByAreaIdAndFloorIdOrderByRackId(areaId, floorId);
		} catch (Exception ex) {
			System.out.println("Exception occurred in MasterRackDetailsService :: " + ex.getStackTrace());
		}
		return null;
	}

}
