package com.glicemap.builder;

import com.glicemap.dto.DailyMeasuresDTO;
import com.glicemap.dto.MeasureDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DailyMeasuresBuilder {
    private List<MeasureDTO> measures;
    private String date;

    public DailyMeasuresBuilder setDate(String date) {
        this.date = date;
        return this;
    }

    public DailyMeasuresBuilder setMeasures(List<MeasureDTO> measures) {
        this.measures = measures;
        return this;
    }

    public DailyMeasuresDTO build() {
        DailyMeasuresDTO dailyMeasuresDTO = new DailyMeasuresDTO();
        dailyMeasuresDTO.setMeasures(this.measures);
        dailyMeasuresDTO.setDate(this.date);
        return dailyMeasuresDTO;
    }
}
