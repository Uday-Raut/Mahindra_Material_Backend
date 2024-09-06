package com.ats.mahindrabattery.controller;

import java.util.Base64;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.mahindrabattery.entity.MasterUserDetailsEntity;
import com.ats.mahindrabattery.entity.OtpEntity;
import com.ats.mahindrabattery.repository.MasterUserDetailsRepository;
import com.ats.mahindrabattery.security.JwtUtil;
import com.ats.mahindrabattery.security.MyUserDetailsService;
import com.ats.mahindrabattery.service.EmailService;
import com.ats.mahindrabattery.service.TokenService;

@RestController
@RequestMapping("/login")
@CrossOrigin

public class LoginController {
 int myOtp=0;
 String email;
// String emailId;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	OtpEntity otpEntity = new OtpEntity();
	@Autowired
	MyUserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MasterUserDetailsRepository userManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;

	@Autowired
	private MasterUserDetailsRepository userDetailsRepository;
	 @Autowired
	 private TokenService tokenService;

//	@PostMapping("/authenticate")
//	public ResponseEntity<MasterUserDetailsEntity> createAuthenticationToken(
//			@RequestParam(value = "userName") String userName,
//			@RequestParam(value = "userPassword") String userPassword) throws Exception {
//		System.out.println("request username: " + userName);
//		List<MasterUserDetailsEntity> userList = userManager.findByuserNameAndUserIsDeleted(userName,0);
//		System.out.println("userlist: " + userList.size());
//		// System.out.println(passwordEncoder.encode(
//		// userList.get(0).getUserPassword()));
//		if (userList != null && userList.size() > 0) {
//
//			//if (passwordEncoder.matches(userPassword, userList.get(0).getUserPassword())) {
//				// if (true) {
//				System.out.println("inside if :" + userName);
//				final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
//				System.out.println(userDetails.getUsername());
//				final String jwt = jwtTokenUtil.generateToken(userDetails);
//
//				MasterUserDetailsEntity user = new MasterUserDetailsEntity();
//
//				user = userList.get(0);
//				user.setJwtToken(jwt);
//
//				user.setUserPhotoImageIn64Base(
//						"data:image/jpeg;base64," + Base64.getEncoder().encodeToString(userList.get(0).getUserImage()));
//				System.out.println(user.getFirstName());
//
//				return new ResponseEntity<MasterUserDetailsEntity>(user, HttpStatus.OK);
//				// return ResponseHandler.generateResponse("Login sucessfully", HttpStatus.OK,
//				// null);
//
////			} else {
////				// throw new Exception("Incorrect username or password");
////				// return ResponseHandler.generateResponse("Incorrect username or password",
////				// HttpStatus.BAD_REQUEST, null);
////				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
////			}
//		} else {
//			return new ResponseEntity<MasterUserDetailsEntity>(HttpStatus.NO_CONTENT);
//			// return ResponseHandler.generateResponse("Login sucessfully",
//			// HttpStatus.NO_CONTENT, null);
//		}
//
//	}
	
	
	
//	 @PostMapping("/authenticate")
//	    public ResponseEntity<?> createAuthenticationToken(
//	            @RequestParam(value = "userName") String userName,
//	            @RequestParam(value = "userPassword") String userPassword) throws Exception {
//	        System.out.println("request username: " + userName);
//	        List<MasterUserDetailsEntity> userList = userManager.findByuserNameAndUserIsDeleted(userName, 0);
//	        System.out.println("userlist: " + userList.size());
//
//	    //    if (userList != null && userList.size() > 0) {
//	            MasterUserDetailsEntity userEntity = userList.get(0);
//
//	             if (bCryptPasswordEncoder.matches(userPassword, userEntity.getUserPassword())) {
//	            System.out.println("inside if :" + userName);
//	            final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
//	            System.out.println(userDetails.getUsername());
//	            final String jwt = jwtTokenUtil.generateToken(userDetails);
//
//	            // Check if the user is already logged in on another machine
////	            String existingToken = tokenService.getToken(userName);
////	            if (!Objects.equals(existingToken, null)) {
////	                // Return a response indicating that the user is already logged in on another machine
////	                return new ResponseEntity<>("User is already logged in on another machine", HttpStatus.CONFLICT);
////	            }
//
//	            // Store the new session token
////	            tokenService.storeToken(userName, jwt);
//
//	            userEntity.setJwtToken(jwt);
//	            userEntity.setUserPhotoImageIn64Base(
//	                    "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(userEntity.getUserImage()));
//	            System.out.println(userEntity.getFirstName());
//
//	            return new ResponseEntity<>(userEntity, HttpStatus.OK);
//	            // } else {
//	            // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	            // }
//	        } else {
//	        	System.out.println("error........");
//	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	        }
//	    }
	 
