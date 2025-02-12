package com.rideit.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rideit.bike.BikeDto;
import com.rideit.bike.ResponseBikeDto;
import com.rideit.dto.BikeResponseDto;
import com.rideit.dto.LocationDto;
import com.rideit.dto.OwnerLoginRequesetDto;
import com.rideit.dto.OwnerLoginResponseDto;
import com.rideit.dto.OwnerRegisterDto;
import com.rideit.dto.UpdateBikeRequestDto;
import com.rideit.pojo.Owner;

public interface IOwnerService {
	String registerOwner(OwnerRegisterDto transientOwner);
	OwnerLoginResponseDto loginOwner(OwnerLoginRequesetDto loginDetails);
	String updateOwnerDetails(OwnerRegisterDto trasientOwner, Long ownerId);
	String updateProfilePhoto();
	String toggleAvailabilty(Boolean status, Long ownerId);
	List<Owner> getAllOwnersDetails();
	OwnerRegisterDto getOwnerDetails(Long ownerId);
	String addBike(BikeDto bike);
	ResponseBikeDto getBikeById(Long bikeId);
	List<BikeResponseDto> getBikeByOwnerId(Long ownerId);
	String updateBikeStatus(String status, Long id);
	String deleteBike(Long bikeId);
	String updateOwnerBike(Long bikeId, UpdateBikeRequestDto bikeDto);
	String uploadbikeImage(Long id, MultipartFile image)throws IOException;
	String deleteOwner(Long ownerId);
	String uploadProfilePhoto(Long id, MultipartFile file)throws IOException;
	String updateLocation(Long id, LocationDto location);
}
