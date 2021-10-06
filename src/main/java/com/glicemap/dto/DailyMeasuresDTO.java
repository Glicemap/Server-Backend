package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DailyMeasuresDTO implements Serializable {
    @JsonProperty("measures")
    private List<MeasureDTO> measures;
}
