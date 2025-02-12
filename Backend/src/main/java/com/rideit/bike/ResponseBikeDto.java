package com.rideit.bike;

import java.math.BigDecimal;

public class ResponseBikeDto {
	private Long bikeId;
	private Long ownerId;
	private String model;
	private String brand;
	private String fuel;
    private String engineCapacity;
    private String registrationNumber;
    private BigDecimal rentHourly;
    private BigDecimal rentDaily;
    private Boolean deliveryOption;
    private String bikeCondition;
    private int yearOfManufacture;
	private String bikeType;
//	private PhotosDto profilePhoto;
//	private String insuranceDetails;
	private BigDecimal mileage;
	public Long getBikeId() {
		return bikeId;
	}
	public void setBikeId(Long bikeId) {
		this.bikeId = bikeId;
	}
//	public PhotosDto getProfilePhoto() {
//		return profilePhoto;
//	}
//	public void setProfilePhoto(PhotosDto profilePhoto) {
//		this.profilePhoto = profilePhoto;
//	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	public String getEngineCapacity() {
		return engineCapacity;
	}
	public void setEngineCapacity(String engineCapacity) {
		this.engineCapacity = engineCapacity;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public BigDecimal getRentHourly() {
		return rentHourly;
	}
	public void setRentHourly(BigDecimal rentHourly) {
		this.rentHourly = rentHourly;
	}
	public BigDecimal getRentDaily() {
		return rentDaily;
	}
	public void setRentDaily(BigDecimal rentDaily) {
		this.rentDaily = rentDaily;
	}
	public Boolean getDeliveryOption() {
		return deliveryOption;
	}
	public void setDeliveryOption(Boolean deliveryOption) {
		this.deliveryOption = deliveryOption;
	}
	public String getBikeCondition() {
		return bikeCondition;
	}
	public void setBikeCondition(String bikeCondition) {
		this.bikeCondition = bikeCondition;
	}
	public int getYearOfManufacture() {
		return yearOfManufacture;
	}
	public void setYearOfManufacture(int yearOfManufacture) {
		this.yearOfManufacture = yearOfManufacture;
	}
	public String getBikeType() {
		return bikeType;
	}
	public void setBikeType(String bikeType) {
		this.bikeType = bikeType;
	}
//	public String getInsuranceDetails() {
//		return insuranceDetails;
//	}
//	public void setInsuranceDetails(String insuranceDetails) {
//		this.insuranceDetails = insuranceDetails;
//	}
	public BigDecimal getMileage() {
		return mileage;
	}
	public void setMileage(BigDecimal mileage) {
		this.mileage = mileage;
	}
	
}
