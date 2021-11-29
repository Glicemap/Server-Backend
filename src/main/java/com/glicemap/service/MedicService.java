package com.glicemap.service;

import com.glicemap.builder.MedicBuilder;
import com.glicemap.builder.PatientPreviewBuilder;
import com.glicemap.builder.PatientsListBuilder;
import com.glicemap.dto.*;
import com.glicemap.exception.BaseBusinessException;
import com.glicemap.indicator.FrequencyIndicator;
import com.glicemap.model.Medic;
import com.glicemap.model.User;
import com.glicemap.repository.MedicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class MedicService {

    final Logger logger = LoggerFactory.getLogger(MedicService.class);

    @Autowired
    private PatientPreviewBuilder patientPreviewBuilder;

    @Autowired
    private PatientsListBuilder patientsListBuilder;

    @Autowired
    private MedicBuilder medicBuilder;

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MeasureService measureService;

    public Medic getMedic(String CRM) {
        logger.info("MedicService - getMedic - Getting info from CRM [{}]", CRM);
        Medic medic = medicRepository.findByCRM(CRM);
        if (medic == null) {
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

    public String login(LoginDTO loginDTO) {
        logger.info("MedicService - login - loginDTO [{}]", loginDTO);
        Medic medic = medicRepository.findByCRM(loginDTO.getLogin());
        if (medic == null) {
            medic = medicRepository.findByEmail(loginDTO.getLogin());
            if (medic == null) {
                throw new BaseBusinessException("MEDIC_NOT_FOUND_LOGIN_ERROR_0001");
            }
        }
        if (medic.getPassword().equals(loginDTO.getPassword())) {
            return medic.getCRM();
        } else {
            throw new BaseBusinessException("PASSWORD_UNMATCHED_LOGIN_ERROR_0001");
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

        if (medicRepository.findByCRM(medicDTO.getCRM()) != null) {
            logger.error("MedicService - SignUp Error - Account already exists - CRM [{}]", medicDTO.getCRM());
            throw new BaseBusinessException("SIGNUP_ERROR_0002");
        }

        Medic newMedic = new Medic(medicDTO.getCRM(), medicDTO.getName(), medicDTO.getEmail(), medicDTO.getPassword());

        medicRepository.save(newMedic);

        return Boolean.TRUE;
    }

    public Boolean updateData(MedicDTO medicDTO) {
        logger.info("MedicService - updateData - Updating data for CRM [{}]", medicDTO.getCRM());
        if ((this.isNullOrEmpty(medicDTO.getCRM())) || //
                (this.isNullOrEmpty(medicDTO.getEmail())) || //
                (this.isNullOrEmpty(medicDTO.getName()))) {
            logger.error("MedicService - Update Info Error - Data Incomplete - MedicDTO [{}]", medicDTO);
            throw new BaseBusinessException("UPDATE_INFO_ERROR_0001");
        }

        Medic medic = this.getMedic(medicDTO.getCRM());
        logger.info("MedicService - update medic - Medic found: [{}]", medic);
        if (medic == null) {
            logger.error("MedicService - Update Info Error - Medic not found - CRM [{}]", medicDTO.getCRM());
            throw new BaseBusinessException("UPDATE_INFO_ERROR_0002");
        } else {
            this.updateMedic(medic, medicDTO);
            return Boolean.TRUE;
        }
    }

    public Boolean updatePassword(String CRM, PasswordDTO passwordDTO) {
        logger.info("MedicService - update Password - Updating password for CRM [{}]", CRM);
        Medic medic = this.getMedic(CRM);
        logger.info("MedicService - update Password - Medic found: [{}]", medic);
        if (medic == null) {
            logger.error("MedicService - Update Info Error - Medic not found - CRM [{}]", CRM);
            throw new BaseBusinessException("UPDATE_INFO_ERROR_0002");
        } else {
            if (medic.getPassword().equals(passwordDTO.getOldPassword())) {
                this.updatePassword(medic, passwordDTO.getNewPassword());
                return Boolean.TRUE;
            } else {
                throw new BaseBusinessException("UPDATE_PASSWORD_ERROR_0001");
            }
        }
    }

    private void updatePassword(Medic medic, String password) {
        medic.setPassword(password);
        logger.info("MedicService - update password - Medic updated: [{}]", medic);
        medicRepository.save(medic);
    }

    private void updateMedic(Medic medic, MedicDTO medicDTO) {
        medic.setName(medicDTO.getName());
        medic.setEmail(medicDTO.getEmail());
        logger.info("MedicService - update medic - Medic updated: [{}]", medic);
        medicRepository.save(medic);
    }

    public PatientsListDTO getPatients(String CRM, GetPatientsDTO getPatientsDTO) throws ParseException {
        //Busca lista de pacientes usando filtros de nome e datas caso necessarios
        List<User> patientList = this.getListPatientFiltered(CRM, getPatientsDTO);

        //montar DTO calculando frequências e porcentagens
        PatientsListDTO patientsList = this.buildPatientWithFrequencyAndPercentage(patientList);

        //retorna filtrado por frequência ou não a depender do que foi pedido
        if (getPatientsDTO.getFrequency() == null) {
            return patientsList;
        } else {
            return filterFrequency(patientsList, getPatientsDTO.getFrequency());
        }
    }

    private List<User> getListPatientFiltered(String CRM, GetPatientsDTO getPatientsDTO) throws ParseException {
        Medic medic = this.getMedic(CRM);

        Date dateFrom;
        Date dateTo;

        if (getPatientsDTO.getFrom() == null) {
            dateFrom = this.stringToDate("01-01-2000");
        } else {
            dateFrom = this.stringToDate(getPatientsDTO.getFrom());
        }

        if (getPatientsDTO.getTo() == null) {
            dateTo = new Date(new java.util.Date(System.currentTimeMillis()).getTime());
        } else {
            dateTo = this.stringToDate(getPatientsDTO.getTo());
        }

        List<User> patientList;
        if (getPatientsDTO.getName() != null) {
            patientList = userService.findByNameLikeAndDatesAndMedic(medic, getPatientsDTO.getName().toLowerCase(), dateFrom, dateTo);
        } else {
            patientList = userService.findByDatesAndMedic(medic, dateFrom, dateTo);
        }

        return patientList;
    }

    private PatientsListDTO buildPatientWithFrequencyAndPercentage(List<User> patients) {
        List<PatientPreviewDTO> patientsList = new ArrayList<>();

        for (User patient : patients) {
            PercentagesDTO percentages = measureService.getMeasuresPercentageFromLastMonth(patient);
            String percentageRightString = Integer.toString(percentages.getPercentageRight());

            FrequencyIndicator frequency;

            if (percentages.getPercentageTotal() > 80) {
                frequency = FrequencyIndicator.high;
            } else if (percentages.getPercentageTotal() > 60) {
                frequency = FrequencyIndicator.medium;
            } else {
                frequency = FrequencyIndicator.low;
            }

            PatientPreviewDTO patientPreviewDTO = patientPreviewBuilder.setDocumentNumber(patient.getDocumentNumber())
                    .setName(patient.getFullName())
                    .setPercentage(percentageRightString)
                    .setFrequency(frequency)
                    .build();

            patientsList.add(patientPreviewDTO);
        }

        return patientsListBuilder.setPatients(patientsList).build();
    }

    private Date stringToDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateUtil = sdf.parse(dateString);
        return new Date(dateUtil.getTime());
    }

    private PatientsListDTO filterFrequency(PatientsListDTO patients, FrequencyIndicator frequency) {
        List<PatientPreviewDTO> filteredPatientsList = new ArrayList<>();

        for (PatientPreviewDTO patient : patients.getPatients()) {
            if (frequency.equals(patient.getFrequency())) {
                filteredPatientsList.add(patient);
            }
        }

        return patientsListBuilder.setPatients(filteredPatientsList).build();
    }
}
