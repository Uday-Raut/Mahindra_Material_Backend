package com.ats.mahindrabattery.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = "ats_wms_inventory_summary_dashboard_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgeingDaysReportEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inventorySummaryDashboardDetailsId;

	private String productName;

	private String productvariantCode;

	private String productvariantName;

//	private int belowAgingDaysQuantity;
//
//	private int aboveAgingDaysQuantity;

	private int range0to3;

	private int range4to7;

	private int range8to14;

	private int range15to30;

	private int range30plus;

	private int totalQuantity;

}
