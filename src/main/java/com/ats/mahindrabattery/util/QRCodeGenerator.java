package com.ats.mahindrabattery.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ats.mahindrabattery.entity.QRCodeDetailsEntity;
import com.ats.mahindrabattery.repository.QRCodeDetailsRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;



@Component
public class QRCodeGenerator {
	@Autowired
	private QRCodeDetailsRepository qrCodeDetailsRepository;

	public void generateQRCode(QRCodeDetailsEntity qrCodeDetailsEntity) throws WriterException, IOException {

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy" + " " + "HH-mm-ss");
		String date = ft.format(dNow);

		BitMatrix matrix = new MultiFormatWriter().encode("qrCodeData=" + qrCodeDetailsEntity.getQrCodeData() + "\n"
				+ "isQRCodeGenerated=" + qrCodeDetailsEntity.getIsQRCodeGenerated() + "\n", BarcodeFormat.QR_CODE, 400,
				400);
		String qrCodePath = "D:\\QRCode\\";
		System.out.println("DateTime=" + date);
		String qrCodeName = qrCodePath + date + "-qrcode.png";
		Path path = FileSystems.getDefault().getPath(qrCodeName);
		MatrixToImageWriter.writeToPath(matrix, "PNG", path);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(matrix, "png", byteArrayOutputStream);
		String image = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
		byte[] byteImage = image.getBytes();
		qrCodeDetailsEntity.setQrCode(byteImage);
		System.out.println("qrCodeEntity=" + qrCodeDetailsEntity.toString());
		qrCodeDetailsRepository.save(qrCodeDetailsEntity);

	}

}
