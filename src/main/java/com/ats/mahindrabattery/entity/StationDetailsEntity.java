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
@Table(name = "ats_wms_station_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationDetailsEntity {

      
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

@Column(name = "WMS_STATION_ID")
private int wmsStationId;

@Column(name = "WMS_STATION_NAME")
private String wmsStationName;

@Column(name = "WMS_STATION_DESC")
private String wmsStationDesc;

@Column(name = "PLC_TAG_NAME")
private String plcTagName;

@Column(name = "PLC_TAG_TYPE")
private String plcTagType;

@Column(name = "CURRENT_VALUE")
private String currentValue;

@Column(name = "WMS_TRANSFER_ORDER_ID")
private String wmsTransferOrderId;

@Column(name = "CC_AKNWOLEDGEMENT")
private int ccAcknwoledgement;

@Column(name = "CDATETIME")
private String cdatetime;


}
