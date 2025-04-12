package com.example.CitizenManagement.dto;

public class LoginResponse {
	
	private final String token;
	
	private  final String username;
	
	private final String fullname;
	
	private final String authority;

	public LoginResponse(String token, String username, String fullname, String authority) {
		super();
		this.token = token;
		this.username = username;
		this.fullname = fullname;
		this.authority = authority;
	}

	public String getToken() {
		return token;
	}

	public String getUsername() {
		return username;
	}

	public String getFullname() {
		return fullname;
	}

	public String getAuthority() {
		return authority;
	}
	
	
}
