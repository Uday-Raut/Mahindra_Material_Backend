package com.ats.mahindrabattery.entity;

import java.sql.Wrapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "ats_wms_current_stock_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrentPalletStockDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CURRENT_STOCK_DETAILS_ID")
	private Integer currentPalletStockDetailsId;

	@Column(name = "PALLET_INFORMATION_ID")
	private Integer palletInformationId;

	@Column(name = "PALLET_CODE")
	private String palletCode;

	@Column(name = "POSITION_ID")
	private Integer positionId;

	@Column(name = "POSITION_NAME")
	private String positionName;

	@Column(name = "SERIAL_NUMBER")
	private Integer serialNumber;

	@Column(name = "PRODUCT_ID")
	private Integer productId;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "PRODUCT_VARIANT_ID")
	private Integer productVariantId;

	@Column(name = "PRODUCT_VARIANT_CODE")
	private String productVariantCode;

	@Column(name = "PRODUCT_VARIANT_NAME")
	private String productVariantName;

	@Column(name = "BATCH_NUMBER")
	private String batchNumber;

	@Column(name = "MODEL_NUMBER")
	private String modelNumber;

	@Column(name = "PALLET_STATUS_ID")
	private Integer palletStatusId;

	@Column(name = "PALLET_STATUS_NAME")
	private String palletStatusname;

	@Column(name = "AGEING_DAYS")
	private Integer ageingDays;

	@Column(name = "QUANTITY")
	private Integer quantity;

	@Column(name = "QUALITY_STATUS")
	private String qualityStatus;

	@Column(name = "EXPIRY_DATE")
	private String expiryDate;

	@Column(name = "LOAD_DATETIME")
	private String loadDatetime;

	@Column(name = "AREA_ID")
	private Integer areaId;

	@Column(name = "AREA_Name")
	private String areaName;

	@Column(name = "FLOOR_ID")
	private Integer floorId;

	@Column(name = "FLOOR_NAME")
	private String floorName;

	@Column(name = "RACK_ID")
	private Integer rackId;

	@Column(name = "RACK_NAME")
	private String rackName;

	@Column(name = "RACK_SIDE")
	private String rackSide;

	@Column(name = "RACK_COLUMN")
	private int rackColumn;

	@Column(name = "POSITION_NUMBER_IN_RACK")
	private Integer positionNumberInRack;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "IS_OUTFEED_MISSION_GENERATED")
	private Integer isOutfeedMissionGenerated;

	@Column(name = "IS_INFEED_MISSION_GENERATED")
	private Integer isInfeedMissionGenerated;

	

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "USER_NAME")
	private String userName;

//	@Column(name = "MFG_DATE")
//	private String mfgDate;
//
//	@Column(name = "MFG_SHIFT")
//	private String mfgShift;
//	
//	@Column(name = "VENDOR_CODE")
//	private String vendorCode;
//	
//	@Column(name = "PART_IDENTIFICATION_CODE")
//	private String partIdentificationCode;
//	
	

}
