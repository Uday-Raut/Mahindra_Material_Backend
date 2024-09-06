package com.ats.mahindrabattery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.mahindrabattery.entity.MasterRoleDetailsEntity;
 

public interface MasterRoleDetailsRepository extends JpaRepository<MasterRoleDetailsEntity, Integer>{

	public MasterRoleDetailsEntity findByRoleNameAndRoleIsDeleted(String roleName, int i);

}
