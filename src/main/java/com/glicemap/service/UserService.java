package com.glicemap.service;

import com.glicemap.builder.UserBuilder;
import com.glicemap.dto.UserDTO;
import com.glicemap.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserBuilder userBuilder;

    public UserDTO getUserInfo(String documentNumber) {
        // aqui buscariamos na base pelo CPF
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

        return userBuilder.buildModel(user);
    }
}
