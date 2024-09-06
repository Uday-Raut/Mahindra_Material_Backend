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
@Table(name = "ats_wms_transfer_pallet_mission_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferPalletMissionDetailsEntity {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANSFER_PALLET_MISSION_DETAILS_ID")
	private int transferPalletMissionDetailsId;

	@Column(name = "PALLET_INFORMATION_ID")
	private int palletInformationId;

	@Column(name = "PALLET_CODE")
	private String palletCode;

	@Column(name = "POSITION_ID")
	private int positionId;

	@Column(name = "POSITION_NAME")
	private String positionName;

	@Column(name = "TRANSFER_POSITION_ID")
	private int transferPositionId;

	@Column(name = "TRANSFER_POSITION_NAME")
	private String transferPositionName;

	@Column(name = "IS_MISSION_GENERATED")
	private int isMissionGenerated;

	@Column(name = "IS_MANUAL_MISSION")
	private int isManualMission;
	
	
//	@Column(name="IS_ALARM_RACK")
//	private  int isAlarmRack;

	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "CDATETIME")
	private String cDateTime;

	
}
