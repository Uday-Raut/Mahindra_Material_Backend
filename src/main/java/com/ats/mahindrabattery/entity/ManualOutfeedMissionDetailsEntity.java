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
@Table(name = "ats_wms_manual_outfeed_mission_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManualOutfeedMissionDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MANUAL_OUTFEED_MISSION_DETAILS_ID")
	private int manualOutfeedMissionDetailsId;

	@Column(name = "PALLET_INFORMATION_ID")
	private int palletInformationDetailsId;

	@Column(name = "PALLET_CODE")
	private String palletCode; 
	
	@Column(name = "POSITION_ID")
	private int positionId;

	@Column(name = "POSITION_NAME")
	private String positionName;

	@Column(name = "IS_MISSION_GENERATED")
	private int isMissionGenerated;

	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "CDATETIME")
	private String cDateTime;
	
	@Column(name = "STATION_ID")
	private int stationId;


	@Column(name = "LOAD_DATETIME")
	private String loadDateTime;
	
//	@Column(name = "MFG_DATE")
//	private String mfgDate;
//	
//
//	@Column(name = "MFG_SHIFT")
//	private String mfgShift;

	

}
