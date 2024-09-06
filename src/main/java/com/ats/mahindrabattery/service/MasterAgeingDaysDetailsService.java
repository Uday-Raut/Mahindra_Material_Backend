package com.ats.mahindrabattery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.MasterAgeingDaysDetailsEntity;

@Service
public interface MasterAgeingDaysDetailsService {

	public List<MasterAgeingDaysDetailsEntity> findAllAgeingDaysDetais();
}
