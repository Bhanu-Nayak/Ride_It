package com.rideit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rideit.enumclass.Role;
import com.rideit.pojo.UserEntity;


public interface UserEntityDao extends JpaRepository<UserEntity, Long>{

	UserEntity findByUserEntityIdEmailAndUserEntityIdRole(String email, Role role);
	UserEntity findByUserEntityIdEmailAndId(String email, Long id);
	Boolean existsByUserEntityIdEmailAndUserEntityIdRole(String email, Role role);
	UserEntity findByUserEntityIdEmail(String email);
//	UserEntity findByEmail(String email);
//	List<UserEntity> findByEmail(String email);O
}
