package com.ats.mahindrabattery.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.MasterShiftDetailsEntity;
import com.ats.mahindrabattery.repository.MasterShiftDetailsRepository;
import com.ats.mahindrabattery.service.MasterShiftDetailsService;
@Service
public class MasterShiftDetailsServiceImpl implements MasterShiftDetailsService {
	@Autowired
	private MasterShiftDetailsRepository masterShiftDetailsRepository;

//	public List<MasterShiftDetailsEntity> fetchAllMasterShiftDetails() {
//		try {
//			List<MasterShiftDetailsEntity> findAll = masterShiftDetailsRepository.findAll();
//			return findAll;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return null;
//	}
	
	public List<MasterShiftDetailsEntity> fetchAllMasterShiftDetails() {
	    try {
	        List<MasterShiftDetailsEntity> findAll = masterShiftDetailsRepository.findAll();
	        
	        // Filter out records where shiftIsDeleted is 1
	        List<MasterShiftDetailsEntity> filteredList = findAll.stream()
	            .filter(entity -> entity.getShiftIsDeleted() != 1)
	            .collect(Collectors.toList());

	        return filteredList;
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}

	

}
