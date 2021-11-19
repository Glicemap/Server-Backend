package com.glicemap.service;

import com.glicemap.builder.MedicBuilder;
import com.glicemap.builder.PatientPreviewBuilder;
import com.glicemap.builder.PatientsListBuilder;
import com.glicemap.dto.*;
import com.glicemap.exception.BaseBusinessException;
import com.glicemap.indicator.FrequencyIndicator;
import com.glicemap.model.Medic;
import com.glicemap.model.MedicInvite;
import com.glicemap.repository.MedicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    @Autowired
    private MedicInviteService medicInviteService;

    public Medic getMedic(String CRM) {
        logger.info("MedicService - getMedic - Getting info from CRM [{}]", CRM);
        Medic medic = medicRepository.findByCRM(CRM);
        if (medic == null){
            logger.error("MedicService - getMedic Error - Medic not found - CRM [{}]", CRM);
            throw new BaseBusinessException("GET_MEDIC_ERROR_0001");
        }
        return medic;
    }

    public MedicDTO getMedicDTO(String CRM) {
        return medicBuilder.buildModel(this.getMedic(CRM));
    }

    private boolean isNullOrEmpty(String txt) {
        return txt == null || txt.isEmpty() || txt.trim().isEmpty();
    }

    public Boolean login(LoginDTO loginDTO){
        logger.info("MedicService - login - loginDTO [{}]", loginDTO);
        Medic medic = medicRepository.findByCRM(loginDTO.getLogin());
        if (medic == null) {
            medic = medicRepository.findByEmail(loginDTO.getLogin());
            if (medic == null) {
                return Boolean.FALSE;
            } else {
                if (medic.getPassword().equals(loginDTO.getPassword())) {
                    return Boolean.TRUE;
                } else {
                    return Boolean.FALSE;
                }
            }
        } else {
            if (medic.getPassword().equals(loginDTO.getPassword())) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }
    }

    public Boolean signUp(MedicDTO medicDTO) {
        logger.info("MedicService - signUp - MedicDTO [{}]", medicDTO);

        if ((this.isNullOrEmpty(medicDTO.getCRM())) || //
                (this.isNullOrEmpty(medicDTO.getEmail())) || //
                (this.isNullOrEmpty(medicDTO.getPassword())) || //
                (this.isNullOrEmpty(medicDTO.getName()))) {
            logger.error("MedicService - SignUp Error - Data Incomplete - UserDTO [{}]", medicDTO);
            throw new BaseBusinessException("SIGNUP_ERROR_0001");
        }

        if (medicRepository.findByCRM(medicDTO.getCRM()) != null){
            logger.error("MedicService - SignUp Error - Account already exists - CRM [{}]", medicDTO.getCRM());
            throw new BaseBusinessException("SIGNUP_ERROR_0002");
        }

        Medic newMedic = new Medic(medicDTO.getCRM(), medicDTO.getName(), medicDTO.getEmail(), medicDTO.getPassword());

        medicRepository.save(newMedic);

        return Boolean.TRUE;
    }

    public Boolean updateData(MedicDTO medicDTO) {
        logger.info("MedicService - updateData - Updating data for CRM [{}]", medicDTO.getCRM());
        // aqui att na base pelo CRM
        return Boolean.TRUE;
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
