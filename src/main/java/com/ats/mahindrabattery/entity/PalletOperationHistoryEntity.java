package com.ats.mahindrabattery.entity;

import java.util.Date;

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
@Table(name = "ats_wms_pallet_operation_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PalletOperationHistoryEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "PALLET_RETRIEVAL_HISTORY_ID")
	private int palletRetrievalHistoryId;

	@Column(name = "WMS_TRANSFER_ORDER_ID")
	private String wmsTransferOrderId;

	@Column(name = "PALLET_INFORMATION_ID")
	private int palletInformationId;

	@Column(name = "PALLET_CODE")
	private String palletCode;

	@Column(name = "ORDER_ID")
	private int orderId;
	
	@Column(name="SAP_ORDER_NO")
	private String sapOrderNo;

	@Column(name = "PRODUCT_ID")
	private int productId;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "PRODUCT_VARIANT_ID")
	private int productVariantId;

	@Column(name = "PRODUCT_VARIANT_CODE")
	private String productVariantCode;

	@Column(name = "PRODUCT_VARIANT_NAME")
	private String productVariantName;

	@Column(name = "QUANTITY")
	private int quantity;

	@Column(name = "POSITION_NAME")
	private String positionName;

	@Column(name = "OPERATION")
	private String operation;
	
	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "LOAD_DATETIME")
	private String loadDateTime;

	@Column(name = "AREA_ID")
	private int areaId;

	@Column(name = "AREA_NAME")
	private String areaName;

	@Column(name = "FLOOR_ID")
	private int floorId;

	@Column(name = "FLOOR_NAME")
	private String floorName;

	@Column(name = "EXPIRY_DATE")
	private String expiryDate;

	
	
	


}
