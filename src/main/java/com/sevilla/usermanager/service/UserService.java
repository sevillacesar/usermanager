package com.sevilla.usermanager.service;

import com.sevilla.usermanager.dao.PhoneRepository;
import com.sevilla.usermanager.dao.UserRepository;
import com.sevilla.usermanager.dto.InfoUser;
import com.sevilla.usermanager.dto.UserDto;
import com.sevilla.usermanager.entity.Phone;
import com.sevilla.usermanager.entity.User;
import com.sevilla.usermanager.util.CommonValidate;
import com.sevilla.usermanager.util.ConvertUtil;
import com.sevilla.usermanager.jwt.JwtTokenUtil;
import com.sevilla.usermanager.util.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PhoneRepository phoneRepository;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Value("${app.email.pattern}")
	private String emailPattern;
	@Value("${app.password.pattern}")
	private String passwordPattern;

	private static final String VALID_MESSAGE = "Por favor verifica los siguientes campos";

	public User createUser(UserDto userDto) {
		User user = ConvertUtil.convertUserToEntity(userDto);
		user.setIsActive(true);
		user.setToken(jwtTokenUtil.generateToken(userDto.getName()));
		user.setCreated(new Date());
		user.setLastLogin(user.getCreated());
		User newUser = userRepository.save(user);
		List<Phone> phoneList = ConvertUtil.convertPhonesToEntities(userDto.getPhones(), newUser);
		for (Phone phone: phoneList) {
			phoneRepository.save(phone);
		}
		return newUser;
	}

	public User getUserById(String id) {
		return userRepository.findById(id).orElseThrow();
	}

	public ResponseObject getUsers() {
		ResponseObject response = new ResponseObject();
		List<User> users = userRepository.findAll();
		if (CollectionUtils.isEmpty(users)) {
			response.badResponse(HttpStatus.NOT_FOUND,"Users not found");
			response.setCode(HttpStatus.NOT_FOUND);
		} else {
			response.successResponse(users);
		}
		return response;
	}

	public ResponseObject findUser(UserDto userDto) {
		ResponseObject response = new ResponseObject();
		List<String> errors = new ArrayList<>();
		Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
		if (optionalUser.isPresent()) {
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
			User user = createUser(userDto);
			response.setCode(HttpStatus.CREATED);
			response.createdResponse(ConvertUtil.convertToInfoUser(user), "Usuario creado con éxito!");
		}
		return response;
	}

	public ResponseObject findUserByEmail(String email) {
		ResponseObject response = new ResponseObject();
		List<String> errors = new ArrayList<>();
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			errors.add("El email no se encuentra registrado.");
		}
		if (!errors.isEmpty()) {
			response.badResponse(errors, VALID_MESSAGE);
		} else {
			User user = optionalUser.get();
			response.setCode(HttpStatus.OK);
			response.successResponse(ConvertUtil.convertToInfoUser(user), "Usuario encontrado");
		}
		return response;
	}
	
	public User updateUser(User user) {
		User oldUser = null;
		Optional<User> optionaluser = userRepository.findById(user.getId().toString());
		if(optionaluser.isPresent()) {
			oldUser=optionaluser.get();
			oldUser.setName(user.getName());
			oldUser.setEmail(user.getEmail());
			userRepository.save(oldUser);
		} else {
			return new User();
		}
		return oldUser;
	}
	
	public String deleteUserById(String id) {
		Optional<User> optionaluser = userRepository.findById(id);
		if(optionaluser.isPresent()) {
			userRepository.deleteById(id);
		} else {
			return "Usuario no existe";
		}
		return "Usuario fue eliminado";
	}

}
