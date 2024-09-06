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
@Table(name = "ats_wms_yokogawa_temperature_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YokogawaTemperatureDetailsEntity {

	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "TEMPERATURE_DETAILS_ID")
	private int temperatureDetailsId;

	@Column(name = "MINIMUM_TEMPERATURE")
	private int minTemp;

	@Column(name = "MAXIMUM_TEMPERATURE")
	private int maxTemp;

	@Column(name = "CDATETIME")
	private String createdDateTime;

}
