package com.jeffreyghj.springusers.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeffreyghj.springusers.dao.RoleRepository;
import com.jeffreyghj.springusers.dao.UserRepository;
import com.jeffreyghj.springusers.dto.UserDto;
import com.jeffreyghj.springusers.entity.User;

@Service
public class UserServiceImpl implements UserService {

	// Field injection
	@Autowired
	private UserRepository userRepository;	// DAO
	
	@Autowired
	private RoleRepository roleRepository;	// DAO
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	// @Transactional //not needed if using JpaRepositoy as opposed to DAO
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public List<User> findAllLastNameAsc() {
		return userRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public User findById(Long theId) {
		Optional<User> result = userRepository.findById(theId);
		
		User theUser = null;
		
		if ( result.isPresent() ) {
			theUser = result.get();
		}
		else {
			throw new RuntimeException("Did not find user with id: " + theId);
		}
		
		return theUser;
	}

	@Override
	public void save(User theUser) {
		userRepository.save(theUser);
	}

	@Override
	public void deleteById(Long theId) {
		userRepository.deleteById(theId);
	}

	@Override
	@Transactional
	public User createNewUser(UserDto userDto) {
		
		User user = new User();
		
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setRoles(Arrays.asList(roleRepository.findRoleByName("USER")));
		
		return userRepository.save(user);
	}
	
	//FOR LOGGIN IN - MAYBE FACTOR OUT INTO UserDetailsService.java
	// MOVED TO MyUserDetailsService.java
	/*
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(userName);
		
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), 
					getAuthorities(user.getRoles()));
	}
	
	private static List<GrantedAuthority> getAuthorities (List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
	*/
	
}
