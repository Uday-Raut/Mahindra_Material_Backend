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
@Table(name = "ats_wms_master_rack_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterRackDetailsEntity {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RACK_ID")
	private int rackId;

	@Column(name = "AREA_ID")
	private int areaId;

	@Column(name = "FLOOR_ID")
	private int floorId;

	@Column(name = "RACK_NAME")
	private String rackName;

	@Column(name = "RACK_DESC")
	private String rackDesk;

	@Column(name = "RACK_NUMBER")
	private int rackNumber;

	@Column(name = "RACK_COLUMN")
	private int rackColumn;

	@Column(name = "RACK_SIDE")
	private String rackSide;

	@Column(name = "CDATETIME")
	private String cDateTime;

	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "RACK_IS_ACTIVE")
	private int rackIsActive;

	@Column(name = "RACK_IS_DELETED")
	private int rackIsDeleted;

	

}
