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
@Table(name = "ats_wms_master_station_tag_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterStationTagDetailsEntity {
	
	 
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		
		@Column(name = "STATION_TAG_DETAILS_ID")
		private int stationTagDetailsId;
		
		@Column(name = "STATION_ID")
		private int stationId;
		
		@Column(name = "STATION_NAME")
		private String stationName;
		
		@Column(name = "PLC_TAG_NAME")
		private String plcTagName;
		
		@Column(name = "PLC_TAG_TYPE")
		private String plcTagType;
		
		@Column(name = "CURRENT_VALUE")
		private String currentValue;
		
		@Column(name = "WMS_TRANSFER_ORDER_ID")
		private String wmsTransferOrderId;
		
		@Column(name = "CC_AKNOWLEDGEMENT")
		private int ccAknowledgement;
		
		@Column(name = "CDATETIME")
		private String cDateTime;

		

		
}
