package com.jeffreyghj.springusers.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeffreyghj.springusers.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	public Role findRoleByName(String theRoleName);
}
