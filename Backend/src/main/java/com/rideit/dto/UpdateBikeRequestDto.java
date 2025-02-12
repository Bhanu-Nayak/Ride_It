package com.rideit.dto;

import java.math.BigDecimal;

import com.rideit.enumclass.Status;

public class UpdateBikeRequestDto {
	private BigDecimal rentHourly;
	private BigDecimal rentDaily;
	private String bikeCondition;
	private Status status;
//	public BigDecimal getRenthourly() {
//		return renthourly;
//	}
//	public void setRenthourly(BigDecimal renthourly) {
//		this.renthourly = renthourly;
//	}
//	public BigDecimal getRentdaily() {
//		return rentdaily;
//	}
//	public void setRentdaily(BigDecimal rentdaily) {
//		this.rentdaily = rentdaily;
//	}
//	public String getBikecondition() {
//		return bikecondition;
//	}
//	public void setBikecondition(String bikecondition) {
//		this.bikecondition = bikecondition;
//	}
	
	public Status getStatus() {
		return status;
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
	public void setStatus(Status status) {
		this.status = status;
	}
	public UpdateBikeRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
