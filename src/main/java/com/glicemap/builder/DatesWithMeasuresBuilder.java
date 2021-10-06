package com.glicemap.builder;

import com.glicemap.dto.DatesWithMeasuresDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatesWithMeasuresBuilder {
    private List<String> dates;

    public DatesWithMeasuresBuilder setDates(List<String> dates) {
        this.dates = dates;
        return this;
    }

    public DatesWithMeasuresDTO build() {
        DatesWithMeasuresDTO datesWithMeasuresDTO = new DatesWithMeasuresDTO();
        datesWithMeasuresDTO.setDates(this.dates);
        return datesWithMeasuresDTO;
    }
}
