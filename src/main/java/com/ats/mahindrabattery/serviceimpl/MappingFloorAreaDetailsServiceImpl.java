package com.ats.mahindrabattery.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ats.mahindrabattery.entity.AuditTrailDetailsEntity;
import com.ats.mahindrabattery.entity.MappingFloorAreaDetailsEntity;
import com.ats.mahindrabattery.repository.AuditTrailDetailsRepository;
import com.ats.mahindrabattery.repository.MappingFloorAreaDetailsRepository;
import com.ats.mahindrabattery.service.MappingFloorAreaDetailsService;

@Service
public class MappingFloorAreaDetailsServiceImpl implements MappingFloorAreaDetailsService {

	@Autowired
	MappingFloorAreaDetailsRepository mappingFloorAreaDetailsRepositoryInstance;

	@Autowired
	private AuditTrailDetailsRepository auditTrailDetailsRepository;

	public List<MappingFloorAreaDetailsEntity> findAll() {
		return mappingFloorAreaDetailsRepositoryInstance.findAll();
	}

	public List<MappingFloorAreaDetailsEntity> findByAreaId(int areaId) {
		try {
			return mappingFloorAreaDetailsRepositoryInstance.findByAreaId(areaId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void updateMappingFloorAreaDetails(
			@RequestBody MappingFloorAreaDetailsEntity mappingMappingFloorAreaDetailsEntity) {
		mappingFloorAreaDetailsRepositoryInstance.save(mappingMappingFloorAreaDetailsEntity);

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
		String date = ft.format(dNow);

		if (mappingMappingFloorAreaDetailsEntity.getInfeedIsActive() == 0) {
			AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String name = authentication.getName();
			System.out.println(" name :: " + name);
			auditTrailDetailsEntity.setOperatorActions(
					"Infeed de-activated for " + mappingMappingFloorAreaDetailsEntity.getFloorName()
							+ " and " + mappingMappingFloorAreaDetailsEntity.getAreaName() + " by " + name);
			auditTrailDetailsEntity.setField("Infeed de activated");
//			auditTrailDetailsEntity.setAfterValue(0);
//			auditTrailDetailsEntity.setBeforeValue(0);
			auditTrailDetailsEntity.setReason("Infeed de activated");

			auditTrailDetailsEntity.setUsername(name);
			auditTrailDetailsEntity.setDatetimeC(date);
			auditTrailDetailsRepository.save(auditTrailDetailsEntity);
		} else {
			AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String name = authentication.getName();
			System.out.println(" name :: " + name);
			auditTrailDetailsEntity.setOperatorActions(
					"Infeed activated for " + mappingMappingFloorAreaDetailsEntity.getFloorName() + " and "
							+ mappingMappingFloorAreaDetailsEntity.getAreaName() + " by " + name);
			auditTrailDetailsEntity.setField("Infeed activated");
//			auditTrailDetailsEntity.setAfterValue(0);
//			auditTrailDetailsEntity.setBeforeValue(0);
			auditTrailDetailsEntity.setReason("Infeed activated");

			auditTrailDetailsEntity.setUsername(name);
			auditTrailDetailsEntity.setDatetimeC(date);
			auditTrailDetailsRepository.save(auditTrailDetailsEntity);
		}
		
		if (mappingMappingFloorAreaDetailsEntity.getOutfeedIsActive() == 0) {
			AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String name = authentication.getName();
			System.out.println(" name :: " + name);
			auditTrailDetailsEntity.setOperatorActions(
					"Outfeed de-activated for " + mappingMappingFloorAreaDetailsEntity.getFloorName()
							+ " and " + mappingMappingFloorAreaDetailsEntity.getAreaName() + " by " + name);
			auditTrailDetailsEntity.setField("Outfeed de activated");
//			auditTrailDetailsEntity.setAfterValue(0);
//			auditTrailDetailsEntity.setBeforeValue(0);
			auditTrailDetailsEntity.setReason("Outfeed de activated");

			auditTrailDetailsEntity.setUsername(name);
			auditTrailDetailsEntity.setDatetimeC(date);
			auditTrailDetailsRepository.save(auditTrailDetailsEntity);
		} else {
			AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String name = authentication.getName();
			System.out.println(" name :: " + name);
			auditTrailDetailsEntity.setOperatorActions(
					"Outfeed activated for " + mappingMappingFloorAreaDetailsEntity.getFloorName() + " and "
							+ mappingMappingFloorAreaDetailsEntity.getAreaName() + " by " + name);
			auditTrailDetailsEntity.setField("Outfeed activated");
//			auditTrailDetailsEntity.setAfterValue(0);
//			auditTrailDetailsEntity.setBeforeValue(0);
			auditTrailDetailsEntity.setReason("Outfeed activated");

			auditTrailDetailsEntity.setUsername(name);
			auditTrailDetailsEntity.setDatetimeC(date);
			auditTrailDetailsRepository.save(auditTrailDetailsEntity);
		}
	}

	public void updateMappingFloorAreaDetailsByareaId(int infeedIsActive, int outfeedIsActive, int areaId) {
		mappingFloorAreaDetailsRepositoryInstance.updateInfeedIsActiveAndOutfeedIsActiveByareaId(infeedIsActive,
				outfeedIsActive, areaId);

	}

	public List<MappingFloorAreaDetailsEntity> fetchMappingFloorAreaDetailsByAreaIdAndFloorName(int areaId,
			String floorName) {
		return mappingFloorAreaDetailsRepositoryInstance.findByAreaIdAndFloorName(areaId, floorName);
	}

}
