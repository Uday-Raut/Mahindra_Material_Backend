package com.ats.mahindrabattery.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.mahindrabattery.entity.MasterEquipmentDetailsEntity;
import com.ats.mahindrabattery.entity.MasterStackerDetailsEntity;

@Repository
public interface MasterStackerDetailsRepository extends JpaRepository<MasterStackerDetailsEntity,Integer> {
	
	List<MasterStackerDetailsEntity>  findByStackerIsDeleted(int stackerIsDeleted);

	

}
