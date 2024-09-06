package com.ats.mahindrabattery.serviceimpl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.MasterUserDetailsEntity;
import com.ats.mahindrabattery.repository.MasterUserDetailsRepository;
import com.ats.mahindrabattery.service.EmailService;

import java.util.List;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	 MasterUserDetailsRepository masterUserDetailsRepository;
//	@Override
//	public boolean sendEmail(String subject, String message, String to) {
//
//		boolean f = false;
//
//		String from = "udayraut0202@gmail.com";
//
//		String host = "smtp.gmail.com";
//
//		Properties properties = System.getProperties();
//		System.out.println("properties::" + properties);
//
//		properties.put("mail.smtp.host", host);
//		properties.put("mail.smtp.port", "465");
//		properties.put("mail.smtp.ssl.enable", "true");
//		Session session = Session.getInstance(properties, new Authenticator() {
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("udayraut0202@gmail.com", "Udayraut0202@");
//			}
//		});
//		session.setDebug(true);
//
//		MimeMessage m = new MimeMessage(session);
//		try {
//			m.setFrom(from);
//			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//			m.setSubject(subject);
//			m.setText(message);
//			Transport.send(m);
//			System.out.println("sent success");
//			f = true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return f;
//	}

	@Override
	public boolean sendEmail(String subject, String message, String to) {
	    boolean isSent = false;
	    String from = "udayraut0202@gmail.com";
	    String host = "smtp.gmail.com";
	    String username = "udayraut0202@gmail.com";
	    String password = "oonv ojbg twjd eqsm"; 

	    Properties properties = new Properties();
	    properties.put("mail.smtp.host", host);
	    properties.put("mail.smtp.port", "587");
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");

	    Session session = Session.getInstance(properties, new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
	    });
	    session.setDebug(true);

	    try {
	    	//List<MasterUserDetailsEntity> findAll = masterUserDetailsRepository.findAll();
	    	 MasterUserDetailsEntity findByEmailId = masterUserDetailsRepository.findByEmailIdAndUserIsDeleted(to,0);
		        if(findByEmailId!=null) {
	        MimeMessage mimeMessage = new MimeMessage(session);
	        mimeMessage.setFrom(new InternetAddress(from));
	       
	        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	        mimeMessage.setSubject(subject);
	      //  mimeMessage.setText(message);
	        mimeMessage.setContent(message,"text/html");

	        Transport.send(mimeMessage);
	      
	        isSent = true;
		        }
	    } catch (MessagingException e) {
	        e.printStackTrace();
	        // Handle the exception here, either log it or perform appropriate action
	    }
	    return isSent;
	}

}
