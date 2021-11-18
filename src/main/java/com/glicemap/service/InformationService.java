package com.glicemap.service;

import com.glicemap.builder.MedicBuilder;
import com.glicemap.builder.UserBuilder;
import com.glicemap.builder.UserMedicInfoBuilder;
import com.glicemap.dto.UserMedicInfoDTO;
import com.glicemap.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformationService {

    @Autowired
    private UserMedicInfoBuilder userMedicInfoBuilder;
    @Autowired
    private UserService userService;
    @Autowired
    private MedicService medicService;
    @Autowired
    private UserBuilder userBuilder;
    @Autowired
    private MedicBuilder medicBuilder;

    public UserMedicInfoDTO getUserMedicInfo(String documentNumber) {
        User user = userService.getUser(documentNumber);
        return userMedicInfoBuilder.setMedic(medicBuilder.buildModel(user.getMedic())).setUser(userBuilder.buildModel(user)).build();
    }

}

