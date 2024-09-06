package com.ats.mahindrabattery.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.MasterRoleDetailsEntity;
import com.ats.mahindrabattery.repository.MasterRoleDetailsRepository;
import com.ats.mahindrabattery.service.MasterRoleDetailsService;



@Service
public class MasterRoleDetailsServiceImpl implements MasterRoleDetailsService{

	@Autowired
	private MasterRoleDetailsRepository masterRoleDetailsRepository;
	
	public List<MasterRoleDetailsEntity> fetchAllMasterRoleDetails(){
		List<MasterRoleDetailsEntity> findAllMasterRoleDetails = masterRoleDetailsRepository.findAll();
		return findAllMasterRoleDetails;
	}
}
