package com.rideit.dto;

import java.math.BigDecimal;

import com.rideit.enumclass.Role;

public class GetCustomerResponseDto {
	private Long custId;
    private String name;
    private String contactNum;
    private String email;
    private Role role;
    private String aadhaarNumber;
    private String drivingLicense;
    private BigDecimal rating;
    private ProfilePhotoDto profilePhoto;
    private Boolean verifiedStatus;
    

	public Boolean getVerifiedStatus() {
		return verifiedStatus;
	}

	public void setVerifiedStatus(Boolean verifiedStatus) {
		this.verifiedStatus = verifiedStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
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

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public String getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public ProfilePhotoDto getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(ProfilePhotoDto profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
    
}
