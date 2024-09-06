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
@Table(name = "ats_wms_master_area_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterAreaDetailsEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AREA_ID")
	private int areaId;
	
	@Column(name = "AREA_NAME")
	private String areaName;
	
	@Column(name = "AREA_DESC")
	private String areaDesc;
	
	@Column(name = "USER_ID")
	private int userId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "CDATETIME")
	private String cDateTime;
	
	@Column(name = "AREA_IS_ACTIVE")
	private int areaIsActive;
	
	@Column(name = "AREA_IS_DELETED")
	private int areaIsDeleted;


}
