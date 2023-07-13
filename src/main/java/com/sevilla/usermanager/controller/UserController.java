package com.sevilla.usermanager.controller;

import com.sevilla.usermanager.dto.UserDto;
import com.sevilla.usermanager.entity.User;
import com.sevilla.usermanager.service.UserService;
import com.sevilla.usermanager.util.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<ResponseObject> addUser(@RequestBody UserDto userDto) {
		ResponseObject response = new ResponseObject();
		try {
			response = userService.findUser(userDto);
		} catch (Exception e) {
			response.badResponse(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.status(response.getCode()).body(response);
	}

	@PostMapping("/get_user_by_email")
	public ResponseEntity<ResponseObject> getUserByEmail(@RequestBody UserDto userDto) {
		ResponseObject response = new ResponseObject();
		try {
			response = userService.findUserByEmail(userDto.getEmail());
		} catch (Exception e) {
			response.badResponse(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.status(response.getCode()).body(response);
	}

	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable String id) {
		return userService.getUserById(id);
	}

	@GetMapping("/users")
	public ResponseEntity<ResponseObject> getAllUsers() {
		ResponseObject response = userService.getUsers();
		return ResponseEntity.status(response.getCode()).body(response);
	}
	
	@PutMapping("/updateuser")
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable String id) {
		return userService.deleteUserById(id);
	}
}
