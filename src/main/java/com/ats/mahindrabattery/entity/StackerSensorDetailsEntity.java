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
@Table(name="ats_wms_stacker_sensor_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StackerSensorDetailsEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STACKER_SENSOR_DETAILS_ID")
	private int stackerSensorDetailsId;
	
	@Column(name = "STACKER_SENSOR_NAME")
	private String stackerSensorName;
	
	@Column(name = "STACKER_SENSOR_TAG_NAME")
	private String stackerSensorTagName;
	
	@Column(name = "STACKER_SENSOR_STATUS")
	private int stackerSensorStatus;
	
	@Column(name="LAYOUT_SENSOR_NUMBER")
	private int layoutSensorNumber;
	
	@Column(name = "STACKER_SENSOR_IS_DELETED")
	private int stackerSensorIsDeleted;
	
	@Column(name = "STACKER_ID")
	private int stackerId;
	
	
	
}
