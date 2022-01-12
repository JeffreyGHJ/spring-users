package com.jeffreyghj.springusers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeffreyghj.springusers.dao.RoleRepository;
import com.jeffreyghj.springusers.entity.Role;

@Component
public class DataLoader {

	private RoleRepository roleRepo;
	
	@Autowired
	public DataLoader(RoleRepository roleRepo) {
		this.roleRepo = roleRepo;
		LoadRoles();
	}
	
	private void LoadRoles() {
		//Role roleAdmin = new Role("ADMIN");
		//Role roleUser = new Role("USER");
		
		if ( roleRepo.findRoleByName("ADMIN") == null ) {
			roleRepo.save(new Role("ADMIN"));
		}
		if ( roleRepo.findRoleByName("USER") == null ) {
			roleRepo.save(new Role("USER"));
		}

	}
	
}
