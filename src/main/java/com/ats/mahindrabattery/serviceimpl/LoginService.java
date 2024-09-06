package com.ats.mahindrabattery.serviceimpl;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.MasterUserDetailsEntity;
import com.ats.mahindrabattery.repository.MasterUserDetailsRepository;
import com.ats.mahindrabattery.security.JwtUtil;
import com.ats.mahindrabattery.security.MyUserDetailsService;

@Service
public class LoginService {

	@Autowired
	MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MasterUserDetailsRepository userManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public ResponseEntity<MasterUserDetailsEntity> createAuthenticationToken(String userName, String userPassword)
			throws Exception {
		System.out.println(userName);
		List<MasterUserDetailsEntity> userList = userManager.findByuserName(userName);
		System.out.println(passwordEncoder.encode(userList.get(0).getUserPassword()));
		if (userList != null && userList.size() > 0) {

			if (passwordEncoder.matches(userPassword, userList.get(0).getUserPassword())) {
				 //if (true) {

				final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

				final String jwt = jwtTokenUtil.generateToken(userDetails);

				MasterUserDetailsEntity user = new MasterUserDetailsEntity();

				user = userList.get(0);
				user.setJwtToken(jwt);

				user.setUserPhotoImageIn64Base(
						"data:image/jpeg;base64," + Base64.getEncoder().encodeToString(userList.get(0).getUserImage()));
				System.out.println(user.getFirstName());

				return new ResponseEntity<MasterUserDetailsEntity>(user, HttpStatus.OK);
				

			} else {
				throw new Exception("Incorrect username or password");
			}
		} else {
			return new ResponseEntity<MasterUserDetailsEntity>(HttpStatus.NO_CONTENT);
		}
		

	}

}
