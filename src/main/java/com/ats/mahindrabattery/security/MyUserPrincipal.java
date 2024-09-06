package com.ats.mahindrabattery.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ats.mahindrabattery.entity.MasterUserDetailsEntity;


public class MyUserPrincipal implements UserDetails {

	private static final long serialVersionUID = 8649268605696478570L;

	private MasterUserDetailsEntity user = new MasterUserDetailsEntity();

	public MyUserPrincipal(MasterUserDetailsEntity user) {
		this.setUser(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return this.user.getUserPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public MasterUserDetailsEntity getUser() {
		return user;
	}

	public void setUser(MasterUserDetailsEntity user) {
		this.user = user;
	}

}
