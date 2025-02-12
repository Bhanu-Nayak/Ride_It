package com.rideit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rideit.enumclass.Status;
import com.rideit.pojo.Bike;
import com.rideit.pojo.Owner;

@Repository
public interface BikeDao extends JpaRepository<Bike, Long> {
	List<Bike> findByOwner(Owner owner);
	List<Bike> findByStatus(Status status);
	@Query("SELECT b FROM Bike b JOIN FETCH b.bikeImage")
    List<Bike> findAllBikesWithPhotos();
//	@Query("SELECT b.bikeId, p FROM Bike b JOIN b.bikeImage p")
//	List<Bike> findAllPhotoByBikeId(Long );
	List<Bike> findAllBikesByOwner(Owner owner);
}
