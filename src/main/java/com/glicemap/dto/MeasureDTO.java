package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MeasureDTO implements Serializable {
    @JsonProperty("sugarLevel")
    private String sugarLevel;

    @JsonProperty("insulin")
    private String insulin;

    @JsonProperty("situation")
    private String situation;

    @JsonProperty("observations")
    private String observations;
}
