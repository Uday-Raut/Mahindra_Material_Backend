package com.ats.mahindrabattery.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.AuditTrailDetailsEntity;
import com.ats.mahindrabattery.entity.MasterReasonDetailsEntity;
import com.ats.mahindrabattery.exception.ResourceNotFoundException;
import com.ats.mahindrabattery.repository.AuditTrailDetailsRepository;
import com.ats.mahindrabattery.repository.MasterReasonDetailsRepository;
import com.ats.mahindrabattery.response.ResponseHandler;
import com.ats.mahindrabattery.service.MasterReasonDetailsService;

@Service
//@CacheConfig(cacheNames = { "allMasterReasonDetailsCache", "masterReasonDetailsCache" })
public class MasterReasonDetailsServiceImpl implements MasterReasonDetailsService {

	@Autowired
	private MasterReasonDetailsRepository masterReasonDetailsRepository;

	@Autowired
	private AuditTrailDetailsRepository auditTrailDetailsRepository;

	public ResponseEntity<Object> addMasterReasonDetails(MasterReasonDetailsEntity masterReasonDetailsEntity) {
		try {

			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
			String date = ft.format(dNow);
			// System.out.println("IN add part number");
			masterReasonDetailsEntity.setCdatetime(date);
			masterReasonDetailsRepository.save(masterReasonDetailsEntity);

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String name = authentication.getName();
			System.out.println("authentication:" + name);

			AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
			auditTrailDetailsEntity.setOperatorActions("Reason added by " + name + " and operator action is "
					+ masterReasonDetailsEntity.getOperatorAction());
			auditTrailDetailsEntity.setField("Reason added");
			auditTrailDetailsEntity.setAfterValue(0);
			auditTrailDetailsEntity.setBeforeValue(0);
			auditTrailDetailsEntity.setReason("Reason added");
//			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//			String name = authentication.getName();
			System.out.println(" name :: " + name);
			auditTrailDetailsEntity.setUsername(name);
			auditTrailDetailsEntity.setDatetimeC(date);
			auditTrailDetailsRepository.save(auditTrailDetailsEntity);

			return ResponseHandler.generateResponse("Reason added sucessfully", HttpStatus.OK, null);
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Error occurred at server side", HttpStatus.INTERNAL_SERVER_ERROR,
					null);
		}

	}

//	@Cacheable(value = "allMasterReasonDetailsCache")
	public List<MasterReasonDetailsEntity> fetchAllMasterReasonDetails(
			MasterReasonDetailsEntity masterReasonDetailsEntity) {
		List<MasterReasonDetailsEntity> findByReasonIsDeleted = masterReasonDetailsRepository.findByReasonIsDeleted(0);
		// List<MasterReasonDetailsEntity> allMasterReasonDetails =
		// masterReasonDetailsRepository.findAll();
		System.out.println("called by findAllMasterReasonDetails() from DB");
		return findByReasonIsDeleted;
	}

//	@Cacheable(key = "#id")
	public MasterReasonDetailsEntity findMasterReasonDetailsById(int id) {
		MasterReasonDetailsEntity masterReasonDetails = masterReasonDetailsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("MasterReasonDetailsEntity", "Id", id));
		System.out.println("called by findAllMasterReasonDetails() from DB");
		return masterReasonDetails;
	}

	public ResponseEntity<Object> updateMasterReasonDetails(int id,
			MasterReasonDetailsEntity masterReasonDetailsEntity) {
		try {

			MasterReasonDetailsEntity save = masterReasonDetailsRepository.save(masterReasonDetailsEntity);

			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
			String date = ft.format(dNow);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String name = authentication.getName();
			System.out.println("authentication:" + name);

			AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
			auditTrailDetailsEntity.setOperatorActions(
					" Operator action " + save.getOperatorAction() + " and reason " + save.getReason() + " updated by " + name);
			auditTrailDetailsEntity.setField("Operator action and Reason updated");
			auditTrailDetailsEntity.setAfterValue(0);
			auditTrailDetailsEntity.setBeforeValue(0);
			auditTrailDetailsEntity.setReason("Operator action and Reason updated");
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String name = authentication.getName();
			System.out.println(" name :: " + name);
			auditTrailDetailsEntity.setUsername(name);
			auditTrailDetailsEntity.setDatetimeC(date);
			auditTrailDetailsRepository.save(auditTrailDetailsEntity);
			return ResponseHandler.generateResponse("Reason updated sucessfully", HttpStatus.OK, null);
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Error occurred at server side", HttpStatus.INTERNAL_SERVER_ERROR,
					null);
		}

	}

	public ResponseEntity<Object> deleteMasterReasonDetails(int id) {
		try {
			List<MasterReasonDetailsEntity> masterReasonDetails = masterReasonDetailsRepository.findByReasonId(id);
			if (masterReasonDetails.size() > 0) {
				masterReasonDetails.get(0).setReasonIsDeleted(1);
				MasterReasonDetailsEntity save = masterReasonDetailsRepository.save(masterReasonDetails.get(0));
				
				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
				String date = ft.format(dNow);
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				String name = authentication.getName();
				AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
				auditTrailDetailsEntity.setOperatorActions(
						"Reason " + save.getReason() + " deleted by " + name);
				auditTrailDetailsEntity.setField("Reason deleted");
				auditTrailDetailsEntity.setAfterValue(0);
				auditTrailDetailsEntity.setBeforeValue(0);
				auditTrailDetailsEntity.setReason("Reason deleted");

				System.out.println(" name :: " + name);
				auditTrailDetailsEntity.setUsername(name);
				auditTrailDetailsEntity.setDatetimeC(date);
				auditTrailDetailsRepository.save(auditTrailDetailsEntity);
				
				return ResponseHandler.generateResponse("Reason deleted sucessfully", HttpStatus.OK, null);
			} else {
				return ResponseHandler.generateResponse("Reason already deleted", HttpStatus.ALREADY_REPORTED, null);
			}
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Error occurred at server side", HttpStatus.INTERNAL_SERVER_ERROR,
					null);

		}

	}

}
