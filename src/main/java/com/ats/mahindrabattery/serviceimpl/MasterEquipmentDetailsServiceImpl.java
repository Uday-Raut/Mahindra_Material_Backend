package com.ats.mahindrabattery.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ats.mahindrabattery.entity.AuditTrailDetailsEntity;
import com.ats.mahindrabattery.entity.MasterEquipmentDetailsEntity;
import com.ats.mahindrabattery.repository.AuditTrailDetailsRepository;
import com.ats.mahindrabattery.repository.MasterEquipmentDetailsRepository;
import com.ats.mahindrabattery.service.MappingFloorAreaDetailsService;
import com.ats.mahindrabattery.service.MasterEquipmentDetailsService;

@Service
public class MasterEquipmentDetailsServiceImpl implements MasterEquipmentDetailsService {

	@Autowired
	private MasterEquipmentDetailsRepository masterEquipmentDetailsRepositoryInstance;
	
	@Autowired
	private AuditTrailDetailsRepository auditTrailDetailsRepository;

	public List<MasterEquipmentDetailsEntity> findAll() {
		return masterEquipmentDetailsRepositoryInstance.findAll();
	}

	public List<MasterEquipmentDetailsEntity> findEquipmentDetailsByEquipmentIsDeleted(int equipmentIsDeleted) {
		return masterEquipmentDetailsRepositoryInstance.findByEquipmentIsDeleted(0);
	}

	public void updateMasterEquipmentDetails(@RequestBody MasterEquipmentDetailsEntity masterEquipmentDetailsEntity) {

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
		String date = ft.format(dNow);

		masterEquipmentDetailsEntity.setCDateTime(date);
		System.out.println("masterEquipmentDetailsEntity" + masterEquipmentDetailsEntity);

		masterEquipmentDetailsRepositoryInstance.save(masterEquipmentDetailsEntity);

		if (masterEquipmentDetailsEntity.getEquipmentIsActive() == 0) {
			AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String name = authentication.getName();
			System.out.println(" name :: " + name);
			auditTrailDetailsEntity
					.setOperatorActions(masterEquipmentDetailsEntity.getEquipmentName() + " de-activated by " + name);
			auditTrailDetailsEntity.setField("Stacker de activated");
//			auditTrailDetailsEntity.setAfterValue(0);
//			auditTrailDetailsEntity.setBeforeValue(0);
			auditTrailDetailsEntity.setReason("Stacker de activated");

			auditTrailDetailsEntity.setUsername(name);
			auditTrailDetailsEntity.setDatetimeC(date);
			auditTrailDetailsRepository.save(auditTrailDetailsEntity);
		} else {
			AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String name = authentication.getName();
			System.out.println(" name :: " + name);
			auditTrailDetailsEntity
					.setOperatorActions(masterEquipmentDetailsEntity.getEquipmentName() + " activated by " + name);
			auditTrailDetailsEntity.setField("Stacker activated");
//			auditTrailDetailsEntity.setAfterValue(0);
//			auditTrailDetailsEntity.setBeforeValue(0);
			auditTrailDetailsEntity.setReason("Stacker activated");

			auditTrailDetailsEntity.setUsername(name);
			auditTrailDetailsEntity.setDatetimeC(date);
			auditTrailDetailsRepository.save(auditTrailDetailsEntity);
		}

	}

}
