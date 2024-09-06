
package com.ats.mahindrabattery.entity;

import java.time.LocalDateTime;

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
@Table(name = "ats_wms_master_equipment_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterEquipementDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EQUIPMENT_ID")
	private int equipmentId;
	
	@Column(name = "EQUIPMENT_NAME")
	private String equipmentName;
	
	@Column(name = "EQUIPMENT_DESC")
	private String equipementDesc;
	
	@Column(name = "USER_ID")
	private int userId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "CDATETIME")
	private String cDateTime;
	
	@Column(name = "EQUIPMENT_IS_ACTIVE")
	private int equipmentIsActive;
	
	@Column(name = "EQUIPMENT_IS_DELETED")
	private int equipmentIsDeleted;


}
