package com.ats.mahindrabattery.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ats_wms_master_reason_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterReasonDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "REASON_ID")
	private int reasonId;

	
	@Column(name = "OPERATOR_ACTIONS")
	private String operatorAction;


	@Column(name = "REASON")
	private String reason;

	
	@Column(name = "CDATETIME")
	private String cdatetime;
	

	@Column(name = "REASON_IS_DELETED")
	private int reasonIsDeleted;

	
	

}
