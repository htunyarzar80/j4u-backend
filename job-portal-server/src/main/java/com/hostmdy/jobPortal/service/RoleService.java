package com.hostmdy.jobPortal.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.jobPortal.domain.security.Role;

public interface RoleService {

	Optional<Role> findById(Long id);
	List<Role> findAll();
	
}
