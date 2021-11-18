package com.glicemap.builder;

import com.glicemap.dto.DailyMeasuresDTO;
import com.glicemap.dto.PatientMeasuresInfoDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class PatientMeasuresInfoBuilder {
    private String name;
    private List<String> low;
    private List<String> midlow;
    private List<String> midhigh;
    private List<String> high;
    private List<String> frequencys;
    private List<DailyMeasuresDTO> measures;

    public PatientMeasuresInfoBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PatientMeasuresInfoBuilder setLow(List<String> low) {
        this.low = low;
        return this;
    }

    public PatientMeasuresInfoBuilder setMidlow(List<String> midlow) {
        this.midlow = midlow;
        return this;
    }

    public PatientMeasuresInfoBuilder setMidhigh(List<String> midhigh) {
        this.midhigh = midhigh;
        return this;
    }

    public PatientMeasuresInfoBuilder setHigh(List<String> high) {
        this.high = high;
        return this;
    }

    public PatientMeasuresInfoBuilder setFrequencys(List<String> frequencys) {
        this.frequencys = frequencys;
        return this;
    }

    public PatientMeasuresInfoBuilder setMeasures(List<DailyMeasuresDTO> measures) {
        this.measures = measures;
        return this;
    }

    public PatientMeasuresInfoDTO build() {
        PatientMeasuresInfoDTO patientMeasuresInfoDTO = new PatientMeasuresInfoDTO();
        patientMeasuresInfoDTO.setName(name);
        patientMeasuresInfoDTO.setLow(low);
        patientMeasuresInfoDTO.setMidlow(midlow);
        patientMeasuresInfoDTO.setMidhigh(midhigh);
        patientMeasuresInfoDTO.setHigh(high);
        patientMeasuresInfoDTO.setFrequencys(frequencys);
        patientMeasuresInfoDTO.setMeasures(measures);
        return patientMeasuresInfoDTO;
    }
}
