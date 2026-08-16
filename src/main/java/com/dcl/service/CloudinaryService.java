package com.dcl.service;

import org.springframework.web.multipart.MultipartFile;

import com.dcl.user.response.CloudinaryResponse;

public interface CloudinaryService {

	CloudinaryResponse uploadImage(MultipartFile image);
	
	void deleteImage(String publicId);
}
