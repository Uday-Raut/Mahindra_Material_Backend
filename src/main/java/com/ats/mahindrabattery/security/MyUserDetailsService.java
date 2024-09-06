package com.ats.mahindrabattery.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ats.mahindrabattery.entity.MasterUserDetailsEntity;
import com.ats.mahindrabattery.repository.MasterUserDetailsRepository;




@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	MasterUserDetailsRepository userDao;

	@Override
	public UserDetails loadUserByUsername(String username) {
		MasterUserDetailsEntity user = null;
		try {
//			System.out.println("hello buddy: "+username);
			user =  userDao.findByuserName(username).get(0);
//			System.out.println("2");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new MyUserPrincipal(user);
	}

}

