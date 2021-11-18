package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PatientsListDTO implements Serializable {

    private static final long serialVersionUID = -2485764751619207149L;

    @JsonProperty("patients")
    private List<PatientPreviewDTO> patients;
}
