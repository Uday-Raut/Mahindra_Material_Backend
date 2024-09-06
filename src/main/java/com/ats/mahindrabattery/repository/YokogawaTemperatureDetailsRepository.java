package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.mahindrabattery.entity.YokogawaTemperatureDetailsEntity;



public interface YokogawaTemperatureDetailsRepository extends JpaRepository<YokogawaTemperatureDetailsEntity,Integer> {

	

	YokogawaTemperatureDetailsEntity findByCreatedDateTimeBetween(String string, String string2);

}
