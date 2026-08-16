																											package com.dcl.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dcl.entity.Profile;
import com.dcl.entity.Role;
import com.dcl.entity.User;
import com.dcl.exception.AppException;
import com.dcl.repo.UserRepository;
import com.dcl.reqdto.LoginRequest;
import com.dcl.reqdto.RegisterRequest;
import com.dcl.responseDto.ProfileDto;
import com.dcl.responseDto.RoleDto;
import com.dcl.responseDto.UserDto;
import com.dcl.service.MailSender;
import com.dcl.service.ProfileService;
import com.dcl.service.RoleService;
import com.dcl.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ProfileService pservice;
	
	@Autowired
	private RoleService rservice;
	
	@Autowired
	private MailSender mservice;
	
	@Transactional
	@Override
	public UserDto register(RegisterRequest request) {
		
	RoleDto existinRole=rservice.getRoleByRoleName(request.getRoleName());
	if(existinRole==null) {
		throw new AppException("Role not found", HttpStatus.NOT_FOUND);
	}
    User alreadyExists=urepo.findByEmail(request.getEmail()).orElse(null);
    if(alreadyExists!=null) {
    	throw new AppException("User already exists", HttpStatus.BAD_REQUEST);
    }
    
    User u=mapper.map(request, User.class);
    Role r=mapper.map(existinRole, Role.class);
    u.setRole(r);
    u=urepo.save(u);
    Profile p=mapper.map(request, Profile.class);
    p.setUser(u);
    p=pservice.addProfile(p);
    
    mservice.sendmail(request.getEmail(),"Welcome to E-commerce", request.getFirstName()+" "+"your account was created successfully");
    
    UserDto dto=mapper.map(u, UserDto.class);
   ProfileDto pdto= mapper.map(p, ProfileDto.class);
   dto.setProfileDto(pdto);
   dto.setRoleDto(existinRole);
		return dto;
	}

	@Override
	public UserDto login(LoginRequest request) {
	User alreadyExists=urepo.findByEmail(request.getEmail()).orElse(null);
	if(alreadyExists==null) {
		throw new AppException("User not found!", HttpStatus.NOT_FOUND);
	}
	if(!alreadyExists.getPassword().equals(request.getPassword())) {
		throw new AppException("Incorrect credentials", HttpStatus.BAD_REQUEST);
	}
	mservice.sendmail(request.getEmail(), "E Commerce"," "+ "your Account Login successfully");
	UserDto dto=mapper.map(alreadyExists,UserDto.class);
	ProfileDto pdto=pservice.getByProfileId(dto.getUserId());
	dto.setProfileDto(pdto);
		return dto;
	}

	@Override
	public void deleteUserById(Integer userId) {
	
		User u=urepo.findById(userId).orElse(null);
		if(u==null) {
			throw new AppException("User not Found", HttpStatus.NOT_FOUND);
		}
		pservice.deleteProfile(userId);
		urepo.deleteById(userId);
		mservice.sendmail(u.getEmail(),"E Commerce","your account Deleted successfully");
		
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User u=urepo.findById(userId).orElseThrow(()->new AppException("USer Not Found", HttpStatus.NOT_FOUND));
		
		ProfileDto pdto=pservice.getByProfileId(userId);
		
		RoleDto rdto=rservice.getRoleById(userId);
		
		UserDto dto=mapper.map(u, UserDto.class);
		dto.setProfileDto(pdto);
		dto.setRoleDto(rdto);
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
