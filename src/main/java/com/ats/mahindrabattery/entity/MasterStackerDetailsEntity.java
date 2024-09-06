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
@Table(name = "ats_wms_master_stacker_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterStackerDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STACKER_ID")
	private int stackerId;
	
	@Column(name = "STACKER_NAME")
	private String stackerName;
	
	@Column(name = "STACKER_DESC")
	private String stackerDesc;
	
	@Column(name = "STACKER_IS_ACTIVE")
	private int stackerIsActive;
	
	@Column(name = "STACKER_IS_DELETED")
	private int stackerIsDeleted;
	
	

}
