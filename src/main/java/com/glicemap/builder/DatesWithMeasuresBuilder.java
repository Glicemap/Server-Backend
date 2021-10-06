package com.glicemap.builder;

import com.glicemap.dto.DatesWithMeasuresDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatesWithMeasuresBuilder {
    public DatesWithMeasuresDTO build(List<String> dates) {
        DatesWithMeasuresDTO datesWithMeasuresDTO = new DatesWithMeasuresDTO();
        datesWithMeasuresDTO.setDates(dates);
        return datesWithMeasuresDTO;
    }
}
