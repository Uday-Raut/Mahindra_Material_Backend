package com.ats.mahindrabattery.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.MasterSensorDetailsEntity;
import com.ats.mahindrabattery.entity.StackerSensorDetailsEntity;
import com.ats.mahindrabattery.repository.StackerSensorDetailsRepository;
import com.ats.mahindrabattery.service.StackerSensorDetailsService;

@Service
public class StackerSensorDetailsServiceImpl implements StackerSensorDetailsService {
	
	
	@Autowired
	private StackerSensorDetailsRepository sensorDetailsRepository;

	public List<StackerSensorDetailsEntity> fetchAllStackerSensorDetails(int stackerSensorIsDeleted){
		return sensorDetailsRepository.findByStackerSensorIsDeleted(0);
	}
	
	
	
	public List<StackerSensorDetailsEntity> findByStackerId(int stackerId) {
		return sensorDetailsRepository.findByStackerId(stackerId);
	}
}
