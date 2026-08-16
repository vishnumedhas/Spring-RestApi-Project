package com.dcl.responseDto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProfileDto {
 
	private Integer profileId;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String phone;
}
