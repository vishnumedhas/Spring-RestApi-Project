package com.dcl.service;

import java.util.List;

import com.dcl.reqdto.LoginRequest;
import com.dcl.reqdto.RegisterRequest;
import com.dcl.responseDto.UserDto;

public interface UserService {

 UserDto register(RegisterRequest request);
 
  UserDto login(LoginRequest request);
  
  void deleteUserById(Integer userId);
  
  UserDto getUserById(Integer userId);
  
  List<UserDto> getAllUser();
}
