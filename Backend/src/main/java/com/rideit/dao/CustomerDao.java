package com.rideit.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rideit.pojo.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {
	Optional<Customer> findByEmail(String email);
}
