package com.sevilla.usermanager.service;

import com.sevilla.usermanager.dao.PhoneRepository;
import com.sevilla.usermanager.dao.UserRepository;
import com.sevilla.usermanager.dto.InfoUser;
import com.sevilla.usermanager.dto.UserDto;
import com.sevilla.usermanager.entity.Phone;
import com.sevilla.usermanager.entity.User;
import com.sevilla.usermanager.util.ConvertUtil;
import com.sevilla.usermanager.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return userRepository.findById(id).orElse(null);
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public Optional<InfoUser> findByEmail(String email) throws Exception {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			InfoUser infoUser = ConvertUtil.convertToInfoUser(user.get());
			return Optional.of(infoUser);
		}
		return Optional.empty();
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
