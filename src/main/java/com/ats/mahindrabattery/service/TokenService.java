package com.ats.mahindrabattery.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class TokenService {
	 private ConcurrentHashMap<String, String> activeSessions = new ConcurrentHashMap<>();

	    // Store the token
	    public void storeToken(String userName, String token) {
	        activeSessions.put(userName, token);
	    }

	    // Retrieve the token
	    public String getToken(String userName) {
	        return activeSessions.get(userName);
	    }

	    // Invalidate the token
	    public void invalidateToken(String userName) {
	        activeSessions.remove(userName);
	    }
}
