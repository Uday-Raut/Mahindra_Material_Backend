package com.ats.mahindrabattery.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ats.mahindrabattery.entity.MasterUserDetailsEntity;

public interface MasterUserDetailsService {

	public ResponseEntity<Object> createMasterUserDetails(MultipartFile file, String firstName, String lastName,
			String userTitle, String userName, String userPassword, String contactNumber, String emailId, String gender,
			String roleName) throws IOException;
	
	public List<MasterUserDetailsEntity> findAllMasterUserDetails();
	
	public MasterUserDetailsEntity findMasterUserDetailsByid(int id);
	
	public ResponseEntity<Object> updateMasterUserDetails(int id, MultipartFile file, String firstName,
			String lastName, String userTitle, String userName, String contactNumber, String emailId, String gender,
			String roleName) throws IOException;
	
	public ResponseEntity<Object> deleteMasterUserDetails(int id);
	
	public MasterUserDetailsEntity addMasterUserDetails(MultipartFile file, String firstName, String lastName,
			String userTitle, String userName, String userPassword, String contactNumber, String emailId, String gender,
			int roleId, String roleName, int userIsDeleted) throws IOException;
	
	
}
