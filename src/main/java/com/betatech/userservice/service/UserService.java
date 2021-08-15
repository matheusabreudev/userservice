package com.betatech.userservice.service;

import java.util.List;

import com.betatech.userservice.domain.AppUser;
import com.betatech.userservice.domain.Role;

public interface UserService {
	AppUser saveUser(AppUser appUser);
	Role saveRole(Role role);
	void addRoleToUser(String username, String roleName);
	AppUser getAppUser(String username);
	List<AppUser>getAppUsers();
}
