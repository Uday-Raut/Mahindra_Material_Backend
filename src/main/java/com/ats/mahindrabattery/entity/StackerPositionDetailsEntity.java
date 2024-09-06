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
@Table(name  = "ats_wms_stacker_position_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StackerPositionDetailsEntity {
	
   
	
	@Id
	@GeneratedValue(strategy = 	GenerationType.IDENTITY)
	
	@Column(name = "STACKER_POSITION_ID")
	private int stackerPositionId;
	
	@Column(name = "EQUIPMENT_ID")
	private int equipmentId;
	
	@Column(name="STACKER_IS_PRESENT")
	private int stackerIsPresent;
	
	@Column(name = "MIN_VALUE")
	private int minValue;
	
	@Column(name="MAX_VALUE")
	private int maxValue;

	
}
