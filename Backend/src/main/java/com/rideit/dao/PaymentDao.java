package com.rideit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rideit.pojo.Payment;

public interface PaymentDao extends JpaRepository<Payment, Long> {

}
