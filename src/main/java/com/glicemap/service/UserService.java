package com.glicemap.service;

import com.glicemap.builder.DailyMeasuresBuilder;
import com.glicemap.builder.MeasureBuilder;
import com.glicemap.builder.PatientMeasuresInfoBuilder;
import com.glicemap.dto.*;
import com.glicemap.exception.BaseBusinessException;
import com.glicemap.indicator.FrequencyIndicator;
import com.glicemap.indicator.SituationsIndicator;
import com.glicemap.model.User;
import com.glicemap.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private MedicService medicService;

    @Autowired
    private PatientMeasuresInfoBuilder patientMeasuresInfoBuilder;

    @Autowired
    private DailyMeasuresBuilder dailyMeasuresBuilder;

    @Autowired
    private MeasureBuilder measureBuilder;

    //@Autowired
    //private UserRepository userRepository;

    public Boolean login(LoginDTO loginDTO){
//        User usuario = userRepository.findByDocumentNumber(loginDTO.getLogin());
//        if (usuario == null){
//            usuario = userRepository.findByEmail(loginDTO.getLogin());
//            if (usuario == null) {
//                return Boolean.FALSE;
//            } else {
//                if (usuario.getPassword().equals(loginDTO.getPassword())){
//                    return Boolean.TRUE;
//                } else {
//                    return Boolean.FALSE;
//                }
//            }
//        } else {
//            if (usuario.getPassword().equals(loginDTO.getPassword())){
//                return Boolean.TRUE;
//            } else {
//                return Boolean.FALSE;
//            }
//        }

        return Boolean.TRUE;
    }

    private boolean isNullOrEmpty(String txt) {
        return txt == null || txt.isEmpty() || txt.trim().isEmpty();
    }

    public Boolean signUp(UserDTO userDTO) throws BaseBusinessException {
        if  ((this.isNullOrEmpty(userDTO.getDocumentNumber())) || //
            (this.isNullOrEmpty(userDTO.getBirthdate())) || //
            (this.isNullOrEmpty(userDTO.getEmail())) || //
            (this.isNullOrEmpty(userDTO.getName())) || //
            (this.isNullOrEmpty(userDTO.getPassword())) || //
            (this.isNullOrEmpty(Float.toString(userDTO.getWeight()))) || //
            (this.isNullOrEmpty(Integer.toString(userDTO.getHeight()))) || //
            (this.isNullOrEmpty(Integer.toString(userDTO.getSugarMax()))) || //
            (this.isNullOrEmpty(Integer.toString(userDTO.getSugarMin())))){
             logger.error("UserService - SignUp Error - Data Incomplete - UserDTO [{}]", userDTO);
             throw new BaseBusinessException("SIGNUP_ERROR_0001");
        }

        //boolean userAlreadyExists = (userRepository.findByDocumentNumber(userDTO.getDocumentNumber()) == null);
        //if (userAlreadyExists){
        // logger.error("UserService - SignUp Error - Account already exists - DocumentNumber [{}]", userDTO.getDocumentNumber());
        // throw new BaseBusinessException("SIGNUP_ERROR_0002");
        //}

        return Boolean.TRUE;
    }

    public Boolean deleteMedic(String documentNumber) throws BaseBusinessException {
//        User user = userRepository.findByDocumentNumber(documentNumber);
//        if (user == null){
//            logger.error("UserService - deleteMedic Error - User doesn't exists - DocumentNumber [{}]", documentNumber);
//            throw new BaseBusinessException("DELETE_MEDIC_ERROR_0001");
//        }
//
//        if (user.getMedic() == null){
//            logger.error("UserService - deleteMedic Error - User doesn't have a doctor - DocumentNumber [{}]", documentNumber);
//            throw new BaseBusinessException("DELETE_MEDIC_ERROR_0002");
//        }
//
//        user.setMedic(null);
//        userRepository.save(user);
//        return Boolean.TRUE;

        return Boolean.TRUE;
    }

    public Boolean addMedic(String documentNumber, String medicCRM) throws BaseBusinessException {
//        User user = userRepository.findByDocumentNumber(documentNumber);
//        if (user == null){
//            logger.error("UserService - addMedic Error - User doesn't exists - DocumentNumber [{}]", documentNumber);
//            throw new BaseBusinessException("ADD_MEDIC_ERROR_0001");
//        }
//
//        Medic medic = medicService.getMedic(medicCRM);
//        if (medic == null){
//            logger.error("UserService - addMedic Error - Medic doesn't exists - medicCRM [{}]", medicCRM);
//            throw new BaseBusinessException("ADD_MEDIC_ERROR_0002");
//        }
//
//        if (user.getMedic() != null){
//            logger.error("UserService - addMedic Error - User already have a doctor - DocumentNumber [{}] - Actual Doctor CRM [{}]", documentNumber, user.getMedic().getCRM());
//            throw new BaseBusinessException("ADD_MEDIC_ERROR_0003");
//        }
//
//        user.setMedic(medic);
//        userRepository.save(user);
//        return Boolean.TRUE;

        return Boolean.TRUE;
    }

    public Boolean updateInfo(UserDTO userDTO) throws BaseBusinessException {
        if  ((this.isNullOrEmpty(userDTO.getDocumentNumber())) || //
                (this.isNullOrEmpty(userDTO.getBirthdate())) || //
                (this.isNullOrEmpty(userDTO.getEmail())) || //
                (this.isNullOrEmpty(userDTO.getName())) || //
                (this.isNullOrEmpty(userDTO.getPassword())) || //
                (this.isNullOrEmpty(Float.toString(userDTO.getWeight()))) || //
                (this.isNullOrEmpty(Integer.toString(userDTO.getHeight()))) || //
                (this.isNullOrEmpty(Integer.toString(userDTO.getSugarMax()))) || //
                (this.isNullOrEmpty(Integer.toString(userDTO.getSugarMin())))){
            logger.error("UserService - Update Info Error - Data Incomplete - UserDTO [{}]", userDTO);
            throw new BaseBusinessException("UPDATE_INFO_ERROR_0001");
        }

//        User usuario = userRepository.findByDocumentNumber(userDTO.getDocumentNumber());
//
//        if (usuario == null){
//            logger.error("UserService - Update Info Error - User not found - Document Number [{}]", userDTO.getDocumentNumber());
//            throw new BaseBusinessException("UPDATE_INFO_ERROR_0002");
//        } else {
//            usuario = this.updateUsuario(usuario, usuarioDTO);
//            return Boolean.TRUE;
//        }
//        userRepository.save(usuario);


        return Boolean.TRUE;
    }

    private User updateUsuario(User usuario, UserDTO userDTO){
        usuario.setSugarMin(userDTO.getSugarMin());
        usuario.setSugarMax(userDTO.getSugarMax());
        usuario.setWeight(userDTO.getWeight());
        usuario.setPassword(userDTO.getPassword());
        usuario.setEmail(userDTO.getEmail());
        usuario.setBirthdate(userDTO.getBirthdate());
        usuario.setHeight(userDTO.getHeight());

        return usuario;
    }

    public User getUserInfo(String documentNumber) {
        // User user = userRepository.findByDocumentNumber(userDTO.getDocumentNumber());
        User user = new User();
        user.setDocumentNumber(documentNumber);
        user.setBirthdate("2000-04-02");
        user.setName("Marco Aurélio");
        user.setEmail("marco.aurelio@gmail.com");
        user.setPassword("mrc42069");
        user.setHeight(165);
        user.setWeight(50.0F);
        user.setSugarMin(69);
        user.setSugarMax(420);
        user.setMedic(medicService.getMedic("42"));
        return user;
    }

    public PatientMeasuresInfoDTO getMeasuresInfo(GetPatientDTO getPatientDTO){
        List<String> listLow = new ArrayList<>();
        List<String> listMidLow = new ArrayList<>();
        List<String> listMidHigh = new ArrayList<>();
        List<String> listHigh = new ArrayList<>();
        List<String> listFrequencys = new ArrayList<>();
        List<DailyMeasuresDTO> dailyMeasures = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            dailyMeasures.add(dailyMeasureMocker(i));
        }

        listLow.add("02-11-2021");
        listLow.add("07-11-2021");
        listLow.add("11-11-2021");

        listMidLow.add("04-11-2021");
        listMidLow.add("14-11-2021");
        listMidLow.add("20-11-2021");

        listMidHigh.add("07-11-2021");
        listMidHigh.add("17-11-2021");
        listMidHigh.add("27-11-2021");

        listHigh.add("10-11-2021");
        listHigh.add("12-11-2021");
        listHigh.add("16-11-2021");
        listHigh.add("25-11-2021");

        listFrequencys.add(FrequencyIndicator.high.getString());
        listFrequencys.add(FrequencyIndicator.medium.getString());
        listFrequencys.add(FrequencyIndicator.low.getString());
        listFrequencys.add(FrequencyIndicator.high.getString());
        listFrequencys.add(FrequencyIndicator.high.getString());
        listFrequencys.add(FrequencyIndicator.medium.getString());
        listFrequencys.add(FrequencyIndicator.low.getString());
        listFrequencys.add(FrequencyIndicator.high.getString());
        listFrequencys.add(FrequencyIndicator.high.getString());

        return patientMeasuresInfoBuilder.setName("Marco Aurélio") //
                .setMeasures(dailyMeasures) //
                .setLow(listLow) //
                .setHigh(listHigh) //
                .setMidhigh(listMidHigh) //
                .setMidlow(listMidLow) //
                .setFrequencys(listFrequencys) //
                .build();
    }

    private DailyMeasuresDTO dailyMeasureMocker(Integer option) {
        List<MeasureDTO> measures = new ArrayList<>();
        if (option == 1) {
            measures.add(measureBuilder.setInsulin("2").setSugarLevel("110")
                    .setSituation(SituationsIndicator.ANTES_ALMOCO.getString())
                    .setObservations("Medi depois de uma caminhada").build());

            measures.add(measureBuilder.setInsulin("3")
                    .setSituation(SituationsIndicator.DEPOIS_LANCHE_TARDE.getString())
                    .setObservations(null).setSugarLevel("200").build());

            measures.add(measureBuilder.setInsulin("1")
                    .setSituation(SituationsIndicator.DEPOIS_JANTAR.getString())
                    .setObservations("Medi logo antes de dormir").setSugarLevel("140").build());

            return dailyMeasuresBuilder.setMeasures(measures).setDate("2020-10-05").build();
        } else if (option == 2) {
            measures.add(measureBuilder.setInsulin("5")
                    .setSituation(SituationsIndicator.ANTES_CAFE.getString())
                    .setObservations("Medi logo antes de dormir").setSugarLevel("104").build());

            measures.add(measureBuilder.setInsulin("1").setSugarLevel("70")
                    .setSituation(SituationsIndicator.DEPOIS_ALMOCO.getString())
                    .setObservations("Comi bolacha").build());

            measures.add(measureBuilder.setInsulin("3")
                    .setSituation(SituationsIndicator.ANTES_LANCHE_TARDE.getString())
                    .setObservations(null).setSugarLevel("116").build());

            measures.add(measureBuilder.setInsulin("1")
                    .setSituation(SituationsIndicator.ANTES_DORMIR.getString())
                    .setObservations("Medi logo antes de dormir").setSugarLevel("850").build());

            return dailyMeasuresBuilder.setMeasures(measures).setDate("2020-10-06").build();
        } else if (option == 3) {
            measures.add(measureBuilder.setInsulin("5")
                    .setSituation(SituationsIndicator.DEPOIS_CAFE.getString())
                    .setObservations("Medi logo antes de dormir").setSugarLevel("150").build());

            measures.add(measureBuilder.setInsulin("1").setSugarLevel("182")
                    .setSituation(SituationsIndicator.ANTES_LANCHE_MANHA.getString())
                    .setObservations(null).build());

            measures.add(measureBuilder.setInsulin("1").setSugarLevel("115")
                    .setSituation(SituationsIndicator.ANTES_ALMOCO.getString())
                    .setObservations("Comi bolacha").build());

            measures.add(measureBuilder.setInsulin("3")
                    .setSituation(SituationsIndicator.DEPOIS_ALMOCO.getString()).setObservations(null)
                    .setSugarLevel("130").build());

            measures.add(measureBuilder.setInsulin("1")
                    .setSituation(SituationsIndicator.ANTES_JANTAR.getString())
                    .setObservations("Medi logo antes de dormir").setSugarLevel("170").build());

            return dailyMeasuresBuilder.setMeasures(measures).setDate("2020-10-07").build();
        } else if (option == 4) {
            measures.add(measureBuilder.setInsulin("5")
                    .setSituation(SituationsIndicator.ANTES_CAFE.getString())
                    .setObservations("Medi logo antes de dormir").setSugarLevel("110").build());

            measures.add(measureBuilder.setInsulin("1").setSugarLevel("80")
                    .setSituation(SituationsIndicator.ANTES_ALMOCO.getString())
                    .setObservations("Comi bolacha").build());

            measures.add(measureBuilder.setInsulin("1")
                    .setSituation(SituationsIndicator.ANTES_JANTAR.getString())
                    .setObservations("Medi logo antes de dormir").setSugarLevel("150").build());

            measures.add(measureBuilder.setInsulin("3")
                    .setSituation(SituationsIndicator.DEPOIS_JANTAR.getString()).setObservations(null)
                    .setSugarLevel("120").build());

            measures.add(measureBuilder.setInsulin("3")
                    .setSituation(SituationsIndicator.ANTES_DORMIR.getString()).setObservations(null)
                    .setSugarLevel("100").build());

            return dailyMeasuresBuilder.setMeasures(measures).setDate("2020-10-08").build();
        } else {
            return null;
        }

    }
}
