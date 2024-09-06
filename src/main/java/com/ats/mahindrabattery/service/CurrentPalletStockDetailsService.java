package com.ats.mahindrabattery.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.ats.mahindrabattery.entity.CurrentPalletStockDetailsEntity;
import com.ats.mahindrabattery.entity.GenerateManualRetrievalOrderEntity;

public interface CurrentPalletStockDetailsService {

	public List<CurrentPalletStockDetailsEntity> findAllCurrentPalletStockDetails();

	public CurrentPalletStockDetailsEntity findbycurrentPalletStockDetailsId(int currentPalletStockDetailsId);

	public List<CurrentPalletStockDetailsEntity> findByQuantity(int quantity);

	public List<CurrentPalletStockDetailsEntity> findAllByPositionName(String positionName);

	public List<CurrentPalletStockDetailsEntity> findBypalletCode(String palletCode);

	public List<CurrentPalletStockDetailsEntity> findByproductName(String productName);

	public List<CurrentPalletStockDetailsEntity> findByproductVariantCode(String productVariantCode);

	public CurrentPalletStockDetailsEntity deleteByProductVariantCodeAndPalletCodeAndCurrentPalletStockDetailsId(
			String productVariantCode, String palletCode, int currentPalletStockDetailsId);

	public List<CurrentPalletStockDetailsEntity> findByAreaName(String areaName);

	public List<CurrentPalletStockDetailsEntity> findByPositionId(int positionId);

	public CurrentPalletStockDetailsEntity updateQuantityAndPalletCodeAndproductVariantCodeByPositionId(
			CurrentPalletStockDetailsEntity currentPalletStockDetailsEntity, int quantity, int positionId,
			String palletCode, String productVariantCode);

	public CurrentPalletStockDetailsEntity deleteByCurrentPalletStockDetailsId(int currentPalletStockDetailsId);

	public List<CurrentPalletStockDetailsEntity> findByLoadDateTime();

	public CurrentPalletStockDetailsEntity updateCurrentPalletStockDetails(int currentPalletStockDetailsId,
			CurrentPalletStockDetailsEntity currentPalletStockDetailsEntity);

	public List<CurrentPalletStockDetailsEntity> findByAllFilters(String startDate, String endDate,
			String productVariantCode, String floorName, String areaName, String productName,String palletStatusname);

	public List<CurrentPalletStockDetailsEntity> findbyloaddatetime();

	public ResponseEntity<Object> addCurrentPalletStockDetails(
			CurrentPalletStockDetailsEntity currentPalletStockDetailsEntity);

	public ResponseEntity<List<CurrentPalletStockDetailsEntity>> unloadingOperationDetails(String palletCode,
			String prodVariantCode, int qty, String binLocation, int userId, String userName);

	public List<CurrentPalletStockDetailsEntity> findByPalletCode(String palletCode);

	public ResponseEntity<Object> updateCurrentStockDetails(CurrentPalletStockDetailsEntity currentPalletStockDetailsEntity);

	public ResponseEntity<Object> deletCurrentStockDetailsByPalletCode(int positionId);

//	public List<CurrentPalletStockDetailsEntity> dispatchNokCurrentStock(String dispatchOrderNumber,
//			String startSerialNumber, String endSerialNumber);

	public Page<CurrentPalletStockDetailsEntity> findByBEV(Pageable pageable);

	public Page<CurrentPalletStockDetailsEntity> findByS230(Pageable pageable);

	public List<CurrentPalletStockDetailsEntity> findByOkAndBev(Pageable pageable);

	List<CurrentPalletStockDetailsEntity> findByOkAndS230(Pageable pageable);

	List<CurrentPalletStockDetailsEntity> findByNOkAndS230(Pageable pageable);

	List<CurrentPalletStockDetailsEntity> findByNOkAndBEV(Pageable pageable);
	
	public ResponseEntity<List<CurrentPalletStockDetailsEntity>> findBypalletCode1(String palletCode);

//	public ResponseEntity<Object> findByserialNumberBetween(int serialNumber1, int serialNumbere2,String dispatchOrderNumber,int shiftId,String shiftName);

}
