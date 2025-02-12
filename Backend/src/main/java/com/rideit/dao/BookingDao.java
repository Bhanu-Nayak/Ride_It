package com.rideit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rideit.pojo.Booking;

public interface BookingDao extends JpaRepository<Booking, Long> {

}
