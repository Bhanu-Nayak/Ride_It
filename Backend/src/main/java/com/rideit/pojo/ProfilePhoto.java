package com.rideit.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="profilePhoto")
public class ProfilePhoto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imageId;


	@Column(name="name")
	private String name;
	
	@OneToOne
	@JoinColumn(name = "ownerId")
    private Owner owner;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "custId")
    private Customer customer;
    
	
	private String type;
	private String filepath;
	
	
//	public Owner getOwnerProfilephoto() {
//		return ownerProfilephoto;
//	}
//	public void setOwnerProfilephoto(Owner ownerProfilephoto) {
//		this.ownerProfilephoto = ownerProfilephoto;
//	}
//	public Customer getCustomerProfilePhoto() {
//		return customerProfilePhoto;
//	}
//	public void setCustomerProfilePhoto(Customer customerProfilePhoto) {
//		this.customerProfilePhoto = customerProfilePhoto;
//	}
	public Long getImageId() {
		return imageId;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public Bike getBike() {
//		return bike;
//	}
//	public void setBike(Bike bike) {
//		this.bike = bike;
//	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public ProfilePhoto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ProfilePhoto [imageId=" + imageId + ", name=" + name + ", type=" + type + ", filepath=" + filepath
				+ "]";
	}
	
	
	

}
