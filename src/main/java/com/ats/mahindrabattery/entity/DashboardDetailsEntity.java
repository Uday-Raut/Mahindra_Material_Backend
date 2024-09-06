package com.ats.mahindrabattery.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ats_wms_dashboard_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DASHBOARD_ID")
	private int dashboardId;
	
	@Column(name = "TOTAL_ALARM_COUNT")
	private int totalAlarmCount;
	
	@Column(name = "BEV_INFEED_COUNT")
	private int bevInfeedCount;
	
	@Column(name = "S230_INFEED_COUNT")
	private int s230InfeedCount;
	
	@Column(name = "BEV_OUTFEED_COUNT")
	private int bevOutfeedCount;
	
	@Column(name = "S230_OUTFEED_COUNT")
	private int s230OutfeedCount;
	
	@Column(name = "CURRENT_OK_MATERIAL_COUNT")
	private int currentokMaterialCount;
	
	@Column(name = "CURRENT_NOK_MATERIAL_COUNT")
	private int currentNokMaterialCount;
	
	@Column(name = "TOTAL_ORDERS")
	private int totalOrders;
	
	@Column(name = "EXECUTED_ORDERS")
	private int executedOrders;
	
	@Column(name = "REMAINING_ORDERS")
	private int remainingOrders;
	
	@Column(name = "PERCENTAGE_ORDERS")
	private String percentageOrders;
	
//	@Column(name = "TOTAL_NOK_COUNT")
//	private int totalNOKCount;
//	
//	@Column(name = "TOTAL_OK_COUNT")
//	private int totalOKCount;
	
	@Column(name = "TOTAL_CURRENTSTOCK_COUNT")
	private int totalCurrentStockCount;
	
	@Column(name = "TOTAL_INFEED_COUNT")
	private int totalInfeedCount;
	
	@Column(name = "TOTAL_OUTFEED_COUNT")
	private int totalOutfeedCount;
	
	@Column(name = "TOTAL_OK_BEV_COUNT")
	private int totalOKBEVCount;
	
	@Column(name = "TOTAL_NOK_BEV_COUNT")
	private int totalNOKBEVCount;
	
	@Column(name = "TOTAL_S230_OK_COUNT")
	private int totalS230OKCount;
	
	@Column(name = "TOTAL_S230_NOK_COUNT")
	private int totalS230NOKCount;
	
	@Column(name = "CREATED_DATETIME")
	private String cDateTime;
	
	
	@Column(name = "BEV_COUNT")
	private int bevCurrentStockCount;
	
	@Column(name="S230_COUNT")
	private int s230CurrentStockCount;
	
	@Column(name="EMPTY_PALLET_COUNT")
	private int emptyPalletCount;
	
}
