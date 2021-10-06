package com.glicemap.builder;

import com.glicemap.dto.MeasureDTO;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Builder
@Component
public class MeasureBuilder {
    private String sugarLevel;
    private String insulin;
    private String situation;
    private String observations;

    public MeasureBuilder setSugarLevel(String sugarLevel) {
        this.sugarLevel = sugarLevel;
        return this;
    }

    public MeasureBuilder setInsulin(String insulin) {
        this.insulin = insulin;
        return this;
    }

    public MeasureBuilder setSituation(String situation) {
        this.situation = situation;
        return this;
    }

    public MeasureBuilder setObservations(String observations) {
        this.observations = observations;
        return this;
    }

    public MeasureDTO build() {
        MeasureDTO measure = new MeasureDTO();
        measure.setSugarLevel(this.sugarLevel);
        measure.setInsulin(this.insulin);
        measure.setSituation(this.situation);
        measure.setObservations(this.observations);
        return measure;
    }
}
