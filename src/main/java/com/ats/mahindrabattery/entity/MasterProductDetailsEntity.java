package com.ats.mahindrabattery.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ats_wms_master_product_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterProductDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private int productId;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "PRODUCT_DESC")
	private String productDesc;

	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "USER_NAME")
	private String userName;

//	@Column(name = "CDATETIME")
//	@DateTimeFormat(pattern = "YYYY-MM-DD")
//	private LocalDateTime cdatetime=LocalDateTime.now();
	
	@Column(name = "CDATETIME")
	private String createdDatetime;

	@Column(name = "PRODUCT_IS_ACTIVE")
	private int productIsActive;

	@Column(name = "PRODUCT_IS_DELETED")
	private int productIsDeleted;

	
	
}
