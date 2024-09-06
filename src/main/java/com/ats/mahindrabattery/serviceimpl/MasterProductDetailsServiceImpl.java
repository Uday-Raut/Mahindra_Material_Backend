package com.ats.mahindrabattery.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.MasterProductDetailsEntity;
import com.ats.mahindrabattery.entity.MasterProductVariantDetailsEntity;
import com.ats.mahindrabattery.exception.ResourceNotFoundException;
import com.ats.mahindrabattery.repository.MasterProductDetailsRepository;
import com.ats.mahindrabattery.response.ResponseHandler;
import com.ats.mahindrabattery.service.MasterProductDetailsService;

@Service
public class MasterProductDetailsServiceImpl implements MasterProductDetailsService {

	@Autowired
	private MasterProductDetailsRepository masterProductDetailsRepository;


	
	public ResponseEntity<Object> createmasterMasterProductDetails(
			MasterProductDetailsEntity masterProductDetailsEntity) {
		try {
			List<MasterProductDetailsEntity> list = masterProductDetailsRepository
					.findByproductName(masterProductDetailsEntity.getProductName());

			if (list.size() == 0) {
				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
				String date = ft.format(dNow);
				masterProductDetailsEntity.setCreatedDatetime(date);

				masterProductDetailsRepository.save(masterProductDetailsEntity);
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


	public List<MasterProductDetailsEntity> getAllMasterProductDetails(
			) {
		try {
			List<MasterProductDetailsEntity> findByproductIsDeleted = masterProductDetailsRepository
					.findByproductIsDeleted(0);
			
			return findByproductIsDeleted;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}


	public MasterProductDetailsEntity findMasterProductDetailsById(Integer id) {
		MasterProductDetailsEntity masterProductDetails = masterProductDetailsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("MasterProductDetailsEntity","Id",id));
		return masterProductDetails;
	}


	
	public ResponseEntity<Object> updateMasterProductDetails(
			MasterProductDetailsEntity masterProductDetailsEntity) {
		try {
			masterProductDetailsRepository.save(masterProductDetailsEntity);

			return ResponseHandler.generateResponse("Material entry updated sucessfully", HttpStatus.OK, null);
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Error occurred at server side", HttpStatus.INTERNAL_SERVER_ERROR,
					null);

		}

	}
	


	
	
	public ResponseEntity<Object> deleteMasterProductDetails(int id) {
		try {
			List<MasterProductDetailsEntity> productDetails = masterProductDetailsRepository
					.findByProductId(id);
			
			if (productDetails.size() > 0) {
				productDetails.get(0).setProductIsDeleted(1);
			
				masterProductDetailsRepository.save(productDetails.get(0));
				return ResponseHandler.generateResponse("Material entry deleted sucessfully", HttpStatus.OK, null);
			}else {
				return ResponseHandler.generateResponse("Material entry already deleted", HttpStatus.ALREADY_REPORTED, null);
			}

		} catch (Exception ex) {
		
			return ResponseHandler.generateResponse("Error occurred at server side", HttpStatus.INTERNAL_SERVER_ERROR,
					null);

		}
	}
	public List<MasterProductDetailsEntity> findByProductIsDeleted() {
		List<MasterProductDetailsEntity> findByproductIsDeleted = masterProductDetailsRepository
				.findByproductIsDeleted(0);
		return findByproductIsDeleted;
	}

	

	
}
