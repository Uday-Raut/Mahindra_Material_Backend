package com.ats.mahindrabattery.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.AuditTrailDetailsEntity;
import com.ats.mahindrabattery.entity.MasterAgeingDaysDetailsEntity;
import com.ats.mahindrabattery.repository.AuditTrailDetailsRepository;
import com.ats.mahindrabattery.repository.IMasterAgeingDaysDetailsRepository;
import com.ats.mahindrabattery.response.ResponseHandler;
import com.ats.mahindrabattery.service.MasterAgeingDaysDetailsService;

@Service
public class MasterAgeingDaysDetailsServiceImpl implements MasterAgeingDaysDetailsService{

	@Autowired
	private IMasterAgeingDaysDetailsRepository masterAgeingDaysDetailsRepository;
	
	@Autowired
	private AuditTrailDetailsRepository auditTrailDetailsRepository;
	
	@Override
	public List<MasterAgeingDaysDetailsEntity> findAllAgeingDaysDetais() {
		List<MasterAgeingDaysDetailsEntity> findAll = masterAgeingDaysDetailsRepository.findAll();
		return findAll;
	}
	
	public ResponseEntity<Object> updateMasterAgeingdaysDetails(
			MasterAgeingDaysDetailsEntity masterAgeingDaysDetailsEntity) {
		
		try {
			masterAgeingDaysDetailsRepository.save(masterAgeingDaysDetailsEntity);

			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentDate = ft.format(dNow);
			AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String name = authentication.getName();
			System.out.println(" name :: " + name);
			auditTrailDetailsEntity.setOperatorActions(
					"Updated Ageing days for  " + masterAgeingDaysDetailsEntity.getProductName() + " is "
							+ masterAgeingDaysDetailsEntity.getAgeingDays() + " by " + name);
			auditTrailDetailsEntity.setField("Outfeed activated");
//			auditTrailDetailsEntity.setAfterValue(0);
//			auditTrailDetailsEntity.setBeforeValue(0);
			auditTrailDetailsEntity.setReason("Outfeed activated");

			auditTrailDetailsEntity.setUsername(name);
			auditTrailDetailsEntity.setDatetimeC(currentDate);
			auditTrailDetailsRepository.save(auditTrailDetailsEntity);
			return ResponseHandler.generateResponse("Ageing days entry updated sucessfully", HttpStatus.OK, null);
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Error occurred at server side", HttpStatus.INTERNAL_SERVER_ERROR,
					null);

		}

	}

}
