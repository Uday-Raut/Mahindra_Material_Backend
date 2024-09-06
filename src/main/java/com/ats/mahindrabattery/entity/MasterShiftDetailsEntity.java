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
@Table(name="ats_wms_master_shift_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterShiftDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = 	GenerationType.IDENTITY)
	@Column(name = "SHIFT_ID")
	private int  shiftId;
	
	@Column(name = "SHIFT_NUMBER")
	private int shiftNumber;
	
	@Column(name = "SHIFT_NAME")
	private String shiftName;
	
	@Column(name = "CDATETIME")
	private String cDateTime;
	
	@Column(name = "SHIFT_DESC")
	private String shiftDesc;
	
	@Column(name = "SHIFT_START_TIME")
	private String shiftStartTime;
	
	
	@Column(name = "SHIFT_END_TIME")
	private String shiftEndTime;
	
	@Column(name = "USER_ID")
	private int userId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "SHIFT_IS_DELETED")
	private int shiftIsDeleted;
	
	
	

}
