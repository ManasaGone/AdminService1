package com.admin.response;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private int adminId;
	private String username;
	private String email;
	
	
	public JwtResponse(String token,  int adminId, String username, String email) {
		
		this.token = token;
		this.adminId = adminId;
		this.username = username;
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	 
}
