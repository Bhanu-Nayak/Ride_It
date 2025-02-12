package com.rideit.pojo;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.rideit.enumclass.FuelType;
import com.rideit.enumclass.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "bikes")
public class Bike extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bikeId;


    @Column(nullable = false)
    private String model;
    
    @Column(nullable = false)
    private String brand;

    @Enumerated(EnumType.STRING)
    private FuelType fuel;
    
    @Column(nullable = false)
    private String engineCapacity;

    @Column(nullable = false, unique = true)
    private String registrationNumber;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal rentHourly;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal rentDaily;

    @Column(nullable = false)
    private Boolean deliveryOption;

    @Column(nullable = false)
    private String bikeCondition;

    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;
    
    private int yearOfManufacture;

    private String bikeType;

    @Column(columnDefinition = "JSON")
    private String insuranceDetails;

    @Column(precision = 5, scale = 2)
    private BigDecimal mileage;
    @Embedded
    private Location location;
     
    @ManyToOne()
    @JoinColumn(name = "owner_id" )
    private Owner owner;
    
    @OneToMany(mappedBy = "bike" , cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
    private List<PhotosByString> bikeImage = new ArrayList<>();

    
    public void addBikeImage(PhotosByString photo) {
    	this.bikeImage.add(photo);
    	photo.setBike(this);
    }
    
	public Long getBikeId() {
		return bikeId;
	}

	public List<PhotosByString> getBikeImage() {
		return bikeImage;
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

	public String getCondition() {
		return bikeCondition;
	}

	public void setCondition(String condition) {
		this.bikeCondition = condition;
	}
	public Integer getYearOfManufacture() {
		return yearOfManufacture;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setYearOfManufacture(Integer yearOfManufacture) {
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Bike() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	
	public String getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(String engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

	public String getBikeCondition() {
		return bikeCondition;
	}

	public void setBikeCondition(String bikeCondition) {
		this.bikeCondition = bikeCondition;
	}

	public void setYearOfManufacture(int yearOfManufacture) {
		this.yearOfManufacture = yearOfManufacture;
	}
	
	public void addPhoto(PhotosByString photo) {
		this.bikeImage.add(photo);
		photo.setBike(this);
	}
	
	public FuelType getFuel() {
		return fuel;
	}

	public void setFuel(FuelType fuel) {
		this.fuel = fuel;
	}


//public Bike(Long bikeId, String model, String brand, FuelType fuel, String engineCapacity,
//			String registrationNumber, BigDecimal rentHourly, BigDecimal rentDaily, Boolean deliveryOption,
//			String bikeCondition, Status status, int yearOfManufacture, String bikeType, String insuranceDetails,
//			BigDecimal mileage, Location location, Owner owner, List<PhotosByString> bikeImage) {
//		super();
//		this.bikeId = bikeId;
//		this.model = model;
//		this.brand = brand;
//		this.fuel = fuel;
//		this.engineCapacity = engineCapacity;
//		this.registrationNumber = registrationNumber;
//		this.rentHourly = rentHourly;
//		this.rentDaily = rentDaily;
//		this.deliveryOption = deliveryOption;
//		this.bikeCondition = bikeCondition;
//		this.status = status;
//		this.yearOfManufacture = yearOfManufacture;
//		this.bikeType = bikeType;
//		this.insuranceDetails = insuranceDetails;
//		this.mileage = mileage;
//		this.location = location;
//		this.owner = owner;
//		this.bikeImage = bikeImage;
//	}

public Bike(Long bikeId, String model, String brand, FuelType fuel, String engineCapacity, String registrationNumber,
		BigDecimal rentHourly, BigDecimal rentDaily, Boolean deliveryOption, String bikeCondition, Status status,
		int yearOfManufacture, String bikeType, String insuranceDetails, BigDecimal mileage, Owner owner,
		List<PhotosByString> bikeImage) {
	super();
	this.bikeId = bikeId;
	this.model = model;
	this.brand = brand;
	this.fuel = fuel;
	this.engineCapacity = engineCapacity;
	this.registrationNumber = registrationNumber;
	this.rentHourly = rentHourly;
	this.rentDaily = rentDaily;
	this.deliveryOption = deliveryOption;
	this.bikeCondition = bikeCondition;
	this.status = status;
	this.yearOfManufacture = yearOfManufacture;
	this.bikeType = bikeType;
	this.insuranceDetails = insuranceDetails;
	this.mileage = mileage;
	this.owner = owner;
	this.bikeImage = bikeImage;
}

@Override
public String toString() {
	return "Bike [bikeId=" + bikeId + ", model=" + model + ", brand=" + brand + ", fuel=" + fuel + ", engineCapacity="
			+ engineCapacity + ", registrationNumber=" + registrationNumber + ", rentHourly=" + rentHourly
			+ ", rentDaily=" + rentDaily + ", deliveryOption=" + deliveryOption + ", bikeCondition=" + bikeCondition
			+ ", status=" + status + ", yearOfManufacture=" + yearOfManufacture + ", bikeType=" + bikeType
			+ ", mileage=" + mileage + "]";
}

//public Bike(Long bikeId, String model, String brand, FuelType fuel, String engineCapacity, String registrationNumber,
//		BigDecimal rentHourly, BigDecimal rentDaily, Boolean deliveryOption, String bikeCondition, Status status,
//		int yearOfManufacture, String bikeType, BigDecimal mileage, Location location, Owner owner,
//		List<PhotosByString> bikeImage) {
//	super();
//	this.bikeId = bikeId;
//	this.model = model;
//	this.brand = brand;
//	this.fuel = fuel;
//	this.engineCapacity = engineCapacity;
//	this.registrationNumber = registrationNumber;
//	this.rentHourly = rentHourly;
//	this.rentDaily = rentDaily;
//	this.deliveryOption = deliveryOption;
//	this.bikeCondition = bikeCondition;
//	this.status = status;
//	this.yearOfManufacture = yearOfManufacture;
//	this.bikeType = bikeType;
//	this.mileage = mileage;
//	this.location = location;
//	this.owner = owner;
//	this.bikeImage = bikeImage;
//}
//	

}
