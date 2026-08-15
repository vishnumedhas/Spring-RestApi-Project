package com.dcl.reqdto;

import lombok.Data;

@Data
public class LoginRequest {

	private String email;
	
	private String password;
}
