package com.ats.mahindrabattery.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.QRCodeDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.QRCodeDetailsServicempl;
import com.google.zxing.WriterException;

@CrossOrigin
@RestController
@RequestMapping("/qrCodeDetails")
public class QRCodeDetailsController {

	@Autowired
	private QRCodeDetailsServicempl QRCodeDetailsServiceInstance;

	@GetMapping("/fetchAllQRCodeDetails")
	public List<QRCodeDetailsEntity> fetchAllQRCodeDetails() {
		return QRCodeDetailsServiceInstance.findAllQRCodeDetails();
	}

	@PostMapping("/addQRCodeDetails")
	public QRCodeDetailsEntity addQRCodeDetails(@RequestBody QRCodeDetailsEntity qrCodeDetailsEntity)
			throws WriterException, IOException {
		QRCodeDetailsServiceInstance.addQRCodeDetails(qrCodeDetailsEntity);
		return qrCodeDetailsEntity;

	}

}
