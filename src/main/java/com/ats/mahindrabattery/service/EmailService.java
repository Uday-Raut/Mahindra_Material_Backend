package com.ats.mahindrabattery.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

	public boolean sendEmail(String subject,String message,String to); 
		
}
