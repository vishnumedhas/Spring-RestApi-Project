package com.dcl.reqdto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.dcl.enums.RoleType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {

	@NotBlank(message="email cannot be empty")
	private String email;
	@NotBlank(message="password cannot be empty")
	private String password;
	@NotBlank(message="first name cannot be empty")
	private String firstName;
	private String lastName;
	@NotNull(message="dob cannot be empty")
	private LocalDate dob;
	@NotBlank(message="phone number cannot be empty")
	@Length(min=10,max=10)
	private String phone;
	private RoleType roleName;
	
}
