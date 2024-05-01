package com.rree.fsnotes.fsgateway.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rree.fsnotes.fsgateway.gateway.apiclient.FSApiClient;
import com.rree.fsnotes.fsgateway.model.AuthenticationUser;
import com.rree.fsnotes.fsgateway.model.UserResponse;

@Service
public class TestService {
	
	@Autowired
	private FSApiClient apiClient;

	public UserResponse getUserById(Integer id) {
		return apiClient.getUser(id);
	}

}
