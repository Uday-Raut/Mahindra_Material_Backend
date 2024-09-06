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
@Table(name = "ats_wms_mapping_floor_area_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MappingFloorAreaDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FLOOR_AREA_MAPPING_ID")
	private int floorAreaMappingid;

	@Column(name = "FLOOR_ID")
	private int floorId;

	@Column(name = "FLOOR_NAME")
	private String floorName;
	
	@Column(name = "AREA_ID")
	private int areaId;
	
	@Column(name = "AREA_NAME")
	private String areaName;

	@Column(name = "INFEED_IS_ACTIVE")
	private int infeedIsActive;
	
	@Column(name="OUTFEED_IS_ACTIVE")
	private int outfeedIsActive;
	
	@Column(name="USER_ID")
	private int userId;
	
	@Column(name="USER_NAME")
	private String userName;


}