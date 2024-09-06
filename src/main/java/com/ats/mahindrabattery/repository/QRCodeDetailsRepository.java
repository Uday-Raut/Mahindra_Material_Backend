package com.ats.mahindrabattery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.mahindrabattery.entity.QRCodeDetailsEntity;
 

public interface QRCodeDetailsRepository extends JpaRepository<QRCodeDetailsEntity,Integer> {
	List<QRCodeDetailsEntity> findByQrCodeData(String qrCodeData);

	//List<QRCodeDetailsEntity> findByQrCodeData(String qrCodeData);

}
