package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class GetPatientDTO implements Serializable {

    private static final long serialVersionUID = -1698957915996041012L;

    @JsonProperty("from")
    private String from;
    @JsonProperty("to")
    private String to;
}
