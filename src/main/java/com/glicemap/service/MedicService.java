package com.glicemap.service;

import com.glicemap.builder.MedicBuilder;
import com.glicemap.builder.PatientPreviewBuilder;
import com.glicemap.builder.PatientsListBuilder;
import com.glicemap.dto.GetPatientsDTO;
import com.glicemap.dto.MedicDTO;
import com.glicemap.dto.PatientPreviewDTO;
import com.glicemap.dto.PatientsListDTO;
import com.glicemap.indicator.FrequencyIndicator;
import com.glicemap.model.Medic;
import com.glicemap.repository.MedicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MedicService {

    Logger logger = LoggerFactory.getLogger(MedicService.class);

    @Autowired
    private PatientPreviewBuilder patientPreviewBuilder;

    @Autowired
    private PatientsListBuilder patientsListBuilder;

    @Autowired
    private MedicBuilder medicBuilder;

    @Autowired
    private MedicRepository medicRepository;

    public Medic getMedic(String CRM) {
        logger.info("MedicService - getMedic - Getting info from CRM [{}]", CRM);
        Medic medic = medicRepository.findByDocumentNumber(CRM);
        return medic;
    }

    public MedicDTO getMedicDTO(String CRM) {
        return medicBuilder.buildModel(this.getMedic(CRM));
    }

    public Boolean updateData(MedicDTO medicDTO) {
        logger.info("MedicService - updateData - Updating data for CRM [{}]", medicDTO.getCRM());
        // aqui att na base pelo CRM
        return Boolean.TRUE;
    }

    public String generateCode() {
        logger.info("MedicService - generateCode - Generating new code");
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final char[] charsArray = chars.toCharArray();
        char[] charsCode = new char[6];
        Random random = new Random();
        for (int i = 0; i < charsCode.length; ++i) {
            charsCode[i] = charsArray[random.nextInt(charsArray.length)];
        }
        String code = String.valueOf(charsCode);
        logger.info("MedicService - generateCode - Code generated [{}]", code);

        return code;
    }

    public PatientsListDTO getPatients(GetPatientsDTO getPatientsDTO) {
        List<PatientPreviewDTO> patients = new ArrayList<>();

        patients.add(patientPreviewBuilder.setName("Marco Aurélio") //
                .setFrequency(FrequencyIndicator.low) //
                .setPercentage("2") //
                .setDocumentNumber("45685145723") //
                .build());

        patients.add(patientPreviewBuilder.setName("Brian Nunes") //
                .setFrequency(FrequencyIndicator.high) //
                .setPercentage("97") //
                .setDocumentNumber("12345678956") //
                .build());

        patients.add(patientPreviewBuilder.setName("Victor Padula") //
                .setFrequency(FrequencyIndicator.medium) //
                .setPercentage("67") //
                .setDocumentNumber("78456213895") //
                .build());


        patients.add(patientPreviewBuilder.setName("Gustavo Trivelatto") //
                .setFrequency(FrequencyIndicator.high) //
                .setPercentage("88") //
                .setDocumentNumber("45678165222") //
                .build());

        return patientsListBuilder.setPatients(patients).build();
    }
}
