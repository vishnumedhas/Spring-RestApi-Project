package com.dcl.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dcl.entity.Role;
import com.dcl.enums.RoleType;
import com.dcl.exception.AppException;
import com.dcl.repo.RoleRepository;
import com.dcl.responseDto.RoleDto;
import com.dcl.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository rrepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public RoleDto addRole(RoleType roleName) {
		Role r=rrepo.findByRoleName(roleName).orElse(null);
		if(r!=null) {
			throw new AppException("Role already exists",HttpStatus.CONFLICT);
		}
		r=new Role();
		r.setRoleName(roleName);
		r=rrepo.save(r);
		return mapper.map(r, RoleDto.class);
	}

	@Override
	public RoleDto getRoleByRoleName(RoleType roleName) {
		Role r=rrepo.findByRoleName(roleName).orElseThrow(()->new AppException("Role Not Found", HttpStatus.NOT_FOUND));
		RoleDto rdto=mapper.map(r, RoleDto.class);
		return rdto;
	}

	@Override
	public RoleDto getRoleById(Integer roleId) {
		Role r=rrepo.findById(roleId).orElseThrow(()->new AppException("Role Not Found", HttpStatus.NOT_FOUND));
		return mapper.map(r, RoleDto.class);
	}

}
