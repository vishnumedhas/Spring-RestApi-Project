package com.dcl.service;

import org.springframework.web.multipart.MultipartFile;

import com.dcl.entity.Profile;
import com.dcl.reqdto.ProfileUpdateRequest;
import com.dcl.responseDto.ProfileDto;

public interface ProfileService {

	Profile addProfile(Profile profile);
	
	ProfileDto updateProfile(Integer profileId, ProfileUpdateRequest request,MultipartFile image);
	
	void deleteProfile(Integer profileId);
	
	ProfileDto getByProfileId(Integer profileId);
	
	ProfileDto getProfileByUserId(Integer userId);
}
