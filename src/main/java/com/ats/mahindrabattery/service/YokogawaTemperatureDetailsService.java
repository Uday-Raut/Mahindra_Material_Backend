package com.ats.mahindrabattery.service;

import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.YokogawaTemperatureDetailsEntity;

@Service
public interface YokogawaTemperatureDetailsService {
	
	public YokogawaTemperatureDetailsEntity getTemperatureDetailsOfCurrentDate();
}
