package com.glicemap.service;

import com.glicemap.builder.DailyMeasuresBuilder;
import com.glicemap.builder.MeasureBuilder;
import com.glicemap.builder.PatientMeasuresInfoBuilder;
import com.glicemap.dto.*;
import com.glicemap.exception.BaseBusinessException;
import com.glicemap.model.Medic;
import com.glicemap.model.User;
import com.glicemap.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

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

    @Autowired
    private UserRepository userRepository;

    public User getUser(String documentNumber){
        User user = userRepository.findByDocumentNumber(documentNumber);
        if (user == null){
            logger.error("UserService - getUserInfo Error - User doesn't exists - DocumentNumber [{}]", documentNumber);
            throw new BaseBusinessException("GET_USER_INFO_0001");
        }
        return user;
    }

    public Boolean login(LoginDTO loginDTO){
        User usuario = userRepository.findByDocumentNumber(loginDTO.getLogin());
        if (usuario == null){
            usuario = userRepository.findByEmail(loginDTO.getLogin());
            if (usuario == null) {
                return Boolean.FALSE;
            } else {
                if (usuario.getPassword().equals(loginDTO.getPassword())){
                    return Boolean.TRUE;
                } else {
                    return Boolean.FALSE;
                }
            }
        } else {
            if (usuario.getPassword().equals(loginDTO.getPassword())){
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }
    }

    private boolean isNullOrEmpty(String txt) {
        return txt == null || txt.isEmpty() || txt.trim().isEmpty();
    }

    public Boolean signUp(UserDTO userDTO) throws BaseBusinessException {
        if  ((this.isNullOrEmpty(userDTO.getDocumentNumber())) || //
            (userDTO.getBirthdate() != null) || //
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

        boolean userAlreadyExists = (userRepository.findByDocumentNumber(userDTO.getDocumentNumber()) == null);
        if (userAlreadyExists){
             logger.error("UserService - SignUp Error - Account already exists - DocumentNumber [{}]", userDTO.getDocumentNumber());
             throw new BaseBusinessException("SIGNUP_ERROR_0002");
        }

        User newUser = new User(userDTO.getDocumentNumber(),
                                userDTO.getName(),
                                userDTO.getEmail(),
                                userDTO.getPassword(),
                                userDTO.getBirthdate(),
                                userDTO.getHeight(),
                                userDTO.getWeight(),
                                userDTO.getSugarMin(),
                                userDTO.getSugarMax());

        userRepository.save(newUser);

        return Boolean.TRUE;
    }

    public Boolean deleteMedic(String documentNumber) throws BaseBusinessException {
        User user = userRepository.findByDocumentNumber(documentNumber);
        if (user == null){
            logger.error("UserService - deleteMedic Error - User doesn't exists - DocumentNumber [{}]", documentNumber);
            throw new BaseBusinessException("DELETE_MEDIC_ERROR_0001");
        }

        if (user.getMedic() == null){
            logger.error("UserService - deleteMedic Error - User doesn't have a doctor - DocumentNumber [{}]", documentNumber);
            throw new BaseBusinessException("DELETE_MEDIC_ERROR_0002");
        }

        user.setMedic(null);
        user.setMedicJoin(null);
        userRepository.save(user);
        return Boolean.TRUE;
    }

    public Boolean addMedic(String documentNumber, String medicCRM) throws BaseBusinessException {
        User user = userRepository.findByDocumentNumber(documentNumber);
        if (user == null){
            logger.error("UserService - addMedic Error - User doesn't exists - DocumentNumber [{}]", documentNumber);
            throw new BaseBusinessException("ADD_MEDIC_ERROR_0001");
        }

        Medic medic = medicService.getMedic(medicCRM);
        if (medic == null){
            logger.error("UserService - addMedic Error - Medic doesn't exists - medicCRM [{}]", medicCRM);
            throw new BaseBusinessException("ADD_MEDIC_ERROR_0002");
        }

        if (user.getMedic() != null){
            logger.error("UserService - addMedic Error - User already have a doctor - DocumentNumber [{}] - Actual Doctor CRM [{}]", documentNumber, user.getMedic().getCRM());
            throw new BaseBusinessException("ADD_MEDIC_ERROR_0003");
        }

        user.setMedic(medic);
        user.setMedicJoin(new Date(System.currentTimeMillis()));
        userRepository.save(user);
        return Boolean.TRUE;
    }

    public Boolean updateInfo(UserDTO userDTO) throws BaseBusinessException {
        if  ((this.isNullOrEmpty(userDTO.getDocumentNumber())) || //
                (userDTO.getBirthdate() != null) || //
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

        User usuario = userRepository.findByDocumentNumber(userDTO.getDocumentNumber());

        if (usuario == null){
            logger.error("UserService - Update Info Error - User not found - Document Number [{}]", userDTO.getDocumentNumber());
            throw new BaseBusinessException("UPDATE_INFO_ERROR_0002");
        } else {
            this.updateUser(usuario, userDTO);
            return Boolean.TRUE;
        }
    }

    private void updateUser(User user, UserDTO userDTO){
        user.setSugarMin(userDTO.getSugarMin());
        user.setSugarMax(userDTO.getSugarMax());
        user.setWeight(userDTO.getWeight());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setBirthdate(userDTO.getBirthdate());
        user.setHeight(userDTO.getHeight());

        userRepository.save(user);
    }
}
