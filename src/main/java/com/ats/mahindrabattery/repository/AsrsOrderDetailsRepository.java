package com.ats.mahindrabattery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.mahindrabattery.entity.AsrsOrderDetailsEntity;


@Repository
public interface AsrsOrderDetailsRepository  extends JpaRepository <AsrsOrderDetailsEntity,Integer>{

	AsrsOrderDetailsEntity findBycdatetimeContaining(String currentDateTime);

}
