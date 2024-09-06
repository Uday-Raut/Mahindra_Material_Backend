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
@Table(name  = "ats_wms_qrcode_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QRCodeDetailsEntity {
	
	
	@Id
	@GeneratedValue(strategy = 	GenerationType.IDENTITY)
	@Column(name = "QR_CODE_ID")
	private int qrCodeId;
	
	@Column(name="QR_CODE_DATA")
	private String qrCodeData;
	
	@Column(name="IS_QR_CODE_GENERATED")
	private int isQRCodeGenerated;
	
	@Column(name="QRCODE")
	private byte[] qrCode;
	
	@Column(name="MATERIAL_CODE")
	private String materialCode;
	
	

	

}
