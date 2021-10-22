package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = 3393057931847063195L;

    @JsonProperty("login")
    private String login;

    @JsonProperty("password")
    private String password;
}
