package com.sevilla.usermanager.util;

import com.sevilla.usermanager.dto.InfoUser;
import com.sevilla.usermanager.dto.PhoneDto;
import com.sevilla.usermanager.dto.UserDto;
import com.sevilla.usermanager.entity.Phone;
import com.sevilla.usermanager.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertUtil {
    public static User convertUserToEntity(UserDto userDto) {
        User user = new User();
        user.setId(UuidUtil.UuidGenerate());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public static List<Phone> convertPhonesToEntities(List<PhoneDto> phoneDtoList, User user) {
        List<Phone> phoneList = phoneDtoList.stream()
                .map(p -> new Phone(UuidUtil.UuidGenerate(), p.getNumber(), p.getCitycode(), p.getContrycode(), user))
                .collect(Collectors.toList());
        return phoneList;
    }

    public static InfoUser convertToInfoUser(User user) {
        InfoUser infoUser = new InfoUser();
        infoUser.setId(user.getId());
        infoUser.setCreated(user.getCreated());
        infoUser.setModified(user.getModified());
        infoUser.setLastLogin(user.getLastLogin());
        infoUser.setToken(user.getToken());
        infoUser.setIsActive(user.getIsActive());
        return infoUser;
    }
}
