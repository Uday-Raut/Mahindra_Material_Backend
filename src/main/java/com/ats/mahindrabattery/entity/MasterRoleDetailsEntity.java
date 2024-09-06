package com.ats.mahindrabattery.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ats_wms_master_role_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterRoleDetailsEntity {

	@Id
	@Column(name = "ROLE_ID")
	private int roleId;
	
	@Column(name = "ROLE_NAME")
	private String roleName;
	
	@Column(name = "ROLE_DESC")
	private String roleDesc;
	
	@Column(name = "ROLE_CDATETIME")
	private LocalDateTime cDateTime=LocalDateTime.now();
	
	@Column(name = "ROLE_IS_DELETED")
	private int roleIsDeleted;

	
}
