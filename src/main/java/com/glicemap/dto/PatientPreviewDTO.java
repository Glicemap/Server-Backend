package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.glicemap.indicator.FrequencyIndicator;
import lombok.Data;

import java.io.Serializable;

@Data
public class PatientPreviewDTO implements Serializable {

    private static final long serialVersionUID = 7095896498633130012L;

    @JsonProperty("documentNumber")
    private String documentNumber;
    @JsonProperty("name")
    private String name;
    @JsonProperty("frequency")
    private FrequencyIndicator frequency;
    @JsonProperty("percentage")
    private String percentage;
}
