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
@Table(name  = "ats_wms_equipment_alarm_troubleshoot_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentAlarmTroubleshootDetailsEntity {
	
	     @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)

	    @Column(name = "ALARM_TROUBLESHOOT_DETAILS_ID")
	    private int alarmTroubleshootId;

	    @Column(name = "EQUIPMENT_ALARM_ID")
	     private int equipmentAlarmId;

	    @Column(name = "ALARM_RESOLVE_STEPS")
	    private String alarmResolveSteps;


}
