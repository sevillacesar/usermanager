package com.nisum.usermanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {
    private String name;
    private String email;
    private String password;
    private List<PhoneDto> phones;
}
