package com.rideit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rideit.pojo.Customer;
import com.rideit.pojo.Owner;
import com.rideit.pojo.ProfilePhoto;

public interface PhotoDao extends JpaRepository<ProfilePhoto, Long> {

	public ProfilePhoto findByOwner(Owner owner);
	public ProfilePhoto findByCustomer(Customer customer);
	
}
