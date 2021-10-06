package com.glicemap.builder;

import com.glicemap.dto.DailyMeasuresDTO;
import com.glicemap.dto.MeasureDTO;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;

@Builder
@Component
public class DailyMeasuresBuilder {
    private List<MeasureDTO> measures;

    public DailyMeasuresBuilder setMeasures(List<MeasureDTO> measures) {
        this.measures = measures;
        return this;
    }

    public DailyMeasuresDTO build() {
        DailyMeasuresDTO dailyMeasuresDTO = new DailyMeasuresDTO();
        dailyMeasuresDTO.setMeasures(this.measures);
        return dailyMeasuresDTO;
    }
}
