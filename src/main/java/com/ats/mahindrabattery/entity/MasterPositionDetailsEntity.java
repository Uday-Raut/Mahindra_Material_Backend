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
import lombok.ToString;

@Entity
@Table(name = "ats_wms_master_position_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MasterPositionDetailsEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POSITION_ID")
	private int positionId;

	@Column(name = "RACK_ID")
	private int rackId;

	@Column(name = "AREA_ID")
	private int areaId;

	@Column(name = "FLOOR_ID")
	private int floorId;

	@Column(name = "POSITION_NAME")
	private String positionName;

	@Column(name = "POSITION_DESC")
	private String positionDesc;

	@Column(name = "POSITION_NUMBER_IN_RACK")
	private int positionNumberInRack;

	@Column(name = "POSITION_IS_ALLOCATED")
	private int positionIsAllocated;

	@Column(name = "POSITION_IS_EMPTY")
	private int emptyPalletPosition;

	@Transient
	private int isMaterialLoaded;

//	@Transient
//	private String productVariantName;
	@Transient
	private String productName;

	@Column(name = "CDATETIME")
	private String cDateTime;

	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "POSITION_IS_ACTIVE")
	private int positionIsActive;

	@Column(name = "POSITION_IS_DELETED")
	private int positionIsDeleted;

	@Column(name = "IS_MANUAL_DISPATCH")
	private int isManualDispatch;

	

}
