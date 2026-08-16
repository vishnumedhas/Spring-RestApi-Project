package com.dcl.entity;

import java.util.List;

import com.dcl.enums.RoleType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
	
	//"This Java enum needs to be stored in the database. Decide how the enum value should be represented."
	//This tells Hibernate to store the enum name as text.
	@Enumerated(EnumType.STRING)
	private RoleType roleName=RoleType.CUSTOMER;
	
	@OneToMany(mappedBy = "role")
	private List<User>users;
}
