package com.glicemap.builder;

import com.glicemap.dto.DailyMeasuresDTO;
import com.glicemap.dto.MeasureDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DailyMeasuresBuilder {
    public DailyMeasuresDTO build(List<MeasureDTO> measures){
        DailyMeasuresDTO dailyMeasuresDTO = new DailyMeasuresDTO();
        dailyMeasuresDTO.setMeasures(measures);
        return dailyMeasuresDTO;
    }
}
