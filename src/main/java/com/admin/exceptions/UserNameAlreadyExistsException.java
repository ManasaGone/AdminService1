package com.admin.exceptions;

public class UserNameAlreadyExistsException extends RuntimeException {
	public UserNameAlreadyExistsException(String msg) {
		super(msg);
	}
}
