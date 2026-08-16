package com.dcl.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dcl.reqdto.ProfileUpdateRequest;
import com.dcl.responseDto.ProfileDto;
import com.dcl.service.ProfileService;
import com.dcl.user.response.ApiResponse;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private ProfileService pservice;
	
	//@RequestParam is a Spring MVC annotation used to get a value from the URL query parameter and pass it into a controller method.
	
	@PutMapping(value="/update/{profileId}",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?>updateProfile(@PathVariable Integer profileId,
			@RequestPart(value="image",required=false)MultipartFile image,
			 @RequestParam String firstName,
			@RequestParam String lastName,@RequestParam LocalDate dob,@RequestParam String phone){
		
		ProfileUpdateRequest p=new ProfileUpdateRequest();
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setDob(dob);
		p.setPhone(phone);
		
		ProfileDto pdto=pservice.updateProfile(profileId, p,image);
		return ResponseEntity.ok(new ApiResponse<>("Profile updated successfully",pdto,HttpStatus.OK));
	}
}
