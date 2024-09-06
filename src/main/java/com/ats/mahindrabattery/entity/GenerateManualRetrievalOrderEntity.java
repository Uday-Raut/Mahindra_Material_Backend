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
import lombok.ToString;

@Entity
@Table(name = "ats_wms_material_dispatch_schedule_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateManualRetrievalOrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DISPATCH_HISTORY_ID")
	private int dispatchHistoryId;

	@Column(name = "DISPATCH_ORDER_NUMBER")
	private String dispatchOrderNumber;

	@Column(name = "PRODUCT_VARIANT_ID")
	private int productVariantId;

	@Column(name = "PRODUCT_VARIANT_CODE")
	private String productVariantCode;

	@Column(name = "PRODUCT_VARIANT_NAME")
	private String productVariantName;

	@Column(name = "PRODUCT_ID")
	private int productId;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "PLANNED_QUANTITY")
	private int plannedQuantity;

	@Column(name = "ACTUAL_QUANTITY")
	private int acutualQuantity;

	@Column(name = "BALANCE_QUANTITY")
	private int balanceQuantity;

	@Column(name = "CDATETIME")
	private String createdDatetime;

	@Column(name = "SHIFT_NAME")
	private String shiftName;

	@Column(name = "SHIFT_ID")
	private int shiftId;

	@Column(name = "IS_DISPATCH_START")
	private int isDispatchStart;

	@Column(name = "DISPATCH_STATUS")
	private String dispatchStatus;

	@Column(name = "SERIAL_NUMBER")
	private int serialNumber;

	@Column(name = "IS_ORDER_CANCELLED")
	private int isOrderCancelled;

	@Column(name = "IS_ORDER_DELETED")
	private int isOrderDeleted;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "LOAD_DATETIME")
	private String loadDatetime;

//	@Column(name = "MFG_DATE")
//	private String mfgDate;
//
//	@Column(name = "MFG_SHIFT")
//	private String mfgShift;
}
