package com.rideit.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.rideit.customException.ResourceNotFoundException;
import com.rideit.dao.BikeDao;
import com.rideit.enumclass.Status;
import com.rideit.pojo.Bike;

public class BikeService implements IBikeService {
	@Autowired
	private BikeDao bikeDao;
	@Override
	public String updateBikeStatus(Status status, Long bikeId) {
		Bike bikeDetails = bikeDao.findById(bikeId).orElseThrow(()->new ResourceNotFoundException("bike not found"));
		bikeDetails.setStatus(status);
		return "status updated successfully";
	}

	@Override
	public String updateBookingType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal calculateTotalAmount(BigDecimal rent, Long hours) {
//		BigDecimal totalAmount  = r
		return null;
	}

}
