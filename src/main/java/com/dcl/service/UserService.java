package com.dcl.service;

import com.dcl.reqdto.RegisterRequest;
import com.dcl.responseDto.UserDto;

public interface UserService {

 UserDto register(RegisterRequest request);
 

}
