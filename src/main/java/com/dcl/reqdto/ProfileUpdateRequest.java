package com.dcl.reqdto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProfileUpdateRequest {

	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String phone;
}
