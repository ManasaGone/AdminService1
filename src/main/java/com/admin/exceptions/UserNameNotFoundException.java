package com.admin.exceptions;

public class UserNameNotFoundException extends RuntimeException {
	public UserNameNotFoundException(String msg) {
		super(msg);
	}
}