		@PostMapping("/authenticate")
		public ResponseEntity<MasterUserDetailsEntity> createAuthenticationToken(
				@RequestParam(value = "userName") String userName,
				@RequestParam(value = "userPassword") String userPassword) throws Exception {
			System.out.println("request username: " + userName);
			List<MasterUserDetailsEntity> userList = userManager.findByuserNameAndUserIsDeleted(userName, 0);
			System.out.println("userlist: " + userList.size());
			// System.out.println(passwordEncoder.encode(
			// userList.get(0).getUserPassword()));
			if (userList != null && userList.size() > 0) {

			if (passwordEncoder.matches(userPassword, userList.get(0).getUserPassword())) {
				//	 if (true) {
					System.out.println("inside if :" + userName);
					final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
					System.out.println(userDetails.getUsername());
					final String jwt = jwtTokenUtil.generateToken(userDetails);

					MasterUserDetailsEntity user = new MasterUserDetailsEntity();

					user = userList.get(0);
					user.setJwtToken(jwt);

					user.setUserPhotoImageIn64Base(
							"data:image/jpeg;base64," + Base64.getEncoder().encodeToString(userList.get(0).getUserImage()));
					System.out.println(user.getFirstName());

					return new ResponseEntity<MasterUserDetailsEntity>(user, HttpStatus.OK);
					// return ResponseHandler.generateResponse("Login sucessfully", HttpStatus.OK,
					// null);

				} else {
					// throw new Exception("Incorrect username or password");
					// return ResponseHandler.generateResponse("Incorrect username or password",
					// HttpStatus.BAD_REQUEST, null);
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<MasterUserDetailsEntity>(HttpStatus.NO_CONTENT);
				// return ResponseHandler.generateResponse("Login sucessfully",
				// HttpStatus.NO_CONTENT, null);
			}

		}

//	 @PostMapping("/authenticate")
//		public ResponseEntity<MasterUserDetailsEntity> createAuthenticationToken(
//				@RequestParam(value = "userName") String userName,
//				@RequestParam(value = "userPassword") String userPassword) throws Exception {
//			System.out.println("request username: " + userName);
//			List<MasterUserDetailsEntity> userList = userManager.findByuserNameAndUserIsDeleted(userName,0);
//			System.out.println("userlist: " + userList.size());
//			// System.out.println(passwordEncoder.encode(
//			// userList.get(0).getUserPassword()));
//			if (userList != null && userList.size() > 0) {
//	 
//				//if (passwordEncoder.matches(userPassword, userList.get(0).getUserPassword())) {
//					// if (true) {
//					System.out.println("inside if :" + userName);
//					final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
//					System.out.println(userDetails.getUsername());
//					final String jwt = jwtTokenUtil.generateToken(userDetails);
//	 
//					MasterUserDetailsEntity user = new MasterUserDetailsEntity();
//	 
//					user = userList.get(0);
//					user.setJwtToken(jwt);
//	 
//					user.setUserPhotoImageIn64Base(
//							"data:image/jpeg;base64," + Base64.getEncoder().encodeToString(userList.get(0).getUserImage()));
//					System.out.println(user.getFirstName());
//	 
//					return new ResponseEntity<MasterUserDetailsEntity>(user, HttpStatus.OK);
//					// return ResponseHandler.generateResponse("Login sucessfully", HttpStatus.OK,
//					// null);
//	 
////				} else {
////					// throw new Exception("Incorrect username or password");
////					// return ResponseHandler.generateResponse("Incorrect username or password",
////					// HttpStatus.BAD_REQUEST, null);
////					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
////				}
//			} else {
//				return new ResponseEntity<MasterUserDetailsEntity>(HttpStatus.NO_CONTENT);
//				// return ResponseHandler.generateResponse("Login sucessfully",
//				// HttpStatus.NO_CONTENT, null);
//			}
//	 
//		}
//	 

	@PostMapping("/sendOtp/{emailId}")
	public ResponseEntity<?> sendOtp(@PathVariable String emailId, HttpSession session) {
		
		otpEntity.setEmail(emailId);
		Random random = new Random();
		int otp = random.nextInt(999999);
		
		otpEntity.setOtp(otp);
		String s = String.valueOf(otp);
		boolean sendEmail = emailService.sendEmail("OTP from user for verification", "OTP=" + otp, emailId);
 
		if (sendEmail) {
			//myOtp = otp;
			//email = emailId;
			session.setAttribute("myotp", otp);
			session.setAttribute("email", emailId);
		myOtp=otpEntity.getOtp();
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			session.setAttribute("message", "check your email id");
		
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
 
	}







	 
		@PostMapping("/verifyOtp/{otp}")
		public ResponseEntity<?> verifyOtp(@PathVariable int otp, HttpSession session) {
			// myOtp=otp;
			 // myOtp = (int) session.getAttribute("myotp");
			
			// String email = (String) session.getAttribute("email");
			String email2 = otpEntity.getEmail();
		
			if (myOtp == otp) {
				
				MasterUserDetailsEntity findByEmailId = userDetailsRepository.findByEmailId(email2);
				if (findByEmailId == null) {
					session.setAttribute("message", "email id does not exists");
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

				String s = String.valueOf(otp);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
			
				session.setAttribute("message", "wrong otp");
				 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}

		@PostMapping("/changePassword")
		public ResponseEntity<?> changePassword(@RequestParam("newPassword1") String newPassword1,
				@RequestParam("newPassword2") String newPassword2, HttpSession session) {
			// String email = (String) session.getAttribute("email");
			String email = otpEntity.getEmail();
			MasterUserDetailsEntity findByEmailId = userDetailsRepository.findByEmailId(email);
			String userName = findByEmailId.getUserName();
			List<MasterUserDetailsEntity> findByuserName = userDetailsRepository.findByuserName(userName);
			if (newPassword1.equals(newPassword2)) {
				findByuserName.get(0).setUserPassword(bCryptPasswordEncoder.encode(newPassword1));
				userDetailsRepository.save(findByuserName.get(0));
				
				return new ResponseEntity<>(HttpStatus.OK);
			}
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
}
