package com.sevilla.usermanager.controller;

import com.sevilla.usermanager.dto.InfoUser;
import com.sevilla.usermanager.dto.UserDto;
import com.sevilla.usermanager.entity.User;
import com.sevilla.usermanager.service.UserService;
import com.sevilla.usermanager.util.CommonValidate;
import com.sevilla.usermanager.util.ConvertUtil;
import com.sevilla.usermanager.util.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@Value("${app.email.pattern}")
	private String emailPattern;
	@Value("${app.password.pattern}")
	private String passwordPattern;

	private static final String VALID_MESSAGE = "Por favor verifica los siguientes campos";

	@PostMapping("/signup")
	public ResponseEntity<ResponseObject> addUser(@RequestBody UserDto userDto) {
		ResponseObject response = new ResponseObject();
		List<String> errors = new ArrayList<>();
		try {
			Optional<InfoUser> infoUser = userService.findByEmail(userDto.getEmail());
			if (infoUser.isPresent()) {
				errors.add("El email ya se encuentra registrado.");
			}
			if (!CommonValidate.patternMatches(userDto.getEmail(), emailPattern)) {
				errors.add("El formato del email es incorrecto.");
			}
			if (!CommonValidate.patternMatches(userDto.getPassword(), passwordPattern)) {
				errors.add("El formato del password es incorrecto.");
			}
			if (!errors.isEmpty()) {
				response.badResponse(errors, VALID_MESSAGE);
			} else {
				User user = userService.createUser(userDto);
				response.setCode(HttpStatus.CREATED);
				response.createdResponse(ConvertUtil.convertToInfoUser(user), "Usuario creado con éxito!");
			}
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
			Optional<InfoUser> infoUser = userService.findByEmail(userDto.getEmail());
			if (infoUser.isPresent()) {
				response.successResponse(infoUser);
			} else {
				response.badResponse("Usuario no encontrado");
			}
			return ResponseEntity.status(response.getCode()).body(response);
		} catch (Exception e) {
			response.badResponse(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable String id) {
		return userService.getUserById(id);
	}

	@GetMapping("/users")
	public ResponseEntity<ResponseObject> getAllUsers() {
		ResponseObject response = new ResponseObject();
		List users = userService.getUsers();
		if (CollectionUtils.isEmpty(users)) {
			response.badResponse("Users not found");
			response.setCode(HttpStatus.NOT_FOUND);
		} else {
			response.successResponse(users);
		}
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
