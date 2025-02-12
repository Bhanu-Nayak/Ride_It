package com.rideit.services;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rideit.bike.BikeDto;
import com.rideit.customException.ResourceNotFoundException;
import com.rideit.dao.BikeDao;
import com.rideit.dao.PhotoByStringDao;
import com.rideit.enumclass.FuelType;
import com.rideit.pojo.Bike;
import com.rideit.pojo.Owner;
import com.rideit.pojo.PhotosByString;

import io.jsonwebtoken.io.IOException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PhotosByStringService implements IPhotoByStringService{
	@Autowired
	private BikeDao bikeDao;
	@Autowired
	private PhotoByStringDao photoDao;
	private final String FOLDER_PATH = "C:/Users/bnaya/Desktop/Project/rideIt-Client/public/";  
	@Override
	public String uploadImageToFileSystem(Long id,MultipartFile file)throws IllegalStateException, java.io.IOException{
		String file_path = FOLDER_PATH+file.getOriginalFilename();
		Bike bikeDetails = bikeDao.findById(id).orElseThrow();
		Bike bike = new Bike();
		PhotosByString photo = new PhotosByString();

		photo.setName(file.getOriginalFilename());

		photo.setType(file.getContentType());
		photo.setFilepath(file_path);
		bikeDetails.addBikeImage(photo);
		file.transferTo(new File(file_path));
		if(photo!=null) {
			return "File is uploaded Successfullt"+ file_path;
		}		
		return null;
	}
	//change
	@Override
	public List<PhotosByString> getAllPhotos() {
		return photoDao.findAll();
	}
	

}
