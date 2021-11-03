package com.glicemap.builder;

import com.glicemap.dto.MedicDTO;
import com.glicemap.model.Medic;
import org.springframework.stereotype.Component;

@Component
public final class MedicBuilder {
    private String CRM;
    private String name;
    private String email;
    private String password;
    private String birthdate;

    public MedicBuilder setCRM(String CRM) {
        this.CRM = CRM;
        return this;
    }

    public MedicBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MedicBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public MedicBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public MedicBuilder setBirthdate(String birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public MedicDTO build() {
        MedicDTO medicDTO = new MedicDTO();
        medicDTO.setCRM(CRM);
        medicDTO.setName(name);
        medicDTO.setEmail(email);
        medicDTO.setPassword(password);
        medicDTO.setBirthdate(birthdate);
        return medicDTO;
    }

    public MedicDTO buildModel(Medic medic) {
        MedicDTO medicDTO = new MedicDTO();
        medicDTO.setBirthdate(medic.getBirthdate());
        medicDTO.setCRM(medic.getCRM());
        medicDTO.setEmail(medic.getEmail());
        medicDTO.setPassword(medic.getPassword());
        medicDTO.setName(medic.getName());
        return medicDTO;
    }
}
