package com.jeffreyghj.springusers.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeffreyghj.springusers.dao.UserRepository;
import com.jeffreyghj.springusers.entity.User;

// This service gives us a UserDetails object that represents the User - we find them based on their unique email
@Service
public class MyUserDetailsService implements UserDetailsService {

	// I think this class is finished - if I'm adding something here it may be a mistake
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail( email );
		
		if ( user == null ) {
			throw new UsernameNotFoundException("Invalid email or password.");
		}
		
		user.getRoles().size(); // Trick to initialize the roles here to avoid LazyInitializationException
		
		return new MyUserDetails( user );
	}
}
