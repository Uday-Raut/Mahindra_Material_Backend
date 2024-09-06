package com.ats.mahindrabattery.service;

import java.io.IOException;
import java.util.List;

import com.ats.mahindrabattery.entity.QRCodeDetailsEntity;
import com.google.zxing.WriterException;

public interface QRCodeDetailsService {

	public QRCodeDetailsEntity addQRCodeDetails(QRCodeDetailsEntity qrCodeDetailsEntity) throws WriterException, IOException;
	
	public List<QRCodeDetailsEntity> findAllQRCodeDetails() ;
	
	
}
