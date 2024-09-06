package com.ats.mahindrabattery.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ats_wms_master_product_variant_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterProductVariantDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_VARIANT_ID")
	private int productVariantId;

	@Column(name = "PRODUCT_VARIANT_CODE")
	private String productVariantCode;

	@Column(name = "PRODUCT_ID")
	private int productId;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "PRODUCT_VARIANT_NAME")
	private String productVariantname;

	@Column(name = "PRODUCT_VARIANT_DESC")
	private String productVariantDesc;

	@Column(name = "QUANTITY")
	private int quantity;

	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "INSPECTION_IN_DAYS")
	private int inspectionInDays = 720;

	@Column(name = "PRODUCT_VARIANT_IS_ACTIVE")
	private int productVariantIsActive;

	@Column(name = "PRODUCT_VARIANT_IS_DELETED")
	private int productVariantIsDeleted;

	@Column(name = "AGEING_DAYS")
	private int aegingDays;

	@Column(name = "CDATETIME")
	private String cdatetime;

	
}
