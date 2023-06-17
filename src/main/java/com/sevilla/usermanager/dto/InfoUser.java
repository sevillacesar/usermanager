package com.sevilla.usermanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InfoUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Date created;
    private Date modified;
    @JsonProperty("last_login")
    private Date lastLogin;
    private String token;
    private Boolean isActive;
}
