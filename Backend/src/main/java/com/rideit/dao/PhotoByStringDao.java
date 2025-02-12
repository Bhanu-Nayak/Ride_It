package com.rideit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rideit.pojo.Bike;
import com.rideit.pojo.PhotosByString;

public interface PhotoByStringDao extends JpaRepository<PhotosByString, Long>{
	List<PhotosByString> findByBike(Bike bike);
}
