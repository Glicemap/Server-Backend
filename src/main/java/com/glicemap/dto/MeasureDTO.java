package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MeasureDTO implements Serializable {

    private static final long serialVersionUID = 4064378649469122652L;

    @JsonProperty("sugarLevel")
    private String sugarLevel;

    @JsonProperty("insulin")
    private String insulin;

    @JsonProperty("situation")
    private String situation;

    @JsonProperty("observations")
    private String observations;
}
