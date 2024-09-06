package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.mahindrabattery.entity.MasterProductDetailsEntity;
import com.ats.mahindrabattery.entity.MasterProductVariantDetailsEntity;
 

public interface MasterProductDetailsRepository extends JpaRepository<MasterProductDetailsEntity, Integer>{
	
	List<MasterProductDetailsEntity> findByproductName(String productName);
	
	public List<MasterProductDetailsEntity> findByProductId(int productId);
	public List<MasterProductDetailsEntity> findByproductIsDeleted(int ProductIsDeleted);

}
