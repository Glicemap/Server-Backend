package com.glicemap.service;

import com.glicemap.model.Medic;
import org.springframework.stereotype.Service;

@Service
public class MedicService {

    public Medic getMedicInfo(String CRM) {
        // aqui buscariamos na base pelo CRM
        Medic medic = new Medic();
        medic.setCRM(CRM);
        medic.setBirthdate("2000-04-30");
        medic.setName("Dorival Caymmi");
        medic.setEmail("dorival@gmail.com");
        medic.setPassword("saudadesdabahia");
        return medic;
    }

}
