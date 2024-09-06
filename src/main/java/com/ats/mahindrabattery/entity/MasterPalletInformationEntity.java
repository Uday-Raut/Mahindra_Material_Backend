package com.ats.mahindrabattery.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ats_wms_master_pallet_information")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterPalletInformationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PALLET_INFORMATION_ID")
	private int palletInformationId;

	@Column(name = "PALLET_CODE")
	private String palletCode;

	@Column(name = "WMS_TRANSFER_ORDER_ID")
	private String wmsTransferMissionOrderId;

	@Column(name = "PRODUCT_ID")
	private int productId;

	@Column(name = "PRODUCT_VARIANT_ID")
	private int productVariantId;

	@Column(name = "QUANTITY")
	private int quantity;

	@Column(name = "QUALITY_STATUS")
	private String qualityStatus;

	@Column(name = "PALLET_STATUS_ID")
	private int palletStatusId;

	@Column(name = "PALLET_STATUS_NAME")
	private String palletStatusName;

	@Column(name = "IS_INFEED_MISSION_GENERATED")
	private int isInfeedMissionGenerated;

	@Column(name = "IS_OUTFEED_MISSION_GENERATED")
	private int isOutfeedMissionGenerated;

	@Column(name = "IS_TRANSFER_MANAGEMENT_MISSION_GENERATED")
	private int isTransferManagementMissionGenerated;

	@Column(name = "STATION_WORKDONE")
	private int stationWorkdone;

	@Column(name = "CDATETIME")
	private String cdatetime;

	@Column(name = "SERIAL_NUMBER")
	private int serialNumber;

	@Column(name = "PRODUCT_VARIANT_CODE")
	private String productVariantCode;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "PRODUCT_VARIANT_NAME")
	private String productVariantName;

	@Column(name = "PALLET_INFORMATION_IS_DELETED")
	private int palletInformationIsDeleted;

	@Column(name = "BATCH_NUMBER")
	private String batchNumber;

	@Column(name = "MODEL_NUMBER")
	private String modelNumber;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "VENDOR_CODE")
	private String vendorCode;

	@Column(name = "PART_IDENTIFICATION_CODE")
	private String partIdentificationCode;

//	@Column(name = "MFG_DATE")
//	private String mfgDate;
//
//	@Column(name = "MFG_SHIFT")
//	private String mfgShift;

	@Column(name = "LOAD_DATETIME")
	private String loadDateTime;

}
