package com.dcl.responseDto;

import com.dcl.enums.RoleType;

import lombok.Data;

@Data
public class RoleDto {

	private Integer roleId;
	private RoleType roleName;
}
