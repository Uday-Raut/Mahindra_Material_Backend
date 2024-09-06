package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.mahindrabattery.entity.MasterReasonDetailsEntity;
 

public interface MasterReasonDetailsRepository extends JpaRepository<MasterReasonDetailsEntity, Integer>{

	List<MasterReasonDetailsEntity> findByReasonIsDeleted(int i);
	public List<MasterReasonDetailsEntity> findByReasonId(int reasonId);

}
