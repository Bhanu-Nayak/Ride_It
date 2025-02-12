package com.rideit.pojo;

import java.math.BigDecimal;

//package com.example.bikerental.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

//@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customers")
public class Customer extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custId;
    @Column(nullable = false, unique = true, length = 12)
    private String aadhaarNumber;

    @Column(nullable = false, unique = true)
    private String drivingLicense;

    @Column(columnDefinition = "JSON")
    private String location; // JSON for latitude and longitude

    @Column(precision = 3, scale = 2)
    private BigDecimal rating;
    
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private ProfilePhoto profilePhoto;


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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfilePhoto getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(ProfilePhoto profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
    
}

