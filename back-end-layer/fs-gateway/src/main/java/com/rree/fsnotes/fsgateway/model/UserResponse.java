package com.rree.fsnotes.fsgateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserResponse {
	private String firstName;
	private String lastName;
	private String email;
	private Integer id;
}
