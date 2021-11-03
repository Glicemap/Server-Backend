package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UpdatePasswordDTO implements Serializable {

    private static final long serialVersionUID = -372185693323511396L;

    @JsonProperty("id")
    private String id;
    @JsonProperty("current")
    private String current;
    @JsonProperty("newPassword")
    private String newPassword;
}
