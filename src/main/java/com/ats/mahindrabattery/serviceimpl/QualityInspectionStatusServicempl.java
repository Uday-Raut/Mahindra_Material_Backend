package com.ats.mahindrabattery.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.QualityInspectionStatusEntity;
import com.ats.mahindrabattery.repository.QualityInspectionStatusRepository;
import com.ats.mahindrabattery.service.QualityInspectionStatusService;


@Service
public class QualityInspectionStatusServicempl implements QualityInspectionStatusService {
	
	@Autowired
	 private QualityInspectionStatusRepository qualityInspectionStatusRepository;

	public List<QualityInspectionStatusEntity> fetchAllQualityInspectionStatusDetails() {
		try {
			List<QualityInspectionStatusEntity> qualityInspectionStatus = qualityInspectionStatusRepository
					.findAll();
			return qualityInspectionStatus;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
		
	}

}
