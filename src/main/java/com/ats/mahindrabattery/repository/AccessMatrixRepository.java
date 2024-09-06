package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ats.mahindrabattery.entity.AccessMatrixEntity;
@Transactional
public interface AccessMatrixRepository extends JpaRepository<AccessMatrixEntity, Integer> {

	@Modifying(clearAutomatically = true)
	@Query("update AccessMatrixEntity a set a.admin =:admin,a.operator =:operator,a.supervisor =:supervisor where a.id =:id")
	void update(int admin, int operator, int supervisor,int id);

	
}
