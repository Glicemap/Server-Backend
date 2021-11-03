package com.glicemap.builder;

import com.glicemap.dto.PatientPreviewDTO;
import com.glicemap.dto.PatientsListDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class PatientsListBuilder {
    private List<PatientPreviewDTO> patients;

    private PatientsListBuilder() {
    }

    public static PatientsListBuilder aPatientsListDTO() {
        return new PatientsListBuilder();
    }

    public PatientsListBuilder setPatients(List<PatientPreviewDTO> patients) {
        this.patients = patients;
        return this;
    }

    public PatientsListDTO build() {
        PatientsListDTO patientsListDTO = new PatientsListDTO();
        patientsListDTO.setPatients(patients);
        return patientsListDTO;
    }
}
