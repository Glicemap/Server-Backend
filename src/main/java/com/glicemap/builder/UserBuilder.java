package com.glicemap.builder;

import com.glicemap.dto.UserDTO;
import com.glicemap.model.User;
import org.springframework.stereotype.Component;

@Component
public final class UserBuilder {
    private String documentNumber;
    private String name;
    private String email;
    private String password;
    private String birthdate;
    private int height;
    private float weight;
    private int sugarMin;
    private int sugarMax;
    private String crmMedic;

    public UserBuilder setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setBirthdate(String birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public UserBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public UserBuilder setWeight(float weight) {
        this.weight = weight;
        return this;
    }

    public UserBuilder setSugarMin(int sugarMin) {
        this.sugarMin = sugarMin;
        return this;
    }

    public UserBuilder setSugarMax(int sugarMax) {
        this.sugarMax = sugarMax;
        return this;
    }

    public UserBuilder setCRMMedic(String crmMedic) {
        this.crmMedic = crmMedic;
        return this;
    }

    public UserDTO build() {
        UserDTO userDTO = new UserDTO();
        userDTO.setDocumentNumber(documentNumber);
        userDTO.setName(name);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setBirthdate(birthdate);
        userDTO.setHeight(height);
        userDTO.setWeight(weight);
        userDTO.setSugarMin(sugarMin);
        userDTO.setSugarMax(sugarMax);
        userDTO.setCrmMedic(crmMedic);
        return userDTO;
    }

    public UserDTO buildModel(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setDocumentNumber(user.getDocumentNumber());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setBirthdate(user.getBirthdate());
        userDTO.setHeight(user.getHeight());
        userDTO.setWeight(user.getWeight());
        userDTO.setSugarMin(user.getSugarMin());
        userDTO.setSugarMax(user.getSugarMax());
        userDTO.setCrmMedic(user.getMedic().getCRM());
        return userDTO;
    }
}
