package com.rideit.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BikeResponseDto {
	private Long bikeId;
	private Long ownerId;
	private String model;
	private String brand;
	private String fuel;
	private String engineCapacity;
	private String registrationNumber;
	private BigDecimal rentHourly;
	private BigDecimal rentDaily;
	private String bikeCondition;
	private String status;
	private BigDecimal mileage;
	private String ownerName;
	private List<BikePhotosDto> bikeImage= new ArrayList<>();
	
//	private Long bikeId;
//	private Long ownerId;
//	private String model;
//	private String brand;
//	private String fuel;
//    private String engineCapacity;
//    private String registrationNumber;
//    private BigDecimal rentHourly;
//    private BigDecimal rentDaily;
//    private Boolean deliveryOption;
//    private String bikeCondition;
//    private int yearOfManufacture;
//	private String bikeType;
////	private PhotosDto profilePhoto;
////	private String insuranceDetails;
//	private BigDecimal mileage;
	
	public List<BikePhotosDto> getBikeImage() {
		return bikeImage;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public void setBikeImage(List<BikePhotosDto> bikeImage) {
		this.bikeImage = bikeImage;
	}
	public Long getBikeId() {
		return bikeId;
	}
	public void setBikeId(Long bikeId) {
		this.bikeId = bikeId;
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
	public String getBikeCondition() {
		return bikeCondition;
	}
	public void setBikeCondition(String bikeCondition) {
		this.bikeCondition = bikeCondition;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getMileage() {
		return mileage;
	}
	public void setMileage(BigDecimal mileage) {
		this.mileage = mileage;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

}
