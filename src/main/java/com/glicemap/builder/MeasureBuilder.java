package com.glicemap.builder;

import com.glicemap.dto.MeasureDTO;
import org.springframework.stereotype.Component;

@Component
public final class MeasureBuilder {
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
        MeasureDTO measureDTO = new MeasureDTO();
        measureDTO.setSugarLevel(sugarLevel);
        measureDTO.setInsulin(insulin);
        measureDTO.setSituation(situation);
        measureDTO.setObservations(observations);
        return measureDTO;
    }
}
