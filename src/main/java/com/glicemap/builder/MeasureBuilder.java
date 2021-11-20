package com.glicemap.builder;

import com.glicemap.dto.MeasureDTO;
import com.glicemap.indicator.SituationsIndicator;
import com.glicemap.model.Measure;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public MeasureDTO buildModel(Measure measure) {
        MeasureDTO measureDTO = new MeasureDTO();
        measureDTO.setSugarLevel(Integer.toString(measure.getSugarLevel()));
        measureDTO.setInsulin(Integer.toString(measure.getInsulin()));
        measureDTO.setSituation(measure.getSituation().getSituation());
        measureDTO.setObservations(measure.getObservations());
        return measureDTO;
    }

    public List<MeasureDTO> buildModelList(List<Measure> measures) {
        List<MeasureDTO> measuresDTO = new ArrayList<>();
        for (Measure measure : measures){
            measuresDTO.add(this.buildModel(measure));
        }
        return measuresDTO;
    }
}
