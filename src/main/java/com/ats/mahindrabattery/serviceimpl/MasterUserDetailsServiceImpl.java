package com.ats.mahindrabattery.serviceimpl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ats.mahindrabattery.entity.AuditTrailDetailsEntity;
import com.ats.mahindrabattery.entity.MasterRoleDetailsEntity;
import com.ats.mahindrabattery.entity.MasterUserDetailsEntity;
import com.ats.mahindrabattery.exception.ResourceNotFoundException;
import com.ats.mahindrabattery.repository.AuditTrailDetailsRepository;
import com.ats.mahindrabattery.repository.MasterRoleDetailsRepository;
import com.ats.mahindrabattery.repository.MasterUserDetailsRepository;
import com.ats.mahindrabattery.response.ResponseHandler;
import com.ats.mahindrabattery.service.MasterUserDetailsService;

@Service
//@CacheConfig(cacheNames = {"allMasterUserDetailsCache", "masterUserCache"})
public class MasterUserDetailsServiceImpl implements MasterUserDetailsService {

	@Autowired
	private MasterUserDetailsRepository masterUserDetailsRepository;

	@Autowired
	private MasterRoleDetailsRepository masterRoleDetailsRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuditTrailDetailsRepository auditTrailDetailsRepository;

	public ResponseEntity<Object> createMasterUserDetails(MultipartFile file, String firstName, String lastName,
			String userTitle, String userName, String userPassword, String contactNumber, String emailId, String gender,
			String roleName) throws IOException {

		try {
			List<MasterUserDetailsEntity> userList = masterUserDetailsRepository.findByuserName(userName);
			if (userList.size() > 0) {
				System.out.println("usename exist");
				return ResponseHandler.generateResponse("usename already exist", HttpStatus.INTERNAL_SERVER_ERROR,
						null);
			}
			List<MasterUserDetailsEntity> findByEmailId = masterUserDetailsRepository.getByEmailId(emailId);
			if (findByEmailId.size() > 0) {
				System.out.println("Email id exist");
				return ResponseHandler.generateResponse("Email id already exist", HttpStatus.INTERNAL_SERVER_ERROR,
						null);
			}
			
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
			String currentDate = ft.format(dNow);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String name = authentication.getName();
			System.out.println("authentication:" + name);

			MasterRoleDetailsEntity masterRoleDetailsInst = new MasterRoleDetailsEntity();
			masterRoleDetailsInst = masterRoleDetailsRepository.findByRoleNameAndRoleIsDeleted(roleName, 0);

			MasterUserDetailsEntity masterUserDetailsEntity = new MasterUserDetailsEntity();
			masterUserDetailsEntity.setFirstName(firstName);
			masterUserDetailsEntity.setLastName(lastName);
			masterUserDetailsEntity.setUserTitle(userTitle);
			masterUserDetailsEntity.setUserName(userName);
			masterUserDetailsEntity.setContactNumber(contactNumber);

			masterUserDetailsEntity.setEmailId(emailId);
			masterUserDetailsEntity.setGender(gender);
			masterUserDetailsEntity.setRoleId(masterRoleDetailsInst.getRoleId());
			masterUserDetailsEntity.setRoleName(roleName);
			masterUserDetailsEntity.setUserIsDeleted(0);
			// masterUserDetailsEntity.setUserPassword(userPassword);
			// System.out.println(file.getOriginalFilename());
			String password = passwordEncoder.encode(userPassword);

			masterUserDetailsEntity.setUserPassword(password);
			if (file != null && file.getSize() < 1048576) {
				byte[] userImage = file.getBytes();
				masterUserDetailsEntity.setUserImage(userImage);
				masterUserDetailsRepository.save(masterUserDetailsEntity);
				
				AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
				auditTrailDetailsEntity.setOperatorActions(
						"User added by " + name );
				auditTrailDetailsEntity.setField("User added");
				auditTrailDetailsEntity.setAfterValue(0);
				auditTrailDetailsEntity.setBeforeValue(0);
				auditTrailDetailsEntity.setReason("User added");
//				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//				String name = authentication.getName();
				System.out.println(" name :: " + name);
				auditTrailDetailsEntity.setUsername(name);
				auditTrailDetailsEntity.setDatetimeC(currentDate);
				auditTrailDetailsRepository.save(auditTrailDetailsEntity);
				
				return ResponseHandler.generateResponse("User entry updated sucessfully", HttpStatus.OK,
						masterUserDetailsEntity);

			} else {
				
				return ResponseHandler.generateResponse("Image Maximum upload size exceeded", HttpStatus.INTERNAL_SERVER_ERROR,
						null);
			}

		} catch (Exception ex) {

			return ResponseHandler.generateResponse("Error occurred at server side", HttpStatus.INTERNAL_SERVER_ERROR,
					null);
		}

	}

//	@Cacheable(value="allMasterUserDetailsCache")
	public List<MasterUserDetailsEntity> findAllMasterUserDetails() {

		List<MasterUserDetailsEntity> masterUserDetails = masterUserDetailsRepository.findByuserIsDeleted(0);

		for (int i = 0; i < masterUserDetails.size(); i++) {

			masterUserDetails.get(i).setUserPhotoImageIn64Base("data:image/jpeg;base64,"
					+ Base64.getEncoder().encodeToString(masterUserDetails.get(i).getUserImage()));
		}

		System.out.println("called by findAllMasterUserDetails() from DB");

		return masterUserDetails;
	}

//	 @Cacheable(key = "#id")
	public MasterUserDetailsEntity findMasterUserDetailsByid(int id) {

		MasterUserDetailsEntity masterUserDetails = masterUserDetailsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("MasterUserDetailsEntity", "Id", id));
		System.out.println("called by findAllMasterUserDetails() from DB");
		return masterUserDetails;
	}

	public ResponseEntity<Object> updateMasterUserDetails(int id, MultipartFile file, String firstName, String lastName,
			String userTitle, String userName, String contactNumber, String emailId, String gender, String roleName)
			throws IOException {
		try {
			MasterUserDetailsEntity masterUserDetailsInst = new MasterUserDetailsEntity();
			masterUserDetailsInst = masterUserDetailsRepository.findByUserId(id);

			MasterRoleDetailsEntity masterRoleDetailsInst = new MasterRoleDetailsEntity();
			masterRoleDetailsInst = masterRoleDetailsRepository.findByRoleNameAndRoleIsDeleted(roleName, 0);

			MasterUserDetailsEntity masterUserDetails = masterUserDetailsRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("MasterUserDetailsEntity", "Id", id));

			// List<MasterUserDetailsEntity> userList =
			// masterUserDetailsRepository.findByuserName(userName);

			List<MasterUserDetailsEntity> findByUserNameAndUserIdNot = masterUserDetailsRepository
					.findByUserNameAndUserIdNot(userName, id);
			if (findByUserNameAndUserIdNot.size() > 0) {
				System.out.println("usename exist");
				return ResponseHandler.generateResponse("usename already exist", HttpStatus.INTERNAL_SERVER_ERROR,
						null);
			}
			
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
			String currentDate = ft.format(dNow);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String name = authentication.getName();
			System.out.println("authentication:" + name);

			// MasterUserDetailsEntity findByEmailId =
			// masterUserDetailsRepository.findByEmailId(emailId);
//			if(userList.size()>0) {
//				System.out.println("usename exist");
//				return ResponseHandler.generateResponse("usename already exist", HttpStatus.INTERNAL_SERVER_ERROR,
//						null);
//			}
			// MasterUserDetailsEntity findByEmailId =
			// masterUserDetailsRepository.findByEmailId(emailId);
			// List<MasterUserDetailsEntity> findByEmailId =
			// masterUserDetailsRepository.getByEmailId(emailId);

			List<MasterUserDetailsEntity> findByEmailIdAndUserIdNot = masterUserDetailsRepository
					.findByEmailIdAndUserIdNotAndUserIsDeleted(emailId, id,0);

			if (findByEmailIdAndUserIdNot.size() > 0) {
				System.out.println("Email id exist");
				return ResponseHandler.generateResponse("Email id already exist", HttpStatus.INTERNAL_SERVER_ERROR,
						null);
			}

			masterUserDetails.setFirstName(firstName);
			masterUserDetails.setLastName(lastName);
			masterUserDetails.setUserTitle(userTitle);
			masterUserDetails.setUserName(userName);
			masterUserDetails.setUserPassword(masterUserDetailsInst.getUserPassword());
			masterUserDetails.setContactNumber(contactNumber);

			masterUserDetails.setEmailId(emailId);
			System.out.println("emailId" + masterUserDetails.getEmailId());
			masterUserDetails.setGender(gender);
			// masterUserDetails.setUserImage(masterUserDetailsEntity.getUserImage());
			masterUserDetails.setRoleId(masterRoleDetailsInst.getRoleId());
			masterUserDetails.setRoleName(roleName);
			masterUserDetails.setCDatetime(LocalDateTime.now());
//			masterUserDetails.setUserIsDeleted(masterUserDetailsEntity.getUserIsDeleted());

			if (file != null ) {
				byte[] userImage = file.getBytes();
				masterUserDetails.setUserImage(userImage);
			}
				masterUserDetailsRepository.save(masterUserDetails);
				
				
				AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
				auditTrailDetailsEntity.setOperatorActions(
						"User updated by " + name );
				auditTrailDetailsEntity.setField("User updated");
				auditTrailDetailsEntity.setAfterValue(0);
				auditTrailDetailsEntity.setBeforeValue(0);
				auditTrailDetailsEntity.setReason("User added");
//				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//				String name = authentication.getName();
				System.out.println(" name :: " + name);
				auditTrailDetailsEntity.setUsername(name);
				auditTrailDetailsEntity.setDatetimeC(currentDate);
				auditTrailDetailsRepository.save(auditTrailDetailsEntity);
				
				
				return ResponseHandler.generateResponse("User entry updated sucessfully", HttpStatus.OK,
						masterUserDetails);

//			} else {
//				System.out.println("1");
//				return ResponseHandler.generateResponse("User Image Size exceeds 1MB",
//						HttpStatus.INTERNAL_SERVER_ERROR, null);
//
//			}

		} catch (Exception ex) {
			// System.out.println(ex.getMessage());
			return ResponseHandler.generateResponse("Error occurred at server side", HttpStatus.INTERNAL_SERVER_ERROR,
					null);
		}

	}

	public ResponseEntity<Object> deleteMasterUserDetails(int id) {
		try {
			MasterUserDetailsEntity masterUserDetails = masterUserDetailsRepository.findByUserId(id);

			masterUserDetails.setCDatetime(LocalDateTime.now());
			masterUserDetails.setUserIsDeleted(1);
			masterUserDetailsRepository.save(masterUserDetails);
			
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
			String currentDate = ft.format(dNow);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String name = authentication.getName();
			System.out.println("authentication:" + name);
			
			AuditTrailDetailsEntity auditTrailDetailsEntity = new AuditTrailDetailsEntity();
			auditTrailDetailsEntity.setOperatorActions(
					"User deleted by " + name );
			auditTrailDetailsEntity.setField("User deleted");
			auditTrailDetailsEntity.setAfterValue(0);
			auditTrailDetailsEntity.setBeforeValue(0);
			auditTrailDetailsEntity.setReason("User deleted");
//			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//			String name = authentication.getName();
			System.out.println(" name :: " + name);
			auditTrailDetailsEntity.setUsername(name);
			auditTrailDetailsEntity.setDatetimeC(currentDate);
			auditTrailDetailsRepository.save(auditTrailDetailsEntity);
			
			return ResponseHandler.generateResponse("User entry deleted sucessfully", HttpStatus.OK, null);
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Error occurred at server side", HttpStatus.INTERNAL_SERVER_ERROR,
					null);
		}

	}

	public MasterUserDetailsEntity addMasterUserDetails(MultipartFile file, String firstName, String lastName,
			String userTitle, String userName, String userPassword, String contactNumber, String emailId, String gender,
			int roleId, String roleName, int userIsDeleted) throws IOException {
		MasterUserDetailsEntity masterUserDetailsEntity = new MasterUserDetailsEntity();
		List<MasterUserDetailsEntity> userList = masterUserDetailsRepository
				.findByuserName(masterUserDetailsEntity.getUserName());

		masterUserDetailsEntity.setFirstName(firstName);
		masterUserDetailsEntity.setLastName(lastName);
		masterUserDetailsEntity.setUserTitle(userTitle);
		masterUserDetailsEntity.setUserName(userName);
		masterUserDetailsEntity.setContactNumber(contactNumber);
		masterUserDetailsEntity.setEmailId(emailId);
		masterUserDetailsEntity.setGender(gender);
		masterUserDetailsEntity.setRoleId(roleId);
		masterUserDetailsEntity.setRoleName(roleName);
		masterUserDetailsEntity.setUserIsDeleted(0);
		masterUserDetailsEntity.setUserPassword(userPassword);
//		String password = passwordEncoder.encode(userPassword);
		byte[] userImage = file.getBytes();
		masterUserDetailsEntity.setUserImage(userImage);

		MasterUserDetailsEntity masterUserDetails = masterUserDetailsRepository.save(masterUserDetailsEntity);
		// System.out.println("first name= " + masterUserDetailsEntity.getFirstName());
		return masterUserDetails;
	}
}
