package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserMedicInfoDTO implements Serializable {

    private static final long serialVersionUID = 6325374678891675272L;
    @JsonProperty("user")
    private UserDTO user;
    @JsonProperty("medic")
    private MedicDTO medic;

}
