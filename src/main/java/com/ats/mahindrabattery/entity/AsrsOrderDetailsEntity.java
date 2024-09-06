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
@Table(name="ats_wms_asrs_order_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsrsOrderDetailsEntity {
	

	@Id
	@GeneratedValue(strategy = 	GenerationType.IDENTITY)
	@Column(name = "ORDER_ID")
	private int orderId;
	
	@Column(name ="TOTAL_ORDERS")
	private int totalOrder;
	
	@Column(name = "EXECUTED")
	private int executed;
	
	@Column(name  = "REMAINING")
	private int remaining;
	
	@Column(name = "PERCENTAGE")
	private float percentage;
	
	@Column(name = "CDATETIME")
	private String cdatetime;
	
	@Column(name = "ASRS_ORDER_IS_DELETED")
	private int asrsOrderIsDeleted;


}
