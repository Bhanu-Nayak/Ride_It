package com.rideit.security;

import org.springframework.stereotype.Component;

@Component
public class Request {
	private String request;

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Request() {
		super();

	}


	
}
