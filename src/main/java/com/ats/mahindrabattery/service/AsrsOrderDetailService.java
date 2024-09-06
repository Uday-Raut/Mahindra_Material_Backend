package com.ats.mahindrabattery.service;

import java.util.List;

import com.ats.mahindrabattery.entity.AsrsOrderDetailsEntity;

public interface AsrsOrderDetailService {
	public List<AsrsOrderDetailsEntity> findAllAsrsOrderDetails();
	public AsrsOrderDetailsEntity fetchCurrentDayAsrsOrderDetails();
		
	
}
