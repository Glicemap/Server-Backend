package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PatientMeasuresInfoDTO implements Serializable {

    private static final long serialVersionUID = 3245481982694192605L;

    @JsonProperty("name")
    private String name;
    @JsonProperty("low")
    private List<String> low;
    @JsonProperty("midlow")
    private List<String> midlow;
    @JsonProperty("midhigh")
    private List<String> midhigh;
    @JsonProperty("high")
    private List<String> high;
    @JsonProperty("frequencys")
    private List<String> frequencys;
    @JsonProperty("measures")
    private List<DailyMeasuresDTO> measures;
}
