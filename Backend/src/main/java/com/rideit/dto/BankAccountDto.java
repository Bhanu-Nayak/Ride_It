package com.rideit.dto;

public class BankAccountDto {
	private String ifscCode;
//	private Long id;
	private Long owner;
	
	
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public Long getOwner() {
		return owner;
	}
	public void setOwner(Long owner) {
		this.owner = owner;
	}
}
