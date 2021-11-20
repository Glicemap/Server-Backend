package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.glicemap.indicator.FrequencyIndicator;
import lombok.Data;

import java.io.Serializable;

@Data
public class GetPatientsDTO implements Serializable {

    private static final long serialVersionUID = 9100458028493780102L;

    @JsonProperty("name")
    private String name;
    @JsonProperty("from")
    private String from;
    @JsonProperty("to")
    private String to;
    @JsonProperty("frequency")
    private FrequencyIndicator frequency;
}
