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
		user.setEmail(userDto.getEmail().toLowerCase());
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setRoles(Arrays.asList(roleRepository.findRoleByName("USER")));
		
		return userRepository.save(user);
	}
	
	@Override
	public boolean emailExists( String email ) {				
		User user = userRepository.findByEmail( email.toLowerCase() );
		
		if ( user == null ) {
			System.out.println( getClass() + " -- email not found");
		} else {
			System.out.println( getClass() + " -- user found by email: " + email );
		}
		
		return user != null;
	}
	
}
