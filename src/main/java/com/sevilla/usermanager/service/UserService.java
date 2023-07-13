package com.sevilla.usermanager.service;

import com.sevilla.usermanager.dto.UserDto;
import com.sevilla.usermanager.entity.User;
import com.sevilla.usermanager.util.ResponseObject;

public interface UserService {
    User createUser(UserDto userDto);
    User getUserById(String id);
    ResponseObject getUsers();
    ResponseObject findUser(UserDto userDto);
    ResponseObject findUserByEmail(String email);
    User updateUser(User user);
    String deleteUserById(String id);
}
