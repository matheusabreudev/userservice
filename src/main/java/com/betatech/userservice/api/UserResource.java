package com.betatech.userservice.api;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.betatech.userservice.domain.AppUser;
import com.betatech.userservice.domain.Role;
import com.betatech.userservice.service.UserService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController @RequiredArgsConstructor
@RequestMapping(path="/api")
public class UserResource {
	
	private final UserService userService;
	
	@GetMapping(path="/users")
	public ResponseEntity<List<AppUser>> getUsers(){
		return ResponseEntity.ok().body(userService.getAppUsers());	
	}
	
	@PostMapping(path="/user/save")
	public ResponseEntity<AppUser> saveUser(@RequestBody AppUser appUser){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
		return ResponseEntity.created(uri).body(userService.saveUser(appUser));
	}
	
	@PostMapping(path="/role/save")
	public ResponseEntity<Role> saveRole(@RequestBody Role role){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
		return ResponseEntity.created(uri).body(userService.saveRole(role));
	}
	
	@PostMapping(path="/role/addtouser")
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
		userService.addRoleToUser(form.getUsername(),form.getRolename());
		return ResponseEntity.ok().build();
	}
	
	@Data
	class RoleToUserForm{
		private String username;
		private String rolename;
		
	}
	
}
