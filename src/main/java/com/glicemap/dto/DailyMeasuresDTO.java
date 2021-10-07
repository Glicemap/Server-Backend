package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DailyMeasuresDTO implements Serializable {
    private static final long serialVersionUID = 7568303989409707752L;
    @JsonProperty("date")
    private String date;
    @JsonProperty("measures")
    private List<MeasureDTO> measures;
}
