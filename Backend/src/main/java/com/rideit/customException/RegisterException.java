package com.rideit.customException;

public class RegisterException extends RuntimeException{
	public RegisterException( String msg)
	{
		super(msg);
	}
}
