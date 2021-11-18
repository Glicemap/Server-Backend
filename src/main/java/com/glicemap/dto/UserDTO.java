package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 8048740251319467334L;
    @JsonProperty("documentNumber")
    private String documentNumber;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("birthdate")
    private Date birthdate;
    @JsonProperty("height")
    private int height;
    @JsonProperty("weight")
    private float weight;
    @JsonProperty("sugarMin")
    private int sugarMin;
    @JsonProperty("sugarMax")
    private int sugarMax;
    @JsonProperty("crmMedic")
    private String crmMedic;
}
