package com.dcl.reqdto;

import com.dcl.enums.RoleType;

import lombok.Data;

@Data
public class AddRoleRequest {

	private RoleType roleName;
}
