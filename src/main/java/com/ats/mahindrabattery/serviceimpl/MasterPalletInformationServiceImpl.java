package com.ats.mahindrabattery.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ats.mahindrabattery.entity.CurrentPalletStockDetailsEntity;
import com.ats.mahindrabattery.entity.MasterPalletInformationEntity;
import com.ats.mahindrabattery.repository.CurrentPalletStockDetailsRepository;
import com.ats.mahindrabattery.repository.MasterPalletInformationRepository;
import com.ats.mahindrabattery.service.MasterPalletInformationService;

@Service
public class MasterPalletInformationServiceImpl implements MasterPalletInformationService {

	@Autowired
	MasterPalletInformationRepository masterPalletInformationRepositoryInstance;

	@Autowired
	private CurrentPalletStockDetailsRepository currentPalletStockDetailsRepositoryInstance;

	public List<MasterPalletInformationEntity> findAllMasterPalletInformation() {
		try {
			return masterPalletInformationRepositoryInstance.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void addMasterPalletInformationDetails(MasterPalletInformationEntity masterPalletInformationEntity) {

		masterPalletInformationRepositoryInstance.save(masterPalletInformationEntity);
	}

//	public MasterPalletInformationEntity addMasterPalletInformation(
//			MasterPalletInformationEntity masterPalletInformationEntity) {
//
//		// String serialNumber = "NA";
//		int ageingDays = 0;
//		String batchNumber = "NA";
//		String modelNumber = "NA";
//		String location = "NA";
//		try {
//
//			Date dNow = new Date();
//			SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
//			String currentDate = ft.format(dNow);
//			List<CurrentPalletStockDetailsEntity> findAll = currentPalletStockDetailsRepositoryInstance
//					.findBypalletInformationId(masterPalletInformationEntity.getPalletInformationId());
//			for (int i = 0; i < findAll.size(); i++) {
//				if (masterPalletInformationEntity.getPalletInformationId() == findAll.get(i).getPalletInformationId()) {
//					masterPalletInformationRepositoryInstance.updateMasterPalletInformation(
//							masterPalletInformationEntity.getPalletInformationId(),
//							masterPalletInformationEntity.getPalletCode(),
//
//							masterPalletInformationEntity.getWmsTransferMissionOrderId(),
//							masterPalletInformationEntity.getSerialNumber(),
//							masterPalletInformationEntity.getProductId(),
//							masterPalletInformationEntity.getProductName(),
//							masterPalletInformationEntity.getProductVariantId(),
//							masterPalletInformationEntity.getProductVariantName(),
//							masterPalletInformationEntity.getProductVariantCode(),
//							masterPalletInformationEntity.getQuantity(),
//							masterPalletInformationEntity.getPalletStatusId(),
//							masterPalletInformationEntity.getPalletStatusName(),
//							masterPalletInformationEntity.getQualityStatus(),
//							masterPalletInformationEntity.getIsInfeedMissionGenerated(),
//							masterPalletInformationEntity.getIsOutfeedMissionGenerated(),
//							masterPalletInformationEntity.getIsTransferManagementMissionGenerated(),
//							masterPalletInformationEntity.getStationWorkdone(), currentDate,
//							masterPalletInformationEntity.getPalletInformationIsDeleted());
//
//					currentPalletStockDetailsRepositoryInstance.updateCurrentPalletInforamtion(
//							masterPalletInformationEntity.getPalletInformationId(),
//							masterPalletInformationEntity.getPalletCode(),
//							masterPalletInformationEntity.getSerialNumber(),
//							masterPalletInformationEntity.getProductId(),
//							masterPalletInformationEntity.getProductName(),
//							masterPalletInformationEntity.getProductVariantId(),
//							masterPalletInformationEntity.getProductVariantName(),
//							masterPalletInformationEntity.getProductVariantCode(),
//							masterPalletInformationEntity.getQuantity(),
//							masterPalletInformationEntity.getPalletStatusId(),
//							masterPalletInformationEntity.getPalletStatusName(), ageingDays, batchNumber, modelNumber,
//							location, masterPalletInformationEntity.getQualityStatus(),
//							masterPalletInformationEntity.getIsInfeedMissionGenerated(),
//							masterPalletInformationEntity.getIsOutfeedMissionGenerated(), currentDate);
//
//					System.out.println("DATA UPDATED SUCCESSFULLY....");
//
//				}
//
//				else {
//					// MasterPalletInformationEntity masterPalletInformationEntity2 = new
//					// MasterPalletInformationEntity();
//					masterPalletInformationEntity.setCdatetime(currentDate);
//					MasterPalletInformationEntity masterPalletInformationEntity2 = masterPalletInformationRepositoryInstance
//							.save(masterPalletInformationEntity);
//					System.out.println("Data added succesfully....");
//					return masterPalletInformationEntity2;
//				}
//
//			}
//			return masterPalletInformationEntity;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
//
//	}
	 
	

	public List<MasterPalletInformationEntity> findPalletInformationIdDesc() {
		try {
			return masterPalletInformationRepositoryInstance.findAllByOrderByPalletInformationIdDesc();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

//	public List<MasterPalletInformationEntity> updatePalletInformationDetails(int palletStatusId,
//			String palletStatusName, String palletCode, int stationWorkdone) {
//		try {
//			List<MasterPalletInformationEntity> masterPalletInfoList = masterPalletInformationRepositoryInstance
//					.findByPalletCodeOrderByPalletInformationIdDesc(palletCode);
//			System.out.println("in updatePalletInformationDetails ::" + palletCode);
//			if (masterPalletInfoList.size() > 0) {
//				System.out.println("updatePalletStatusNameBypalletInformationId ::"
//						+ masterPalletInfoList.get(0).getPalletInformationId());
//				masterPalletInformationRepositoryInstance.updatePalletStatusNameBypalletInformationId(palletStatusId,
//						palletStatusName, stationWorkdone, masterPalletInfoList.get(0).getPalletInformationId());
//				System.out.println("palletStatusName ::" + palletStatusName);
//			}
//			List<CurrentPalletStockDetailsEntity> list = currentPalletStockDetailsRepositoryInstance
//					.findByPalletCode(palletCode);
//			if (list.size() == 0) {
//				System.out.println("Empty pallet data added on WorkDone button");
//				List<MasterPalletInformationEntity> palletInfoList = masterPalletInformationRepositoryInstance
//						.findByPalletCode(palletCode);
//
//				Date dNow = new Date();
//				SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy" + " " + "HH:mm:ss");
//				String date = ft.format(dNow);
//				CurrentPalletStockDetailsEntity instance = new CurrentPalletStockDetailsEntity(0,
//						
//						palletInfoList.get(0).getPalletInformationId(), palletCode, 0, "NA", "NA", "NA", "NA", 0, "NA",
//						0, "NA", "NA", "NA", 0, "NA", 0, 0, 0, 0, date, date, 0, "NA", 0, "NA", 0, "NA", "NA", 0, 0,
//						"NA");
//				
//				currentPalletStockDetailsRepositoryInstance.save(instance);
//
//			}
//			return masterPalletInfoList = masterPalletInformationRepositoryInstance.findByPalletCode(palletCode);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return null;
//	}

	public List<MasterPalletInformationEntity> updatePalletInformationDetailsByStationWorkdone(int stationWorkdone,
			String palletCode) {
		try {
			List<MasterPalletInformationEntity> masterPalletInfoList = masterPalletInformationRepositoryInstance
					.findByPalletCodeOrderByPalletInformationIdDesc(palletCode);
			System.out.println("work done: " + palletCode + " " + stationWorkdone);
			if (masterPalletInfoList.size() > 0) {
				System.out.println("pallet info id: " + masterPalletInfoList.get(0).getPalletInformationId());
				masterPalletInformationRepositoryInstance.updatePalletStationWorkdoneBypalletInformationId(
						stationWorkdone, masterPalletInfoList.get(0).getPalletInformationId());

			}
			return masterPalletInformationRepositoryInstance.findByPalletCode(palletCode);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MasterPalletInformationEntity> updatePalletInformationDetails(int palletStatusId,
			String palletStatusName, String palletCode, int stationWorkdone) {
		// TODO Auto-generated method stub
		return null;
	}
	
	


//	  public MasterPalletInformationEntity addPalletInformationDetailsFillData(String palletCode, String productName, String productVariantCode,
//	            String productVariantName, String location, String batchNumber, String modelNumber, int quantity, int userId, String userName) {
//
//	        // Create an instance of MasterPalletInformationModel and set the values
//		  MasterPalletInformationEntity palletInformation = new MasterPalletInformationEntity();
//	        palletInformation.setPalletCode(palletCode);
//	        palletInformation.setProductName(productName);
//	        palletInformation.setProductVariantCode(productVariantCode);
//	        palletInformation.setProductVariantName(productVariantName);
//	        palletInformation.setLocation(location);
//	        palletInformation.setBatchNumber(batchNumber);
//	        palletInformation.setModelNumber(modelNumber);
//	        palletInformation.setQuantity(quantity);
//	        palletInformation.setUserId(userId);
//	        palletInformation.setUserName(userName);
//
//	        // Save the information to the database
//	        return masterPalletInformationRepositoryInstance.save(palletInformation);
//	    }
//	}

}
