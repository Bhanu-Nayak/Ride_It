package com.rideit.customException;

public class LoginException extends RuntimeException{
	public LoginException(String msg) {
		super(msg);
		
	}
}
