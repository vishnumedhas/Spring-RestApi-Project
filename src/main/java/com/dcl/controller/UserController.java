package com.dcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.exception.AppException;
import com.dcl.reqdto.LoginRequest;
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
	public ResponseEntity<?> register( @Validated @RequestBody RegisterRequest request,BindingResult result){
		if(result.hasErrors()) {
			throw new AppException(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		UserDto dto=uservice.register(request);
		return  ResponseEntity.ok(new ApiResponse<>("Data added successfully",dto,HttpStatus.OK));
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?>login(@RequestBody LoginRequest request){
		UserDto dto= uservice.login(request);
		return ResponseEntity.ok(new ApiResponse<>("Login Successful", dto, HttpStatus.OK));
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?>delete(@PathVariable Integer userId){
		uservice.deleteUserById(userId);
		return ResponseEntity.ok("Deleted Successfully");
	}
	
	@GetMapping("/get/{userId}")
	public ResponseEntity<?>getUserById(@PathVariable Integer userId){
		UserDto dto=uservice.getUserById(userId);
		return ResponseEntity.ok(new ApiResponse<>("User Details Fetched", dto, HttpStatus.FOUND));
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?>getAll(){
		List<UserDto>dto=uservice.getAllUser();
		return ResponseEntity.ok(new ApiResponse<>("Users Found", dto, HttpStatus.FOUND));
	}
}
