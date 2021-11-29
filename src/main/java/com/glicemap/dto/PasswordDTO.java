package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PasswordDTO implements Serializable {

    private static final long serialVersionUID = 7558610581342906719L;

    @JsonProperty("oldPassword")
    private String oldPassword;

    @JsonProperty("newPassword")
    private String newPassword;
}
