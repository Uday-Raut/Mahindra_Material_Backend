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
@Table(name = "ats_wms_master_floor_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterFloorDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FLOOR_ID")
	private int floorId;
	
	@Column(name = "FLOOR_NAME")
	private String floorName;
	
	@Column(name = "FLOOR_DESC")
	private String floorDesc;
	
	@Column(name = "USER_ID")
	private int userId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "CDATETIME")
	private String cDateTime;
	
	@Column(name = "FLOOR_IS_ACTIVE")
	private int floorIsActive;
	
	@Column(name = "FLOOR_IS_DELETED")
	private int floorIsDeleted;

	
}
