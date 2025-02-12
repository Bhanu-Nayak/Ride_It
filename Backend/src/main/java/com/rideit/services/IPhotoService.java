package com.rideit.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IPhotoService{
	String uploadImageToFileSystem(Long id, MultipartFile file, String type) throws IllegalStateException, IOException;
}
