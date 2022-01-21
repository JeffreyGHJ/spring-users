package com.jeffreyghj.springusers.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeffreyghj.springusers.dao.UserRepository;
import com.jeffreyghj.springusers.entity.Role;
import com.jeffreyghj.springusers.entity.User;

// This service gives us a UserDetails object that represents the User - we find them based on their unique email
@Service
public class MyUserDetailsService implements UserDetailsService {

	// I think this class is finished - if I'm adding something here it may be a mistake
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		user.getRoles().size();
		
		return new MyUserDetails(user);
	}
}
