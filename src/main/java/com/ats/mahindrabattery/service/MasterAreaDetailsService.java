package com.ats.mahindrabattery.service;

import java.util.List;

import com.ats.mahindrabattery.entity.MasterAreaDetailsEntity;

public interface MasterAreaDetailsService  {

	
	public List<MasterAreaDetailsEntity> findAll();
	
	public List<MasterAreaDetailsEntity> findByAreaId(int areaId) ;
	
}
