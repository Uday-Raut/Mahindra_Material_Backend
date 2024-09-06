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
@Table(name = "ats_wms_quality_inspection_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QualityInspectionStatusEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "QUALITY_INSPECTION_STATUS_ID")
	private int qualityInspectionStatusId;
	
	@Column(name = "QUALITY_INSPECTION_STATUS_NAME")
	private String qualityInspectionStatusName;
	
	@Column(name = "CDATETIME")
	private String cDatetime;

	

}
