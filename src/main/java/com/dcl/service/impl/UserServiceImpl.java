package com.dcl.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dcl.entity.User;
import com.dcl.exception.AppException;
import com.dcl.repo.UserRepository;
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

}
