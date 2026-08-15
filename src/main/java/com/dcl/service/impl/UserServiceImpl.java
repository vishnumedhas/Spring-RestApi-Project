package com.dcl.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dcl.entity.User;
import com.dcl.exception.AppException;
import com.dcl.repo.UserRepository;
import com.dcl.reqdto.LoginRequest;
import com.dcl.reqdto.RegisterRequest;
import com.dcl.responseDto.UserDto;
import com.dcl.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public UserDto register(RegisterRequest request) {
		
    User alreadyExists=urepo.findByEmail(request.getEmail()).orElse(null);
    if(alreadyExists!=null) {
    	throw new AppException("User already exists", HttpStatus.BAD_REQUEST);
    }
    
    User u=mapper.map(request, User.class);
    u=urepo.save(u);
    UserDto dto=mapper.map(u, UserDto.class);
		return dto;
	}

	@Override
	public UserDto login(LoginRequest request) {
	User alreadyExists=urepo.findByEmail(request.getEmail()).orElse(null);
	if(alreadyExists==null) {
		throw new AppException("User not found!", HttpStatus.NOT_FOUND);
	}
	UserDto dto=mapper.map(alreadyExists,UserDto.class);
		return dto;
	}

	@Override
	public void deleteUserById(Integer userId) {
	
		User u=urepo.findById(userId).orElse(null);
		if(u==null) {
			throw new AppException("User not Found", HttpStatus.NOT_FOUND);
		}
		urepo.deleteById(userId);
		
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User u=urepo.findById(userId).orElse(null);
		if(u==null) {
			throw new AppException("User Not Found", HttpStatus.NOT_FOUND);
		}
		UserDto dto=mapper.map(u, UserDto.class);
		return dto;
	}

	
	
	//urepo.findAll() gives you a List of User entities, but your method needs to return a List of UserDto objects.
	//You cannot directly use .map() on a normal List.
	//List doesn't have a map() method. So we convert the list into a Stream:A stream allows you to process every element one by one.
   // map what it does Takes each User object and convert it into a UserDto object.
//	After map(), you have: Stream<UserDto>
//	But your method says:		public List<UserDto> getAllUser()
// So you need to convert the stream back into a List So use .collect(Collectors.toList()) does.

	@Override
	public List<UserDto> getAllUser() {

		return urepo.findAll()
				.stream()
				.map(u->mapper.map(u, UserDto.class))
				.collect(Collectors.toList());
		
	}



}
