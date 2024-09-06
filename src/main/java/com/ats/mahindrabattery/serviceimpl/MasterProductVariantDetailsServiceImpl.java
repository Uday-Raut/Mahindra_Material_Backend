package com.ats.mahindrabattery.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.AuditTrailDetailsEntity;
import com.ats.mahindrabattery.entity.MasterProductVariantDetailsEntity;
import com.ats.mahindrabattery.exception.ResourceNotFoundException;
import com.ats.mahindrabattery.repository.AuditTrailDetailsRepository;
import com.ats.mahindrabattery.repository.MasterProductVariantDetailsRepository;
import com.ats.mahindrabattery.response.ResponseHandler;
import com.ats.mahindrabattery.service.MasterProductVariantDetailsService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
//@CacheConfig(cacheNames = {"allMasterProductVariantDetailsCache","masterProductVariantDetailsCache"})
public class MasterProductVariantDetailsServiceImpl implements MasterProductVariantDetailsService {

	@Autowired
	private MasterProductVariantDetailsRepository masterProductVariantDetailsRepository;
	
	@Autowired
	private AuditTrailDetailsRepository auditTrailDetailsRepository;

	public ResponseEntity<Object> createmasterMasterProductVariantDetails(
			MasterProductVariantDetailsEntity masterProductVariantDetailsEntity) {
		try {
			List<MasterProductVariantDetailsEntity> list = masterProductVariantDetailsRepository
					.findByProductVariantCode(masterProductVariantDetailsEntity.getProductVariantCode());

			if (list.size() == 0) {
				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
				String date = ft.format(dNow);
				masterProductVariantDetailsEntity.setCdatetime(date);

				AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				String name = authentication.getName();
				System.out.println(" name :: " + name);
				auditTrailDetailsEntity.setOperatorActions(
						"Part number " + masterProductVariantDetailsEntity.getProductVariantCode() + " added " +
								 " by " + name);
				auditTrailDetailsEntity.setField("Part added");
//				auditTrailDetailsEntity.setAfterValue(0);
//				auditTrailDetailsEntity.setBeforeValue(0);
				auditTrailDetailsEntity.setReason("Part added");

				auditTrailDetailsEntity.setUsername(name);
				auditTrailDetailsEntity.setDatetimeC(date);
				auditTrailDetailsRepository.save(auditTrailDetailsEntity);
				
				masterProductVariantDetailsRepository.save(masterProductVariantDetailsEntity);
				return ResponseHandler.generateResponse("Material entry sucessfully added", HttpStatus.OK, null);
			} else {
				return ResponseHandler.generateResponse("Material entry already exists", HttpStatus.ALREADY_REPORTED,
						null);
			}
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Error occurred at server side", HttpStatus.INTERNAL_SERVER_ERROR,
					null);

		}

	}
	
	
	
	public List<MasterProductVariantDetailsEntity> getAllMasterProductVariantDetails() {
		try {
			List<MasterProductVariantDetailsEntity> findByproductVariantIsDeleted = masterProductVariantDetailsRepository
					.findByproductVariantIsDeleted(0);
//			System.out.println("called by getAllMasterProductVariantDetails() from DB");
			return findByproductVariantIsDeleted;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

//	@Override
////	@Cacheable(value="allMasterProductVariantDetailsCache")
//	public Page<MasterProductVariantDetailsEntity> getAllMasterProductVariantDetails(Pageable pageable) {
//		try {
//			Page<MasterProductVariantDetailsEntity> findByproductVariantIsDeleted = masterProductVariantDetailsRepository
//					.findByproductVariantIsDeleted(pageable, 0);
////			System.out.println("called by getAllMasterProductVariantDetails() from DB");
//			return findByproductVariantIsDeleted;
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//		return null;
//	}

//	 @Cacheable(key = "#id")
	public MasterProductVariantDetailsEntity findMasterProductVariantDetailsById(int id) {
		MasterProductVariantDetailsEntity masterProductVariantDetails = masterProductVariantDetailsRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("MasterProductVariantDetailsEntity", "Id", id));
//		System.out.println("called by findAllMasterUserDetails() from DB");
		return masterProductVariantDetails;
	}

	public ResponseEntity<Object> updateMasterProductVariantDetails(
			MasterProductVariantDetailsEntity masterProductVariantDetailsEntity) {
		try {
			if(masterProductVariantDetailsEntity.getProductVariantIsActive()==0) {
				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentDate = ft.format(dNow);
				AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				String name = authentication.getName();
				System.out.println(" name :: " + name);
				auditTrailDetailsEntity.setOperatorActions(
						"Status Changed Active to In-Active of   " + masterProductVariantDetailsEntity.getProductVariantCode() + 
								 " by " + name);
				auditTrailDetailsEntity.setField("Status changed to In Active");
//				auditTrailDetailsEntity.setAfterValue(0);
//				auditTrailDetailsEntity.setBeforeValue(0);
				auditTrailDetailsEntity.setReason("Status changed to In Active");

				auditTrailDetailsEntity.setUsername(name);
				auditTrailDetailsEntity.setDatetimeC(currentDate);
				auditTrailDetailsRepository.save(auditTrailDetailsEntity);
			}
			else {
				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentDate = ft.format(dNow);
				AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				String name = authentication.getName();
				System.out.println(" name :: " + name);
				auditTrailDetailsEntity.setOperatorActions(
						"Status Changed In-Active to Active of   " + masterProductVariantDetailsEntity.getProductVariantCode() + 
								 " by " + name);
				auditTrailDetailsEntity.setField("Status changed to Active");
//				auditTrailDetailsEntity.setAfterValue(0);
//				auditTrailDetailsEntity.setBeforeValue(0);
				auditTrailDetailsEntity.setReason("Status changed to Active");

				auditTrailDetailsEntity.setUsername(name);
				auditTrailDetailsEntity.setDatetimeC(currentDate);
				auditTrailDetailsRepository.save(auditTrailDetailsEntity);
			}
			masterProductVariantDetailsRepository.save(masterProductVariantDetailsEntity);

			return ResponseHandler.generateResponse("Material entry updated sucessfully", HttpStatus.OK, null);
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Error occurred at server side", HttpStatus.INTERNAL_SERVER_ERROR,
					null);

		}

	}

	public ResponseEntity<Object> deleteMasterProductVariantDetails(int id) {
		try {
			List<MasterProductVariantDetailsEntity> productVariantDetails = masterProductVariantDetailsRepository
					.findByProductVariantId(id);

			if (productVariantDetails.size() > 0) {
				productVariantDetails.get(0).setProductVariantIsDeleted(1);

				masterProductVariantDetailsRepository.save(productVariantDetails.get(0));
				
//				Date dNow = new Date();
//				SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				String currentDate = ft.format(dNow);
//				AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
//				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//				String name = authentication.getName();
//				System.out.println(" name :: " + name);
//				auditTrailDetailsEntity.setOperatorActions(
//						"Part number " + productVariantDetails.get(0).getProductVariantCode() + " deleted by " 
//								 + name);
//				auditTrailDetailsEntity.setField("Par deleted");
////				auditTrailDetailsEntity.setAfterValue(0);
////				auditTrailDetailsEntity.setBeforeValue(0);
//				auditTrailDetailsEntity.setReason("Part deleted");
//
//				auditTrailDetailsEntity.setUsername(name);
//				auditTrailDetailsEntity.setDatetimeC(currentDate);
//				auditTrailDetailsRepository.save(auditTrailDetailsEntity);
				
				return ResponseHandler.generateResponse("Material entry deleted sucessfully", HttpStatus.OK, null);
			} else {
				return ResponseHandler.generateResponse("Material entry already deleted", HttpStatus.ALREADY_REPORTED,
						null);
			}

		} catch (Exception ex) {

			return ResponseHandler.generateResponse("Error occurred at server side", HttpStatus.INTERNAL_SERVER_ERROR,
					null);

		}
	}

	



	public List<MasterProductVariantDetailsEntity> findByProductVariantIsDeleted() {
		List<MasterProductVariantDetailsEntity> findByproductVariantIsDeleted = masterProductVariantDetailsRepository
				.findByproductVariantIsDeleted(0);
		return findByproductVariantIsDeleted;
	}
}