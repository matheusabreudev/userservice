package com.betatech.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betatech.userservice.domain.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{
	Role findByName(String name);
}
