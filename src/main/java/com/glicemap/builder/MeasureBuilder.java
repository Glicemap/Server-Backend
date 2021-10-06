package com.glicemap.builder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.glicemap.dto.DailyMeasuresDTO;
import com.glicemap.dto.MeasureDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MeasureBuilder {
    public MeasureDTO build(String sugarLevel, String insulin, String situation, String observations){
        MeasureDTO measure = new MeasureDTO();
        measure.setSugarLevel(sugarLevel);
        measure.setInsulin(insulin);
        measure.setSituation(situation);
        measure.setObservations(observations);
        return measure;
    }
}
