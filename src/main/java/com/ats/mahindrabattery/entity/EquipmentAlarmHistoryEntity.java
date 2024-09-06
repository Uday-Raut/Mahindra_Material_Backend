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
@Table(name = "ats_wms_equipment_alarm_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentAlarmHistoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WMS_EQUIPMENT_ALARM_HISTORY_ID")
	private int wmsEquipmentAlarmHistoryId;
	
	@Column(name = "EQUIPMENT_ID")
	private int equipmentId;
	
	@Column(name = "EQUIPMENT_NAME")
	private String equipmentName;
	
	@Column(name = "EQUIPMENT_DESC")
	private String equipmentDesc;
	
	@Column(name = "EQUIPMENT_ALARM_ID")
	private int equipmentAlarmId;
	
	@Column(name = "EQUIPMENT_ALARM_NAME")
	private String equipmentAlarmName;
	
	@Column(name = "EQUIPMENT_ALARM_DESC")
	private String equipmentAlarmDesc;
	
	@Column(name = "EQUIPMENT_ALARM_OCCURRED_DATETIME")
	private String equipmentAlarmOccurredDatetime;
	
	@Column(name = "EQUIPMENT_ALARM_RESOLVED_DATETIME")
	private String equipmentAlarmResolvedDatetime;
	
	@Column(name = "EQUIPMENT_ALARM_STATUS")
	private int equipmentAlarmStatus;
	
	@Column(name = "ALARM_LEVEL")
	private String alarmLevel;
	
	@Column(name = "ALARM_ZONE")
	private String alarmZone;
	
	


	
	
	
}
