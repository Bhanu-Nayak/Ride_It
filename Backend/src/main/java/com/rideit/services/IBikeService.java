package com.rideit.services;

import java.math.BigDecimal;

import com.rideit.enumclass.Status;

public interface IBikeService {
	String updateBikeStatus(Status status, Long bikeId);
	String updateBookingType();
	BigDecimal calculateTotalAmount(BigDecimal rent, Long hours);
	
}
