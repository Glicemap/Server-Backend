package com.glicemap.builder;

import com.glicemap.dto.PatientPreviewDTO;
import com.glicemap.indicator.FrequencyIndicator;
import org.springframework.stereotype.Component;

@Component
public final class PatientPreviewBuilder {
    private String documentNumber;
    private String name;
    private FrequencyIndicator frequency;
    private String percentage;

    public PatientPreviewBuilder setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
        return this;
    }

    public PatientPreviewBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PatientPreviewBuilder setFrequency(FrequencyIndicator frequency) {
        this.frequency = frequency;
        return this;
    }

    public PatientPreviewBuilder setPercentage(String percentage) {
        this.percentage = percentage;
        return this;
    }

    public PatientPreviewDTO build() {
        PatientPreviewDTO patientPreviewDTO = new PatientPreviewDTO();
        patientPreviewDTO.setDocumentNumber(documentNumber);
        patientPreviewDTO.setName(name);
        patientPreviewDTO.setFrequency(frequency);
        patientPreviewDTO.setPercentage(percentage);
        return patientPreviewDTO;
    }
}
