package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MedicDTO implements Serializable {

    private static final long serialVersionUID = -2114029757896334651L;
    @JsonProperty("CRM")
    private String CRM;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("birthdate")
    private String birthdate;

}
