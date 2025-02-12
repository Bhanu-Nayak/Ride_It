package com.rideit.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
//import lombok.Data;
import lombok.EqualsAndHashCode;

//@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "owners")
public class Owner extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;
    @Column(nullable = false, unique = true, length = 12)
    private String aadhaarNumber;

    private Boolean deliveryAvailability = false;
    
    
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bike> bikes = new ArrayList<>();
    
    @OneToMany(mappedBy="owner", cascade= CascadeType.ALL, orphanRemoval = true)
    private List<BankAccount> AccountsList= new ArrayList<>();

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProfilePhoto profilePhoto;
    
    public void addAccount(BankAccount acc) {
		
		AccountsList.add(acc);
		acc.setOwner(this);
	}
    
	public void removeAccount(BankAccount bAcc) {

		AccountsList.remove(bAcc);
		bAcc.setOwner(null);
		
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}



	public Boolean getDeliveryAvailability() {
		return deliveryAvailability;
	}

	public void setDeliveryAvailability(Boolean deliveryAvailability) {
		this.deliveryAvailability = deliveryAvailability;
	}



	public Owner() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Owner(Long ownerId, String aadhaarNumber, BigDecimal rating,
			Boolean deliveryAvailability) {
		super();
		this.ownerId = ownerId;
		this.aadhaarNumber = aadhaarNumber;

		this.deliveryAvailability = deliveryAvailability;
	}
    
	public void addBike(Bike bike) {
		this.bikes.add(bike);
		bike.setOwner(this);

	}

	
	public void removeBike(Bike bike) {
		this.bikes.remove(bike);
		bike.setOwner(null);
	}

//	@Override
//	public String toString() {
//		return "Owner [ownerId=" + ownerId + ", aadhaarNumber=" + aadhaarNumber + ", deliveryAvailability="
//				+ deliveryAvailability + "]";
//	}

	public List<Bike> getBikes() {
		return bikes;
	}

	@Override
	public String toString() {
		return "Owner [ownerId=" + ownerId + ", aadhaarNumber=" + aadhaarNumber + ", deliveryAvailability="
				+ deliveryAvailability + ", bikes=" + bikes + ", profilePhoto=" + profilePhoto + "]";
	}

	public ProfilePhoto getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(ProfilePhoto profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	
}

