package com.rree.fsnotes.fsgateway.gateway.apiclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rree.fsnotes.fsgateway.model.AuthenticationUser;
import com.rree.fsnotes.fsgateway.model.UserResponse;

@FeignClient(value = "FS-PERSISTANCE")
public interface FSApiClient {
	@GetMapping(value = "/user/{id}")
	UserResponse getUser(@PathVariable("id") Integer id);
}
