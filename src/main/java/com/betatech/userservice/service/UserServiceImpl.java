package com.betatech.userservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.betatech.userservice.domain.AppUser;
import com.betatech.userservice.domain.Role;
import com.betatech.userservice.repo.RoleRepo;
import com.betatech.userservice.repo.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService{
	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = userRepo.findByUsername(username);
		if(appUser == null) {
			log.error("user not found in the database");
			throw new UsernameNotFoundException("user not found in the database");
		}else {
			log.info("user found in the database: {}", username);
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		appUser.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new User(appUser.getUsername(), appUser.getPassword(), authorities);
	}
	
	@Override
	public AppUser saveUser(AppUser appUser) {
		log.info("Saving new user {} to the database", appUser.getName());
		return userRepo.save(appUser);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("Saving new role {} to the database", role.getName());
		return roleRepo.save(role);
	}
	
	@Override
	public void addRoleToUser(String username, String roleName) {
		log.info("Adding role {} to user {}", roleName, username);
		AppUser appUser = userRepo.findByUsername(username);
		Role role = roleRepo.findByName(roleName);
		appUser.getRoles().add(role);
		
	}
	
	@Override
	public AppUser getAppUser(String username) {
		log.info("Fetching user {}", username);
		return userRepo.findByUsername(username);
	}

	@Override
	public List<AppUser> getAppUsers() {
		log.info("Fetching all users");
		return userRepo.findAll();
	}



}
