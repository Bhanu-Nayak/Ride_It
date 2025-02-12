package com.rideit.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rideit.pojo.PhotosByString;

public interface IPhotoByStringService{
	String uploadImageToFileSystem(Long id, MultipartFile file	) throws IllegalStateException, IOException;
	List<PhotosByString> getAllPhotos();
}
