package com.hostmdy.jobPortal.service.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hostmdy.jobPortal.domain.User;
import com.hostmdy.jobPortal.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomUserDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> userOpt = userRepository.findByUsername(username);
		
		if(userOpt.isEmpty()) {
			throw new UsernameNotFoundException("Username not found!");
		}
		
		return  userOpt.get();
	}
	
	public User loadUserById(Long id) throws UsernameNotFoundException{
		Optional<User> userOpt = userRepository.findById(id);
		
		if(userOpt.isEmpty()) {
			throw new UsernameNotFoundException("UserId is not found!");
		}
		
		return userOpt.get();
	}

	
}
