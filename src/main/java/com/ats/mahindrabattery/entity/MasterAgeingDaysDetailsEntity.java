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
@Table(name = "ats_wms_master_ageing_days_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterAgeingDaysDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MASTER_AGEING_DAYS_DETAILS_ID")
	private int masterAgeingDaysDetailsId;
	
	@Column(name = "AGEING_DAYS")
	private int ageingDays;
	
	@Column(name = "CDATETIME")
	private String cdateTime;
	
	@Column(name = "AGEING_DAYS_IS_DELETED")
	private int ageingDaysIsDeleted;
	
	@Column(name="PRODUCT_NAME")
	private String productName;
}