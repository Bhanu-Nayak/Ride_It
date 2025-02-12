package com.rideit.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rideit.bike.BikeDto;
import com.rideit.bike.ResponseBikeDto;
import com.rideit.customException.ResourceNotFoundException;
import com.rideit.dao.BankAccountDao;
import com.rideit.dao.BikeDao;
import com.rideit.dao.OwnerDao;
import com.rideit.dao.PhotoDao;
import com.rideit.dao.UserEntityDao;
import com.rideit.dto.BikeResponseDto;
import com.rideit.dto.LocationDto;
import com.rideit.dto.OwnerLoginRequesetDto;
import com.rideit.dto.OwnerLoginResponseDto;
import com.rideit.dto.OwnerRegisterDto;
import com.rideit.dto.ProfilePhotoDto;
import com.rideit.dto.UpdateBikeRequestDto;
import com.rideit.enumclass.FuelType;
import com.rideit.enumclass.Role;
import com.rideit.enumclass.Status;
import com.rideit.pojo.BankAccount;
import com.rideit.pojo.Bike;
import com.rideit.pojo.Location;
import com.rideit.pojo.Owner;
import com.rideit.pojo.ProfilePhoto;
import com.rideit.pojo.UserEntity;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class OwnerService implements IOwnerService{
	@Autowired
	private OwnerDao ownerDao;
	@Autowired
	private BikeDao bikeDao;
	@Autowired
	private IPhotoByStringService photoService;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private BankAccountDao bankAccDao;
	@Autowired
	private PhotoDao photoDao;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UserEntityDao userEntityDao;
	@Autowired
	private PhotoService profilePhotoService;
	@Override
	public String registerOwner(OwnerRegisterDto transientOwner) {
			transientOwner.setPassword(encoder.encode(transientOwner.getPassword()));
			Owner ownerDetails = mapper.map(transientOwner, Owner.class);
			ownerDetails.setRole(Role.valueOf(transientOwner.getRole().toUpperCase()));
			ownerDao.save(ownerDetails);
			return "Owner Addedd Successfully";
		
		
	}


	@Override
	public OwnerLoginResponseDto loginOwner(OwnerLoginRequesetDto loginDetails) {
		Owner o =ownerDao.findByEmail(loginDetails.getEmail())
				.orElseThrow(()->new ResourceNotFoundException("email not found"));
		ProfilePhoto photoDetails = photoDao.findByOwner(o);
		if(encoder.matches(loginDetails.getPassword(), o.getPassword())) {
			if(userEntityDao.existsByUserEntityIdEmailAndUserEntityIdRole(o.getEmail(), o.getRole())) {
				OwnerLoginResponseDto dto = mapper.map(o, OwnerLoginResponseDto.class);
				if(photoDetails!=null)
				dto.setProfilePhoto(mapper.map(photoDetails, ProfilePhotoDto.class));
				return dto;
			}
			UserEntity e1 = new UserEntity(o.getName(), o.getEmail(), o.getPassword(), o.getRole());
			userEntityDao.save(e1);
			
		}
		else {
			 new ResourceNotFoundException("Invalid password");
		}
		OwnerLoginResponseDto resDto = mapper.map(o, OwnerLoginResponseDto.class);
		if(photoDetails!=null) {			
			ProfilePhotoDto  profilePhoto = mapper.map(photoDetails, ProfilePhotoDto.class);
			resDto.setProfilePhoto(profilePhoto);
		}
		return resDto;
	}

	@Override
	public String updateOwnerDetails(OwnerRegisterDto trasientOwner, Long ownerId) {

		Owner existingOwner=ownerDao.findById(ownerId).orElseThrow(()->new ResourceNotFoundException("invalid owner id ")) ;		
		mapper.map(trasientOwner, existingOwner);
		ownerDao.save(existingOwner) ;

		return "Owner update successfull";
	}

	@Override
	public String updateProfilePhoto() {
		return null;
	}

	@Override
	public String toggleAvailabilty(Boolean status, Long ownerId) {
		Owner o	=ownerDao.findById(ownerId).orElseThrow(()->new ResourceNotFoundException("invalid owner Id")) ;
		o.setDeliveryAvailability(status);
		ownerDao.save(o);
		return "Availability updated successfully";
	}

	@Override
	public List<Owner> getAllOwnersDetails() {
		List<Owner> olist=ownerDao.findAll();
		return olist;
	}

	@Override
	public OwnerRegisterDto getOwnerDetails(Long ownerId) {
		Owner o	=ownerDao.findById(ownerId).orElseThrow(()->new ResourceNotFoundException("invalid owner id"));
	
	return mapper.map(o, OwnerRegisterDto.class) ;
	}

	@Override
	public String addBike(BikeDto bike) {
		Owner ownerDetails = ownerDao.findById(bike.getOwnerId()).orElseThrow(()->new ResourceNotFoundException("invalid bike id"));
		Bike bikeDetails= mapper.map(bike, Bike.class);
		bikeDetails.setFuel(FuelType.valueOf(bike.getFuel().toUpperCase()));
		bikeDetails.setLocation(new Location(bike.getLatitude(), bike.getLongitude()));
		ownerDetails.addBike(bikeDetails);
		return "Bike Added Successfully";
	}

	@Override
	public ResponseBikeDto getBikeById(Long bikeId) {
		Bike bikeDetails = bikeDao.findById(bikeId).orElseThrow(()->new ResourceNotFoundException("invalid bike id"));
		ResponseBikeDto bikeDto = mapper.map(bikeDetails, ResponseBikeDto.class);
		bikeDto.setOwnerId(bikeDetails.getOwner().getOwnerId());
		return bikeDto;
	}

	@Override
	public List<BikeResponseDto> getBikeByOwnerId(Long ownerId) {
		Owner owner = ownerDao.findById(ownerId).orElseThrow(()->new ResourceNotFoundException("invalid onwer id"));
//		Owner owner = ownerDao.findById(ownerId);
//		System.out.println(owner);
//		PhotosDto photos = mapper.map(owner.getProfilePhoto(), PhotosDto.class);
		System.out.println(owner);
//		System.out.prnintln(owner.get)
		List<Bike> bikeList = bikeDao.findAllBikesByOwner(owner);//bike with image
		System.out.println(bikeList);
//		for(Bike bike: bikeList) {
//			System.out.println(bike.getBikeImage());
//		}
		List<BikeResponseDto> resbikeList = new ArrayList<>();
		
		
		
		for(Bike bike: bikeList) {
//			ResponseBikeDto bikeDto = mapper.map(bike, ResponseBikeDto.class);
			BikeResponseDto bikeDto = mapper.map(bike, BikeResponseDto.class);
			//bike init
			bikeDto.setOwnerId(owner.getOwnerId());
			bikeDto.setOwnerName(owner.getName());
//			List<PhotosByString> photo = bike.getBikeImage();
//			for(PhotosByString photoDetails: photo) {
//				BikePhotosDto dto = mapper.map(photoDetails, BikePhotosDto.class);
//				bikeDto.getBikeImage().add(dto);
//			}
			resbikeList.add(bikeDto);
		}
		
		return resbikeList;
	}

	@Override
	public String updateBikeStatus(String status, Long id) {
		Bike bike = bikeDao.findById(id).orElseThrow(()->new ResourceNotFoundException("invalid bike id"));
		bike.setStatus(Status.valueOf(status.toUpperCase()));
		return "Bike Status updated Successfully";
	}

	@Override
	public String deleteBike(Long bikeId) {
		bikeDao.deleteById(bikeId);
		return "Bike Deleted Successfully";
	}
	@Override
	public String uploadbikeImage(Long id,MultipartFile file)throws IllegalStateException, java.io.IOException{
		return photoService.uploadImageToFileSystem(id, file);
	}
	@Override
	public String deleteOwner(Long ownerId) {
		Owner owner  = ownerDao.findById(ownerId).orElseThrow(()->new ResourceNotFoundException("invalid owner id"));
		List<Bike> bikeDetails = bikeDao.findByOwner(owner);
		List<BankAccount> bankList = bankAccDao.findByOwner(owner);
		ProfilePhoto photoList = photoDao.findByOwner(owner);
		if(photoList!=null) {
			photoDao.delete(photoList);			
		}
		if(bikeDetails!=null) {			
			bikeDao.deleteAll(bikeDetails);
		}
		if(bankList!=null) {
			bankAccDao.deleteAll(bankList);
		}
		ownerDao.delete(owner);
		return "owner deleted sucessfully";
	}


	@Override
	public String uploadProfilePhoto(Long id, MultipartFile file) throws IOException {
		
		return profilePhotoService.uploadImageToFileSystem(id, file, "OWNER");
	}


	@Override
	public String updateLocation(Long id, LocationDto location) {
		Bike bikeDetails = bikeDao.findById(id).orElseThrow(()->new ResourceNotFoundException("bike not found"));
		Location loc = mapper.map(location, Location.class);
		bikeDetails.setLocation(loc);
		return "location updated successfully";
	}


	@Override
	public String updateOwnerBike(Long bikeId, UpdateBikeRequestDto bikeDto) {
		Bike existingBike = bikeDao.findById(bikeId).orElseThrow(()->new ResourceNotFoundException("bike not found"));
//		private BigDecimal renthourly;
//		private BigDecimal rentdaily;
//		private String bikecondition;
//		private Status status;
		existingBike.setRentHourly(bikeDto.getRentHourly());
		existingBike.setRentDaily(bikeDto.getRentDaily());
		existingBike.setBikeCondition(bikeDto.getBikeCondition());
		existingBike.setStatus(bikeDto.getStatus());
//		mapper.map(bikeDto, existingBike);
//		bikeDao.save(existingBike);
		return "Bike Updated Successfully";
	}

	
}
