package com.dcl.service;

import com.dcl.enums.RoleType;
import com.dcl.responseDto.RoleDto;

public interface RoleService {

	RoleDto addRole(RoleType roleName);
	RoleDto getRoleByRoleName(RoleType roleName);
	RoleDto getRoleById(Integer roleId);
}
