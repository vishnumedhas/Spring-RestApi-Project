package com.dcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.reqdto.RegisterRequest;
import com.dcl.responseDto.UserDto;
import com.dcl.service.UserService;
import com.dcl.user.response.ApiResponse;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService uservice;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request){
		
		UserDto dto=uservice.register(request);
		return new ResponseEntity<>(new ApiResponse<>("Data added successfully",dto,HttpStatus.OK),HttpStatus.OK);
	}
}
