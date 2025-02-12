package com.rideit.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rideit.pojo.Owner;

public interface OwnerDao extends JpaRepository<Owner, Long> {

	Optional<Owner> findByEmail(String email);
	@Query("SELECT o FROM Owner o LEFT JOIN FETCH o.bikes ")
	//SELECT b.mileage, o.email 
//	FROM Bike b 
//	JOIN b.owner o

    List<Owner> findAllOwnerWithBikes();
	@Query("SELECT o, p FROM Owner o JOIN o.profilePhoto p")
	public Owner findOwnerWithProfilePhoto(Long id);
	
}
