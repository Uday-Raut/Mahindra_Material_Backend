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
@Table(name = "ats_wms_staging_table_picklist_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PickListStagingTableEntity {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "WMS_STAGING_TABLE_PICKLIST_DETAILS_ID")
	private int picklistId;
	
	@Column(name = "BIN_LOCATION")
	private String binLocation;
	
	@Column(name = "SAP_ORDER_NO")
	private String sapOrderNo;
	
	@Column(name = "UOM")
	private String uom;
	
	@Column(name = "QUANTITY")
	private int quantity;
	
	
	
	@Column(name = "GRN_NUMBER")
	private String grnNumber;
	
	@Column(name = "PO_NUMBER")
	private String poNumber;
	
	@Column(name = "BATCH_NUMBER")
	private String batchNumber;
	
	@Column(name = "SAP_TRANSFER_ORDER_DATE")
	private String sapTransferOrderDate;
	
	@Column(name = "IS_ORDER_FULFILLED")
	private int isOrderFullFilled;
	
	@Column(name = "CDATETIME")
	private String createdDateTime;
	
	
	
	@Column(name = "MATERIAL_NAME")
	private String materialName;
	
	@Column(name = "MATERIAL_CODE")
	private String materialCode;
	
	
	
	
	
	
	


	
}
