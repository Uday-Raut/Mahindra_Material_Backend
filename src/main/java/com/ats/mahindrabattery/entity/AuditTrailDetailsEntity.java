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
@Table(name = "ats_wms_audit_trail_report")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditTrailDetailsEntity {

	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AUDIT_TRAIL_ID")
	private int auditId;

	
	@Column(name = "OPERATORS_ACTION")
	private String operatorActions;

	
	@Column(name = "FIELD")
	private String field;

	
	@Column(name = "REASON")
	private String reason;

	
	@Column(name = "BEFORE_VALUE")
	private int beforeValue;

	
	@Column(name = "AFTER_VALUE")
	private int afterValue;
	
	@Column(name = "USER_ID")
	private int userId;

	
	@Column(name = "USER_NAME")
	private String username;

	
	@Column(name = "CDATETIME")
	private String datetimeC;


}
