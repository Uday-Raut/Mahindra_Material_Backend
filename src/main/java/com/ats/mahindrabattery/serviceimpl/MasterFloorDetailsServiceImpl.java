package com.ats.mahindrabattery.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.MasterFloorDetailsEntity;
import com.ats.mahindrabattery.repository.MasterFloorDetailsRepository;
import com.ats.mahindrabattery.service.MasterFloorDetailsService;


@Service
public class MasterFloorDetailsServiceImpl implements MasterFloorDetailsService {

	@Autowired
	private MasterFloorDetailsRepository masterFloorDetailsRepository;

	public List<MasterFloorDetailsEntity> fetchAllMasterFloorDetails() {
		try {
			List<MasterFloorDetailsEntity> findAll = masterFloorDetailsRepository.findAll();
			return findAll;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
