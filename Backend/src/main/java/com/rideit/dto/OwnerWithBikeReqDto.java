package com.rideit.dto;

import java.util.ArrayList;
import java.util.List;

public class OwnerWithBikeReqDto {
	private Long ownerId;
	private String ownerName;
	private Boolean verifiedStatus;
	private String email;
	public Boolean getVerifiedStatus() {
		return verifiedStatus;
	}
	public void setVerifiedStatus(Boolean verifiedStatus) {
		this.verifiedStatus = verifiedStatus;
	}
	private List<BikeForOwnerDto> bikes = new ArrayList<>();
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public List<BikeForOwnerDto> getBikes() {
		return bikes;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setBikes(List<BikeForOwnerDto> bikes) {
		this.bikes = bikes;
	}
	public OwnerWithBikeReqDto() {
		super();
	}
	
	
}
