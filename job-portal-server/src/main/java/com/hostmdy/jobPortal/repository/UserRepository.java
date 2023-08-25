package com.hostmdy.jobPortal.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.jobPortal.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{

	Optional<User> findByUsername(String username);
	
	 Optional<User> findByFullname(String fullname);
}
