package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DailyMeasuresDTO implements Serializable, Comparable<DailyMeasuresDTO> {
    private static final long serialVersionUID = 7568303989409707752L;
    @JsonProperty("date")
    private String date;
    @JsonProperty("measures")
    private List<MeasureDTO> measures;

    @Override
    public int compareTo(DailyMeasuresDTO dm) {
        if (this.date == null || dm.getDate() == null) {
            return 0;
        }
        return this.date.compareTo(dm.getDate());
    }
}
