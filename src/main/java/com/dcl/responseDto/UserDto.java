package com.dcl.responseDto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDto {

	private Integer userId;
	private String email;
	private LocalDateTime createdAt;
}
