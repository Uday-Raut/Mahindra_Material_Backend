package com.ats.mahindrabattery.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.AsrsOrderDetailsEntity;
import com.ats.mahindrabattery.entity.DashboardDetailsEntity;
import com.ats.mahindrabattery.repository.AsrsOrderDetailsRepository;
import com.ats.mahindrabattery.service.AsrsOrderDetailService;


@Service

public class AsrsOrderDetailsServiceImpl implements AsrsOrderDetailService {

	@Autowired
	AsrsOrderDetailsRepository asrsOrderDetailsRepositoryInstance;

     @Cacheable
	public List<AsrsOrderDetailsEntity> findAllAsrsOrderDetails() {
		try {
			return asrsOrderDetailsRepositoryInstance.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	

	public AsrsOrderDetailsEntity fetchCurrentDayAsrsOrderDetails() {
		try {
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String currentDateTime = dateFormat.format(date);
		
			AsrsOrderDetailsEntity asrsOrderIntance = asrsOrderDetailsRepositoryInstance
					.findBycdatetimeContaining(currentDateTime);
			if (asrsOrderIntance == null) {
				
				asrsOrderIntance = new AsrsOrderDetailsEntity(0, 0, 0, 0, 0, "NA", 0);
			}
			return asrsOrderIntance;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
