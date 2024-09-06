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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ats_wms_buffer_details")
public class BufferDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BUFFER_ID")
	private int bufferId;
	
	
	@Column(name="PALLET_INFORMATION_ID")
	private int palletInformationId;
	
	@Column(name="PALLET_CODE")
	private String palletCode;
	
	@Column(name="SERIAL_NUMBER")
	private int serialNumber;
	
	@Column(name="PRODUCT_ID")
	private int productId;
	
	@Column(name="PRODUCT_NAME")
	private String productName;
	
	@Column(name="PRODUCT_VARIANT_ID")
	private int productVariantId;
	
	@Column(name="PRODUCT_VARIANT_CODE")
	private String productVariantCode;
	
	@Column(name="PRODUCT_VARIANT_NAME")
	private String productVariantName;
	
	@Column(name="QUANTITY")
	private int quantity;
	
	@Column(name="LOAD_DATE_TIME")
	private String loadDateTime;
	
	@Column(name="MFG_DATE")
	private String mfgDate;
	
	
	@Column(name="MFG_SHIFT")
	private String mfgShift;
	
	@Column(name="BUFFER_IS_DELETED")
	private int bufferIsDeleted;
	
	
}
