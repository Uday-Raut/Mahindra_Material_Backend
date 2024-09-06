package com.ats.mahindrabattery.service;

import java.util.List;

import com.ats.mahindrabattery.entity.StationDetailsEntity;

public interface StationDetailsService {

	public List<StationDetailsEntity> findAllStationDetailsByWmsStationId(int wmsStationId);
}
