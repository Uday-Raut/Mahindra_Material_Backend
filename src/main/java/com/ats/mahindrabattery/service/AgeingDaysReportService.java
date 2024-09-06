package com.ats.mahindrabattery.service;

import java.util.List;

import com.ats.mahindrabattery.entity.AgeingDaysReportEntity;
import com.ats.mahindrabattery.entity.CurrentPalletStockDetailsEntity;

public interface AgeingDaysReportService {

	public List<CurrentPalletStockDetailsEntity> findAgeingDays();
	
	public List<AgeingDaysReportEntity> findInventoryAgeingReport();
}
