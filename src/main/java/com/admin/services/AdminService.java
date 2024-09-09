package com.admin.services;

import java.util.List;

import com.admin.dto.Route;

public interface AdminService {
	
	public List<Route> viewAllRoutes();

	
	String changePassword(String userName, String oldPassword, String newPassward);
	
}
