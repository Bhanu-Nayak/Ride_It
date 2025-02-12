package com.rideit.services;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rideit.dao.CustomerDao;
import com.rideit.dao.OwnerDao;
import com.rideit.dao.PhotoDao;
import com.rideit.pojo.Customer;
import com.rideit.pojo.Owner;
import com.rideit.pojo.ProfilePhoto;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class PhotoService implements IPhotoService {

	@Autowired
	private PhotoDao profilePhotoDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private OwnerDao ownerDao;
	private final String FOLDER_PATH = "C:/Users/bnaya/Desktop/Project/rideIt-Client/public/";
	
	@Override
	public String uploadImageToFileSystem(Long id, MultipartFile file, String type) throws IllegalStateException, IOException {
				//D:/DriveIt_Client/public/resume(1)
			String file_path = FOLDER_PATH+file.getOriginalFilename();
			ProfilePhoto photo = new ProfilePhoto();
			photo.setType(file.getContentType());
			photo.setName(file.getOriginalFilename());
			photo.setFilepath(file_path);
			if(type == "CUSTOMER") {
				Customer cust = customerDao.findById(id).orElseThrow();
				
				photo.setCustomer(cust);
			}
			else {
				Owner owner = ownerDao.findById(id).orElseThrow();
				photo.setOwner(owner);
			}
			
			
			profilePhotoDao.save(photo);
			file.transferTo(new File(file_path));
			if(photo!=null) {
				return "File is uploaded Successfullt"+ file_path;
			}		
			return null;
		
//		return null;
	}

}
