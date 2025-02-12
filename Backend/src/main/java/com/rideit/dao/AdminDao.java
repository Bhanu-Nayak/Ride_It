package com.rideit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rideit.pojo.Admin;

public interface AdminDao extends JpaRepository<Admin, Long>{
	Admin findByEmail(String email);
}
