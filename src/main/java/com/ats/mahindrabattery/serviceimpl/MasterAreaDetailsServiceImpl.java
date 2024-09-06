package com.ats.mahindrabattery.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.MasterAreaDetailsEntity;
import com.ats.mahindrabattery.repository.MasterAreaDetailsRepository;
import com.ats.mahindrabattery.service.MasterAreaDetailsService;



@Service
public class MasterAreaDetailsServiceImpl implements MasterAreaDetailsService {

	@Autowired
	private MasterAreaDetailsRepository masterAreaDetailsRepositoryInstance;

	public List<MasterAreaDetailsEntity> findAll() {
		return masterAreaDetailsRepositoryInstance.findByAreaIsDeleted(0);
//		 masterAreaDetailsRepositoryInstance.findAll();
	}

	public List<MasterAreaDetailsEntity> findByAreaId(int areaId) {
		try {
			return masterAreaDetailsRepositoryInstance.findByAreaId(areaId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
