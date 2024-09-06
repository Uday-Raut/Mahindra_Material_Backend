package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.mahindrabattery.entity.MasterProductVariantDetailsEntity;

public interface MasterProductVariantDetailsRepository
		extends JpaRepository<MasterProductVariantDetailsEntity, Integer> {

	public List<MasterProductVariantDetailsEntity> findByproductVariantIsDeleted(int ProductVariantIsDeleted);

	public List<MasterProductVariantDetailsEntity> findByproductVariantCode(String productVariantCode);

	public List<MasterProductVariantDetailsEntity> findByProductVariantCode(String productVariantCode);

	public List<MasterProductVariantDetailsEntity> findByProductVariantId(int productVariantId);

	public MasterProductVariantDetailsEntity findByProductVariantname(String productVariantName);

	public Page<MasterProductVariantDetailsEntity> findByproductVariantIsDeleted(Pageable pageable, int i);

	public List<MasterProductVariantDetailsEntity> findByproductVariantCodeAndProductVariantIsActive(
			String productVariantCode, int i);

	public List<MasterProductVariantDetailsEntity> findByproductVariantCodeAndProductVariantIsActiveAndProductVariantIsDeleted(
			String productVariantCode, int i, int j);
}
