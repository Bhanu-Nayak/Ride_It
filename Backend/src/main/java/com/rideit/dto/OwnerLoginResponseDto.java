package com.rideit.dto;

import com.rideit.enumclass.Role;

public class OwnerLoginResponseDto {
	private Long ownerId;
	private String name;
	private String email;
	private String contactNum;
	private String aadhaarNumber;
	private Boolean deliveryAvailability;
	private ProfilePhotoDto profilePhoto;
	
	
	public ProfilePhotoDto getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(ProfilePhotoDto profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public Boolean getDeliveryAvailability() {
		return deliveryAvailability;
	}

	public void setDeliveryAvailability(Boolean deliveryAvailability) {
		this.deliveryAvailability = deliveryAvailability;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	private Role role;
	
	private String jwt;

	public OwnerLoginResponseDto() {
		super();
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "LoginResponseDto [name=" + name + ", email=" + email + ", role=" + role + "]";
	}
}
