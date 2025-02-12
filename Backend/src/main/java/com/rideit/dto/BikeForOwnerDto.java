package com.rideit.dto;

import java.math.BigDecimal;

import com.rideit.enumclass.FuelType;
import com.rideit.enumclass.Status;

public class BikeForOwnerDto {
	private Long bikeId;
    private String model;
    private String brand;
    private FuelType fuel;
    private String engineCapacity;
    private String registrationNumber;
    private BigDecimal rentHourly;
    private BigDecimal rentDaily;
    private Boolean deliveryOption;
    private String bikeCondition;
    private Status status;
    private int yearOfManufacture;
    private String bikeType;
    private String insuranceDetails;
    private BigDecimal mileage;

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

	public FuelType getFuel() {
		return fuel;
	}

	public void setFuel(FuelType fuel) {
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public String getInsuranceDetails() {
		return insuranceDetails;
	}

	public void setInsuranceDetails(String insuranceDetails) {
		this.insuranceDetails = insuranceDetails;
	}

	public BigDecimal getMileage() {
		return mileage;
	}

	public void setMileage(BigDecimal mileage) {
		this.mileage = mileage;
	}

	public BikeForOwnerDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
}
