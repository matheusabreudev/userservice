package com.betatech.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betatech.userservice.domain.AppUser;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Long>{
	AppUser findByUsername(String username);
}
