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
@Table(name="ats_wms_master_sensor_details")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class MasterSensorDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EQUIPMENT_SENSOR_ID")
	private int equipmentSensorId;
	
	@Column(name = "WMS_EQUIPMENT_ID")
	private int wmsEquipmentId;
	
	@Column(name = "EQUIPMENT_SENSOR_NAME")
	private String equipmentSensorName;
	
	@Column(name = "EQUIPMENT_SENSOR_DESC")
	private String equipmentSensorDesc;
	
	@Column(name = "EQUIPMENT_SENSOR_TAG")
	private String equipmentSensorTag;
	
	@Column(name = "EQUIPMENT_SENSOR_STATUS")
	private int equipmentSensorStatus;
	
	@Column(name = "EQUIPMENT_SENSOR_CDATETIME")
	private String equipmentSensorCdatetime;
	
	@Column(name = "EQUIPMENT_SENSOR_IS_DELETED")
	private int equipmentSensorIsDeleted;
	

	


}
