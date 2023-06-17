package com.sevilla.usermanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String number;
    private String citycode;
    private String contrycode;
}
