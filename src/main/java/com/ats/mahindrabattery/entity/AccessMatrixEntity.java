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
@Table(name = "ats_wms_access_matrix_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessMatrixEntity {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ACCESS_MATRIX_ID")
	private int accessmMatrixId;
	
	
	@Column(name="FIELD")
	private String field;
	
	
	@Column(name="ADMIN")
	private int admin;
	
	@Column(name="OPERATOR")
	private int operator;
	
	@Column(name="SUPERVISOR")
	private int supervisor;
	
	
}
